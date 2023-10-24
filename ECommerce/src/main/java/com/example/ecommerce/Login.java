package com.example.ecommerce;

import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.concurrent.Callable;

public class Login {
    public Customer customeLogin(String username,String password){
        String query = "Select * from customer WHERE email = '"+username+"' AND password = '"+password+"'";
        DBConnection connection = new DBConnection();
        try {
            ResultSet rs = connection.getQueryTable(query);
            if(rs.next()){
              return new Customer(rs.getInt("id"),rs.getString("name"),
                      rs.getString("email"), rs.getString("mobile") );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Login login = new Login();
        Customer customer = login.customeLogin("legendyash56789@gmail.com","12345678");
        System.out.println("Welcome : " +customer.getName());
        //System.out.println(login.customeLogin("legendyash56789@gmail.com","12345678"));
    }
}
