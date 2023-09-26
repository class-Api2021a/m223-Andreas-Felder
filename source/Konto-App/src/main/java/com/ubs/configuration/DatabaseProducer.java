package com.ubs.configuration;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseProducer {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/m223";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection produceConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to produce database connection", e);
        }
    }
}
