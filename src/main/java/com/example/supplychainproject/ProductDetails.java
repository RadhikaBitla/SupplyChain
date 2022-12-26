package com.example.supplychainproject;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
public class ProductDetails
{
    public TableView<Product> productTable;

    public Pane getAllProducts()
    {
        TableColumn id=new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        //ObservableList<Product> data= FXCollections.observableArrayList();
        //data.add(new Product(1,"lenova",8455));
        //data.add(new Product(2,"Redmi", 8000));

        Product product=new Product();

        ObservableList<Product> data=FXCollections.observableArrayList();
        data= product.getAllProducts();
        productTable=new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChainMain.width,SupplyChainMain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane=new Pane();
        tablePane.setStyle("-fx-background-color:black;");
        tablePane.setMinSize(SupplyChainMain.width,SupplyChainMain.height);
        tablePane.getChildren().addAll(productTable);

        return tablePane;

    }
    public Pane getProductsByName(String Productname)
    {
        TableColumn id=new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        //ObservableList<Product> data= FXCollections.observableArrayList();
        //data.add(new Product(1,"lenova",8455));
        //data.add(new Product(2,"Redmi", 8000));

        Product product=new Product();

        ObservableList<Product> data=FXCollections.observableArrayList();
        data= product.getProductsByName(Productname);
        productTable=new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChainMain.width,SupplyChainMain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane=new Pane();
        tablePane.setStyle("-fx-background-color:black;");
        tablePane.setMinSize(SupplyChainMain.width,SupplyChainMain.height);
        tablePane.getChildren().addAll(productTable);

        return tablePane;

    }
}
