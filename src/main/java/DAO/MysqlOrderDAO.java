package DAO;

import Model.Client;
import Model.Product;
import Model.NumberOfProductsInOrder;

import java.io.Closeable;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MysqlOrderDAO implements OrderDAO {

    private Client checkСlientExistsInDB(Client client) {
        Client dbClient = null;
        MysqlClientDAO mysqlClientDAO = new MysqlClientDAO();
        dbClient = mysqlClientDAO.checkingExistenceClient(client);
        return dbClient;
    }

    private List<Product> searchProductsById(HashMap<Integer, Integer> productsCount) {
        Set<Integer> listIdProductsIdFromClientOrder = productsCount.keySet();
        List<Product> productsBDForClientOrder = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        MysqlProductDAO mysqlProductDAO = new MysqlProductDAO();
        try {
            connection = MySQLConection.getConnection();
            for (Integer Id : listIdProductsIdFromClientOrder) {
                statement = connection.prepareStatement("select * from product where product.Id=?;");
                statement.setInt(1, Id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Product product1 = new Product();
                    product1 = mysqlProductDAO.setProducttFromResultSet(resultSet);
                    productsBDForClientOrder.add(product1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            closeResourses(resultSet);
            closeResourses(statement);
            closeResourses(connection);
        }
        return productsBDForClientOrder;
    }

    private void closeResourses(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String checkProductsDatabaseMatchCustomerOrder(List<Product> productsBDForClientOrder, HashMap<Integer, Integer> productsCount) {
        StringBuilder checCountProductInDB = new StringBuilder("Quantity of goods specified by you in the order exceeds availability");
        boolean flag = false;
        for (Product product : productsBDForClientOrder) {
            int countProductInOrder = productsCount.get(product.getId());
            if (product.getQuantity() < countProductInOrder) {
                checCountProductInDB.append("Id " + product.getId() + " count  " + product.getQuantity());
                flag = true;
            }
        }
        if (flag == true) {
            return checCountProductInDB.toString();
        } else {
            return "";
        }
    }

    private List<NumberOfProductsInOrder> creatingListProductsQuantities(List<Product> products, HashMap<Integer, Integer> productsCount) {
        List<NumberOfProductsInOrder> listProductForOrder = new ArrayList<>();
        for (Product product : products) {
            int countProducts = productsCount.get(product.getId());
            NumberOfProductsInOrder productForOrder = new NumberOfProductsInOrder();
            productForOrder.setProduct(product);
            productForOrder.setQuanlityProudtForOrder(countProducts);
            listProductForOrder.add(productForOrder);
        }
        return listProductForOrder;
    }

    private int callProcedurNumberOrder(Connection connection) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("{call number_order(?)}");
        callableStatement.registerOutParameter(1, java.sql.Types.INTEGER);
        callableStatement.execute();
        return callableStatement.getInt(1);
    }

    private String precheckBeforeCreatingOrder(Client client, HashMap<Integer, Integer> productsCount) {
        String rezult = "";
        if (checkСlientExistsInDB(client) == null) {
            rezult = "The client is not in the database";
        }
        List<Product> products = searchProductsById(productsCount);
        String verificationСomplianceDBToOrderedGoods = "";
        if (products.size() != 0) {
            verificationСomplianceDBToOrderedGoods = checkProductsDatabaseMatchCustomerOrder(products, productsCount);
            if (verificationСomplianceDBToOrderedGoods.length() > 0) {
                return verificationСomplianceDBToOrderedGoods;
            }
        } else {
            rezult = "selected products are out of stock";
        }
        return rezult;
    }

    private boolean createPreparedStatement(Connection connection, NumberOfProductsInOrder productForOrder, Client client, int namberOfOrder) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into orders (Id_product,Id_client,quantity_product,number_order,date_order,time_order )" +
                "value (?,?,?,?,?,?);");
        preparedStatement.setInt(1, productForOrder.getProduct().getId());
        preparedStatement.setInt(2, client.getId());
        preparedStatement.setInt(3, productForOrder.getQuanlityProudtForOrder());
        preparedStatement.setInt(4, namberOfOrder);
        preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
        preparedStatement.setTime(6, Time.valueOf(LocalTime.now()));
        return preparedStatement.execute();
    }

    @Override
    public String createOrder(Client client, HashMap<Integer, Integer> productsCount) {
        precheckBeforeCreatingOrder(client, productsCount);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Product> products = searchProductsById(productsCount);
        List<NumberOfProductsInOrder> listProductsQuantities = creatingListProductsQuantities(products, productsCount);
        try {
            connection = MySQLConection.getConnection();
            connection.setAutoCommit(false);
            int namberOfOrder = callProcedurNumberOrder(connection);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            for (NumberOfProductsInOrder productForOrder : listProductsQuantities) {
                createPreparedStatement(connection, productForOrder, client, namberOfOrder);
                PreparedStatement stmt = connection.prepareStatement(" update  product  set product.quantity =? " +
                        "where product.Id=?;");
                int newCountProduct = productForOrder.getProduct().getQuantity() - productForOrder.getQuanlityProudtForOrder();
                System.out.println(newCountProduct);
                stmt.setInt(1, (newCountProduct));
                stmt.setInt(2, productForOrder.getProduct().getId());
                stmt.execute();
            }
            connection.commit();
            return "Oreder created";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
                return "Oreder didn't creat";
            }
        } finally {
            closeResourses(preparedStatement);
            closeResourses(connection);
        }
        return "Oreder didn't creat";
    }


    @Override
    public boolean deleteOrder(int numberOrder) {
        return false;
    }
}
