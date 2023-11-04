package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Search {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public Search(int id, String name, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }
    public static ObservableList<Search> getSearched(String name){
        String query = "select * from product where name like '%"+name+"%'";
        return fetchSearchedData(query);
    }
    public static ObservableList<Search> fetchSearchedData(String query){
        ObservableList<Search> data = FXCollections.observableArrayList();
        DBConnection dbConnection = new DBConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(query);
            while(rs.next()){
                Search search = new Search(rs.getInt("id"), rs.getString("name"),rs.getDouble("price"));
                data.add(search);
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
}
