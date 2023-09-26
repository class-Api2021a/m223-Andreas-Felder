package com.ubs.data.dao;

import com.ubs.data.dto.Kunde;
import com.ubs.data.dto.Konto;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KundeDAO {
    private Connection connection; // Your database connection goes here

    public KundeDAO(Connection con) {
        this.connection = con;
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

    public List<Kunde> getAllKunden() throws Exception {
        List<Kunde> list = new ArrayList<Kunde>();
        String sql = "SELECT * FROM kunde";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(
                        new Kunde(
                        rs.getString("kontaktdaten"),
                        rs.getString("kundennamen"),
                        rs.getDate("Geburtsdatum"),
                        new ArrayList<Konto>()));
            }
            System.out.println(list.size() +" Kunden gefunden");
        } catch(Exception e){
            System.out.println(e);
        }finally {
            return list;
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
