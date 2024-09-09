package org.example.pt2024_30226_stoica_sergiu_assignment_3.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DatabaseConnection class provides a method to establish a connection to the database.
 */

public class DatabaseConnection {

    /**
     * Establishes and returns a connection to the database.
     *
     * @return a Connection object to the database
     */

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/ordersmanagement";
        String user = "root";
        String password = "Sergiu1!";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}