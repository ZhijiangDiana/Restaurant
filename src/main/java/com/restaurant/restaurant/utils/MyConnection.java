package com.restaurant.restaurant.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String HOST_ADDRESS="localhost";
    private static final String DATABASE_NAME="restaurant";

    private static final String USER = "root";
    private static final String PASSWORD = "newphone2015";
    private static String dbUrl="jdbc:mysql://"+HOST_ADDRESS+":3306/"+DATABASE_NAME+"?useUnicode=true&serverTimezone=GMT&characterEncoding=UTF-8&useSSL=false";
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection conn = DriverManager.getConnection(dbUrl,USER,PASSWORD);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
