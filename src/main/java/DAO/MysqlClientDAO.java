package DAO;

import Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlClientDAO implements ClientDAO {
    @Override
    public boolean addClient(Client client) {
        boolean rezult = false;
        try (Connection connection = MySQLConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     " insert into client_shop (name_client, surname_client,phone_number,pasword_client,email)" +
                             "value(?,?,?,?,?);")) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setString(4, client.getPasword());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.execute();
            rezult = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezult;
    }

    @Override
    public boolean updatePasswordClient(Client client, String newPassword) {
        boolean rezult = false;
        Client clientInDb = checkingExistenceClient(client);
        if (clientInDb != null) {
            try (Connection connection = MySQLConection.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(
                         " update  client_shop  set client_shop.pasword_client =? " +
                                 "where client_shop.email=?;")) {
                stmt.setString(1, newPassword);
                stmt.setString(2, client.getEmail());
                stmt.execute();
                rezult = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rezult;
    }

    public Client checkingExistenceClient(Client client) {
        Client client1 = null;
        try (Connection connection = MySQLConection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     "select * from client_shop where client_shop.email =? and client_shop.pasword_client=?")) {
            stmt.setString(1, client.getEmail());
            stmt.setString(2, client.getPasword());
            try (ResultSet resultSet = stmt.executeQuery()) {
                client1 = new Client();
                while (resultSet.next()) {
                    client1 = setClientFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client1;
    }

    @Override
    public List<Client> showAllClient() {
        List<Client> clientList = null;
        try (Connection connection = MySQLConection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from client_shop;")) {
            clientList = new ArrayList<>();
            while (rs.next()) {
                Client client = setClientFromResultSet(rs);
                clientList.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }

    @Override
    public List<Client> customerSearchByEmail(String email) {
        ResultSet resultSet = null;
        List<Client> clientList = null;
        try (Connection connection = MySQLConection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("select * from client_shop where client_shop.email =?")) {
            stmt.setString(1, email);
            resultSet = stmt.executeQuery();
            clientList = new ArrayList<>();
            while (resultSet.next()) {
                Client client = setClientFromResultSet(resultSet);
                clientList.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return clientList;
    }

    private Client setClientFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt("Id"));
        client.setName(resultSet.getString("name_client"));
        client.setSurname(resultSet.getString("surname_client"));
        client.setPhoneNumber(resultSet.getString("phone_number"));
        client.setPasword(resultSet.getString("pasword_client"));
        client.setEmail(resultSet.getString("email"));
        return client;
    }
}
