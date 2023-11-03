package com.example.ecommerce;

import java.sql.ResultSet;

public class Signup {
    public boolean registerUser(String name,String email, String mobile,String pass ,String address){
        String query =  "Select * from customer WHERE email = '"+email+"' AND password = '"+pass+"'";
        DBConnection dbConnection = new DBConnection();
        try {
            ResultSet rs = dbConnection.getQueryTable(query);
            if(!rs.next()){

                String store = "insert into customer (name,email,mobile,password,address) values('" + name + "' ,'" + email + "','" + mobile + "','" + pass + "','" + address + "');";
                return dbConnection.updateDatabase(store) != 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Signup signup = new Signup();
        boolean reg = signup.registerUser("tejus","tej12@gmail.com","1234567898","123456","acd");
        System.out.println(reg);
    }
}
