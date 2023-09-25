package com.ubs.data.dao;

import com.ubs.data.dto.Kunde;
import com.ubs.data.dto.Konto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KundeDAO {
    private Connection connection; // Your database connection goes here

    public KundeDAO(Connection connection) {
        this.connection = connection;
    }

    public void createKunde(Kunde kunde) {
        String insertQuery = "INSERT INTO kunde (kontaktdaten, kunden_namen, geburtsdatum) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, kunde.getKontaktdaten());
            preparedStatement.setString(2, kunde.getKundenNamen());
            preparedStatement.setDate(3, new java.sql.Date(kunde.getGeburtsdatum().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    public List<Konto> getKontenForKunde(int kundeId) {
        List<Konto> konten = new ArrayList<Konto>();
        String selectQuery = "SELECT * FROM konto WHERE kunde_id = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, kundeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Konto konto = new Konto();
                konto.setKontonummer(resultSet.getDouble("kontonummer"));
                konto.setKontostand(resultSet.getDouble("kontostand"));
                konto.setEröffnungsdatum(resultSet.getDate("eroeffnungsdatum"));
                konten.add(konto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return konten;
    }

    // Implement other CRUD methods as needed (update, delete, getById, etc.)
}
