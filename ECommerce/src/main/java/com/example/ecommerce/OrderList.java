package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class OrderList {

    private TableView<Order> orderTable;
    public VBox createTable(ObservableList<Order> data){
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("QUANTITY");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn date = new TableColumn("ORDER DATE");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn status = new TableColumn("ORDER STATUS");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        orderTable = new TableView<>();
        orderTable.getColumns().addAll(id,name,price,quantity,date,status);
        orderTable.setItems(data);
        orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(orderTable);
        return vBox;
    }
   /* public VBox getDummyTable(){
        ObservableList<Order> data = FXCollections.observableArrayList();
        data.add(new Order(1,"Dell",55000,2,"11","ordered"));
        data.add(new Order(3,"Lenovo",50000,2,"12","ordered"));
        return createTable(data);
    }*/
    public VBox getAllOrder(){
        ObservableList<Order> data = Order.getAllOrder();
        return createTable(data);
    }
}
