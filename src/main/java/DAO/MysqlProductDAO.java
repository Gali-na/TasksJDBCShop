package DAO;

import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlProductDAO implements ProductDAO {
    @Override
    public boolean addProduct(Product product) {
        int idProduct = 0;
        boolean rezult = false;
        idProduct = getIdProduct(product);
        if (idProduct == 0) {
            try (Connection connection = MySQLConection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement("insert into product  " +
                         "( name_product, description_product, price,quantity)" +
                         "value (?,?,?,?);")) {
                stmt.setString(1, product.getNameProduct());
                stmt.setString(2, product.getDescriptionProduct());
                stmt.setInt(3, product.getPrice());
                stmt.setInt(4, product.getQuantity());
                stmt.execute();
                rezult = true;
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return rezult;
    }

    @Override
    public boolean updateDescription(Product product1, String description) {
        int idProduct = 0;
        boolean rezult = false;
        idProduct = getIdProduct(product1);
        if (idProduct != 0) {
            try (Connection connection = MySQLConection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         " update  product  set product.description_product =? " +
                                 "where product.Id=?;")) {
                stmt.setString(1, description);
                stmt.setInt(2, idProduct);
                stmt.execute();
                rezult = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return rezult;
    }

    public int getIdProduct(Product product) {
        int rezult = 0;
        Product product1 = new Product();
        try (Connection connection = MySQLConection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from product where product.name_product=? and product.description_product=? and product.price=? and product.quantity=?;")) {
            stmt.setString(1, product.getNameProduct());
            stmt.setString(2, product.getDescriptionProduct());
            stmt.setInt(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    product1 = setProducttFromResultSet(resultSet);
                    rezult = product1.getId();
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return rezult;
    }

    @Override
    public List<Product> showAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = MySQLConection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from product;")) {
            while (rs.next()) {
                Product product = setProducttFromResultSet(rs);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Product> searchProductByName(String name) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = MySQLConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     " select * from product where product.name_product=?;")) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = setProducttFromResultSet(resultSet);
                    productList.add(product);
                }
            } catch (SQLException e) {
                e.getMessage();
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return productList;
    }

    protected Product setProducttFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setNameProduct(resultSet.getString("name_product"));
        product.setDescriptionProduct(resultSet.getString("description_product"));
        product.setPrice(resultSet.getInt("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setId(resultSet.getInt("Id"));
        return product;
    }
}
