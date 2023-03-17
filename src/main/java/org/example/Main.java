package org.example;

import DAO.*;
import Model.Client;
import Model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
     public static void main(String[] args) {

         MysqlClientDAO mysqlClientDAO = new MysqlClientDAO();
       /*List<Client>  clients =mysqlClientDAO.showAllClient();
       for(Client client :clients) {
           System.out.println(client.toString());
       }*/
        /* List<Client>  clients2=  mysqlClientDAO.customerSearchByEmail("ylia@gmail.com");
         for(Client client :clients2) {
             System.out.println(client.toString());
         }*/

        /* Client client = new Client();
         client.setId(1);
         client.setName("Ylia");
         client.setSurname("Zinoveva");
         client.setPhoneNumber("0987685667");
         client.setPasword("aaaaa");
         client.setEmail("ylia@gmail.com");
         System.out.println(mysqlClientDAO.checkingExistenceClient(client));*/

     /*  Client client = new Client();
       client.setEmail("natalia@gmail.com");
         System.out.println( mysqlClientDAO.updatePasswordClient(client,"hhhhhh"));

     }*/
      /*  Client newClient = new Client("HHHH","GGGG","jjjjjj","fffff","ffffff");
         mysqlClientDAO.addClient(newClient);*/

        /* ProductDAO productDAO = new MysqlProductDAO();
         List<Product> products =productDAO.showAllProducts();
         if(products.size()>0){
             for (Product product:products){
                 System.out.println(product);
             }
         */
       /*  ProductDAO productDAO = new MysqlProductDAO();
        List<Product> products =productDAO.searchProductByName("LG-356");
         if(products!=null) {
             for (Product product : products) {
                 System.out.println(product);
             }
         }*/

        /* Product product = new Product();
         product.setNameProduct("LG-357456");
         product.setDescriptionProduct("hgjfdfsdad");
         product.setPrice(6000);
         product.setQuantity(8);
         //System.out.println(productDAO.updateDescription(product,"washing machine"));
         System.out.println(productDAO.addProduct(product));*/

Client client = new Client();
client.setId(1);
client.setName("Ylia");
client.setSurname("Zinoveva");
client.setPhoneNumber("0987685667");
client.setPasword("aaaaa");
client.setEmail("ylia@gmail.com");

         HashMap<Integer,Integer> productCountForOrder = new HashMap<>();
         productCountForOrder.put(4,1);
         productCountForOrder.put(3,1);

         MysqlOrderDAO mysqlOrderDAO = new MysqlOrderDAO();
           System.out.println(mysqlOrderDAO.createOrder(client,productCountForOrder));



     }
}



