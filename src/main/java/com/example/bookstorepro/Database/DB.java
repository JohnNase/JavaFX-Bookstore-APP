package com.example.bookstorepro.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection mockConnection = null;
    public static Connection RealConnection = null;


    public static Connection getConnection() {
        if(mockConnection != null) {
            return mockConnection;
        }
        else {
            try {
                String url = "jdbc:mysql://127.0.0.1:3306/booklist";
                String username = "root";
                String password = "sarasara1";

                // Register the MySQL JDBC driver (for MySQL 8.x)
                Class.forName("com.mysql.cj.jdbc.Driver");

                RealConnection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return RealConnection;
        }
    }

    public static void setMockConnection(Connection mockConnection) {
        DB.mockConnection = mockConnection;
    }




}

