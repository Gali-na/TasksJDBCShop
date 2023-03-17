package Model;

public class NumberOfProductsInOrder {
    private Product product;
    private int quanlityProudtForOrder;

    public NumberOfProductsInOrder() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuanlityProudtForOrder() {
        return quanlityProudtForOrder;
    }

    public void setQuanlityProudtForOrder(int quanlityProudtForOrder) {
        this.quanlityProudtForOrder = quanlityProudtForOrder;
    }

    @Override
    public String toString() {
        return "ProductForOrder{" +
                "product=" + product +
                ", quantityProudt=" + quanlityProudtForOrder +
                '}';
    }
}
