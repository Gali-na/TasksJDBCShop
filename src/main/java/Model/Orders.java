package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Orders {
    private  int id;
    private int idProduct;
    private  int idClient;
    private int quantityProduct;
    private  int numberOrder;
    private LocalDate dateOrder;
    private LocalTime timeOrder;

    public Orders() {
    }

    public Orders(int id, int idProduct, int idClient, int quantityProduct, int numberOrder,
                  LocalDate dateOrder, LocalTime timeOrder) {
        this.id = id;
        this.idProduct = idProduct;
        this.idClient = idClient;
        this.quantityProduct = quantityProduct;
        this.numberOrder = numberOrder;
        this.dateOrder = dateOrder;
        this.timeOrder = timeOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public LocalTime getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(LocalTime timeOrder) {
        this.timeOrder = timeOrder;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", idProduct=" + idProduct +
                ", idClient=" + idClient +
                ", quantityProduct=" + quantityProduct +
                ", numberOrder=" + numberOrder +
                ", dateOrder=" + dateOrder +
                ", timeOrder=" + timeOrder +
                '}';
    }
}
