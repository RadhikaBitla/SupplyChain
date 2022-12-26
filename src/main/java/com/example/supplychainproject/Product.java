package com.example.supplychainproject;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product
{
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public Product() {

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

    public Product(int id, String name, int price)
    {
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);
    }
    public ObservableList<Product> getAllProducts()
    {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> data= FXCollections.observableArrayList();
        String selectMethod="select * from product";
        try
        {
            ResultSet rs=databaseConnection.getQueryTable(selectMethod);
            while(rs.next())
            {
                data.add(new Product(rs.getInt("product_id"),rs.getString("name"),rs.getInt("price")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public ObservableList<Product> getProductsByName(String name)
    {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Product> data= FXCollections.observableArrayList();
        String selectMethod=String.format("SELECT * FROM product WHERE lower(name) LIKE '%%%s%%' ",name.toLowerCase());
        try
        {
            ResultSet rs=databaseConnection.getQueryTable(selectMethod);
            while(rs.next())
            {
                data.add(new Product(rs.getInt("product_id"),rs.getString("name"),rs.getInt("price")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public String getUserName(String email)
    {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        String query=String.format("select first_name from customer where email = '%s' ",email);
        try
        {
            ResultSet rs=databaseConnection.getQueryTable(query);
            if(rs!=null && rs.next())
            {
                return rs.getString("first_name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
