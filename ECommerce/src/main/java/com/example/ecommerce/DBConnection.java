package com.example.ecommerce;
import java.sql.*;
public class DBConnection {
    private final String dburl = "jdbc:mysql://localhost:3306/ecommerce";
    private final String username = "root";
    private final String password = "yash@11";
    private Statement getStatement(){
        try {
            Connection connection = DriverManager.getConnection(dburl,username,password);
            return connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryTable(String query){
        try {
            Statement statement = getStatement();
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updateDatabase(String query){
        try {
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        DBConnection conn = new DBConnection();
        ResultSet rs = conn.getQueryTable("Select * from customer");
        if(rs!=null) System.out.println("Connection Successfull");
        else System.out.println("Connection Failed");
    }
}
