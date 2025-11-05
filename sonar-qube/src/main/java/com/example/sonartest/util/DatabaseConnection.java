package com.example.sonartest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Hardcoded credentials (vulnerability)
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password123");
            } catch (SQLException e) {
                System.err.println("DB connection failed: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Swallowed exception (code smell)
        }
    }
}
