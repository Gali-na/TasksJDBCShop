package DAO;

import Model.Client;

import java.sql.SQLException;
import java.util.HashMap;

public interface OrderDAO {
  String createOrder(Client client, HashMap<Integer,Integer>productsCount) throws SQLException;
  boolean deleteOrder(int numberOrder);

}
