package Model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String nameProduct;
    private String descriptionProduct;
    private int price;
    private int quantity;

    public Product() {

    }

    public Product( String nameProduct, String descriptionProduct, int price, int quantity) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", descriptionProduct='" + descriptionProduct + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
