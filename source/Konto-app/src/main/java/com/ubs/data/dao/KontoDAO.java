package com.ubs.data.dao;

import com.ubs.data.dto.Konto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KontoDAO {
    private Connection connection; // Your database connection goes here

    public KontoDAO(Connection connection) {
        this.connection = connection;
    }

    public void createKonto(Konto konto) {
        String insertQuery = "INSERT INTO konto (kontonummer, kontostand, eroeffnungsdatum) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDouble(1, konto.getKontonummer());
            preparedStatement.setDouble(2, konto.getKontostand());
            preparedStatement.setDate(3, new java.sql.Date(konto.getEröffnungsdatum().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    // Implement other CRUD methods as needed (update, delete, getById, etc.)
}
