package com.springboot.workers.jdbcexample.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
 private static final String USERNAME = "root";
 private static final String PASSWORD = "Vamsireddy@460";
 private static final String URL = "jdbc:mysql://localhost:3306/org";
 private static Connection connection = null;

 private DatabaseConnection() 
 {

 }

 public static Connection getConnection() throws SQLException {
     if (connection == null) {
         connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             System.out.println("Connected to database server "
             + connection.getMetaData().getDatabaseProductName()
             + " version: "
             + connection.getMetaData().getDatabaseProductVersion());
     }
     return connection;
 }

 public static void closeConnection() throws SQLException {
     if (connection != null)
         connection.close();
 }
}