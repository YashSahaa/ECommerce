package com.example.ecommerce;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class SearchList {
    private TableView<Search> searchTable;

    public VBox createTable(ObservableList<Search> data) {
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchTable = new TableView<>();
        searchTable.getColumns().addAll(id, name, price);
        searchTable.setItems(data);
        searchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(searchTable);
        return vBox;
    }
    public VBox getAllSearched(String name){
        ObservableList<Search> data = Search.getSearched(name);
        return createTable(data);
    }
}
