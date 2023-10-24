package com.example.ecommerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {

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
