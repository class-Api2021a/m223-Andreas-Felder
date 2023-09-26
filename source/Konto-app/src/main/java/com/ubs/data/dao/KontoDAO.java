package com.ubs.data.dao;

import com.ubs.data.dto.Konto;

import com.ubs.data.setExtractors.KontoExtractor;
import jakarta.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class KontoDAO {
    private Connection connection;

    public KontoDAO(Connection connection) {
        this.connection = connection;
    }

    public int create(Konto konto) {
        String insertQuery = "INSERT INTO konto (kontonummer, kontostand, eroeffnungsdatum) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDouble(1, konto.getKontonummer());
            preparedStatement.setDouble(2, konto.getKontostand());
            preparedStatement.setDate(3, new java.sql.Date(konto.getEröffnungsdatum().getTime()));
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return 0;
    }

    public int updateByID (Konto konto, double kontoID) {
        String updateQuery = "UPDATE konto SET kontostand = ?, eroeffnungsdatum = ? WHERE kontonummer = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(3, kontoID);
            preparedStatement.setDouble(1, konto.getKontostand());
            preparedStatement.setDate(2, konto.getEröffnungsdatum());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

   // public Optional<List<Konto>> getAll(){
   //     String getQuery = "SELECT * FROM konto";
   //     try{
   //         PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
   //         ResultSet resultSet = preparedStatement.executeQuery();
   //         return extract(resultSet);
   //     } catch (SQLException e) {
   //         throw new RuntimeException(e);
   //     }
   // }
}
