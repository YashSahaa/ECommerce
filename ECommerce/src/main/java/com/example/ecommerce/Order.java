package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty date;
    private SimpleStringProperty status;

    public Order(int id, String name, double price, int quantity, String date, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.date = new SimpleStringProperty(date);
        this.status = new SimpleStringProperty(status);
    }

    public static ObservableList<Order> getAllOrder(){
        String selectAllOrder = "select orders.id , product.name , product.price,orders.quantity,orders.order_date ,orders.order_status from orders join product where orders.product_id = product.id;";
        return fetchOrderData(selectAllOrder);
    }
    public static ObservableList<Order> fetchOrderData(String query){
        ObservableList<Order> data = FXCollections.observableArrayList();
        DBConnection dbConnection = new DBConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(query);
            while(rs.next()){
                Order order = new Order(rs.getInt("id"), rs.getString("name"),rs.getDouble("price"),rs.getInt("quantity"),rs.getString("order_date"), rs.getString("order_status"));
                data.add(order);
            }
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public double getPrice() {
        return price.get();
    }
    public int getQuantity() {
        return quantity.get();
    }
    public String getDate() {
        return date.get();
    }
    public String getStatus() {
        return status.get();
    }

    public static boolean placeOrder(Customer customer , Product product){
        String groupId = "select max(group_id)+1 id from orders";
        DBConnection dbConnection = new DBConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(groupId);
            if(rs.next()){
                String placeOrder = "insert into orders (group_id,customer_id,product_id)values("+rs.getInt("id")+","+customer.getId()+","+ product.getId()+")";
                return dbConnection.updateDatabase(placeOrder)!=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static int placeMultipleOrder(Customer customer , ObservableList<Product> productList){
        String groupId = "select max(group_id)+1 id from orders";
        DBConnection dbConnection = new DBConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(groupId);
            int count = 0;
            if(rs.next()){
                for(Product product:productList){
                    String placeOrder = "insert into orders (group_id,customer_id,product_id)values("+rs.getInt("id")+","+customer.getId()+","+ product.getId()+")";
                    count+= dbConnection.updateDatabase(placeOrder);
                }
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
