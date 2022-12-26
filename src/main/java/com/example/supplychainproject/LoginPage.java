package com.example.supplychainproject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage
{
    public byte[] getSHA(String password)
    {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String getEncryptedPassword(String password)
    {
        String encrypted="";
        try
        {
            BigInteger number=new BigInteger(1,getSHA(password));
            StringBuilder hexString=new StringBuilder(number.toString(16));
            return  hexString.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return encrypted;
    }
    public boolean checking(String email, String password) {
        String query = String.format("select email, mobile from customer where email = '%s' and password='%s' ", email, password);
        DatabaseConnection databaseConnection=new DatabaseConnection();
        ResultSet rs=databaseConnection.getQueryTable(query);
        try {
            if (rs!=null && rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String args[])
    {
        LoginPage loginPage=new LoginPage();
      //  System.out.println(loginPage.checking("bitlaradhika@gmail.com", "6305908601"));
        System.out.println(loginPage.getEncryptedPassword("radhika"));
    }
}
