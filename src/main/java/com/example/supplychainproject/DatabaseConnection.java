package com.example.supplychainproject;
import javafx.scene.chart.PieChart;

import java.sql.*;
public class DatabaseConnection
{
    private static final String databaseConn="jdbc:mysql://localhost:3306/bitlaradhika";
    private static final String user="root";
    private  static final String password="Radhika@123";


    public Statement getStatement()
    {
        Statement statement;
        Connection conn;
        try
        {
            conn=DriverManager.getConnection(databaseConn,user,password);
            statement=conn.createStatement();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return statement;
    }
    public ResultSet getQueryTable(String query)
    {
        Statement statement=getStatement();
        try
        {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  static  void main(String[] args)
    {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        ResultSet rs=databaseConnection.getQueryTable("select email,mobile from student");
        try
        {
            while(rs.next())
            {
                System.out.println(rs.getString("email")+" "+rs.getString("mobile"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
