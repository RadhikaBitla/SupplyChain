package com.example.supplychainproject;

public class PlaceOrder
{
    public  boolean ordering(String email, Product product)
    {
        DatabaseConnection databaseConnection=new DatabaseConnection();
        String query=String.format("insert into orders(cutomerId,productId) values((select customer_id from customer where email='%s'),'%s')",email,product.getId());
        int affectedRow=0;
        try {
            affectedRow = databaseConnection.executeUpdateQuery(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return affectedRow!=0;
    }
}
