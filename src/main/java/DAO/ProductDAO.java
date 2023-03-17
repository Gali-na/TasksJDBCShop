package DAO;

import Model.Client;
import Model.Product;

import java.util.List;

public interface ProductDAO {
    boolean addProduct(Product product);
    boolean updateDescription(Product product, String description);

    List<Product> showAllProducts();

    List<Product> searchProductByName(String name);
}
