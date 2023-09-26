package com.ubs.data.dao;

import com.ubs.data.dto.Raumreservierung;
import com.ubs.data.interfaces.ISQLOperator;
import com.ubs.expections.TransactionFailedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RaumreservierungDAO implements ISQLOperator<Raumreservierung>, AutoCloseable {

    private Connection con;

    private final String createQuery = "INSERT INTO Raumreservierung (Datum, RaumID, Name) " +
                                       "SELECT ?, ?, ? ;";
    private final String readQuery = "SELECT * FROM raumreservierung WHERE ReservierungID = ?";
    private final String updateQuery = "UPDATE raumreservierung SET Datum = ?, RaumID = ?, Name = ? WHERE ReservierungID = ?";
    private final String deleteQuery = "DELETE FROM raumreservierung WHERE ReservierungID = ?";
    private final String getAllQuery = "SELECT * FROM raumreservierung";
    private final String findByRoomIDQuery = "SELECT * FROM raumreservierung WHERE RaumID = ?";


    public RaumreservierungDAO(Connection con) {
        this.con = con;
    }


    @Override
    public int create(Raumreservierung raumreservierung) {
        int result = 0;
        try{

            PreparedStatement create = con.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            create.setTimestamp(1, raumreservierung.getDatum());
            create.setInt(2, raumreservierung.getRaumID());
            create.setString(3, raumreservierung.getName());

            result = create.executeUpdate();
            ResultSet genID = create.getGeneratedKeys();
            List<Raumreservierung> rsList = findByRoomID(raumreservierung.getRaumID());
            if(result != 1){
                con.rollback();
                throw new TransactionFailedException("Transaction failed, Room already reserved for that date");

            } else if (rsList.size() >=1){
                genID.next();
                for (Raumreservierung r : rsList){
                    if(r.getReservierungID() != genID.getInt(1) &&
                       new Date(r.getDatum().getTime())
                       .equals(new Date(raumreservierung.getDatum().getTime()))){
                        con.rollback();
                        throw new TransactionFailedException("Transaction failed, Room already reserved for that date");
                    }
                }
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Raumreservierung read(Raumreservierung raumreservierung) {
        Raumreservierung result = null;
        try{
            PreparedStatement read = con.prepareStatement(readQuery);
            read.setInt(1, raumreservierung.getReservierungID());
            ResultSet rs = read.executeQuery();
            if(rs.next()){
                result = new Raumreservierung(
                        rs.getInt("ReservierungID"),
                        rs.getTimestamp("Datum"),
                        rs.getInt("RaumID"),
                        rs.getString("Name"));
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Raumreservierung raumreservierung, Raumreservierung old_t) {
        int result = 0;
        try{
            PreparedStatement update = con.prepareStatement(updateQuery);
            update.setTimestamp(1, raumreservierung.getDatum());
            update.setInt(2, raumreservierung.getRaumID());
            update.setString(3, raumreservierung.getName());
            update.setInt(4, old_t.getReservierungID());
            result = update.executeUpdate();
            if(result != 1){
                con.rollback();
                throw new TransactionFailedException("Transaction failed, no rows affected.");
            }
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Raumreservierung raumreservierung) {
        int result = 0;
        try {
            PreparedStatement delete = con.prepareStatement(deleteQuery);
            delete.setInt(1, raumreservierung.getReservierungID());
            result = delete.executeUpdate();
            if(result != 1){
                con.rollback();
                throw new TransactionFailedException("Transaction failed, no rows affected.");
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Raumreservierung> getAll() {
        List<Raumreservierung> result = new ArrayList<>();
        try {
            PreparedStatement getAll = con.prepareStatement(getAllQuery);
            ResultSet rs = getAll.executeQuery();
            while(rs.next()){
                result.add(new Raumreservierung(
                        rs.getInt("ReservierungID"),
                        rs.getTimestamp("Datum"),
                        rs.getInt("RaumID"),
                        rs.getString("Name")));
            }
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Raumreservierung> findByRoomID(int roomID) {
        List<Raumreservierung> result = new ArrayList<>();
        try{
            PreparedStatement findByRoomID = con.prepareStatement(findByRoomIDQuery);
            findByRoomID.setInt(1, roomID);
            ResultSet rs = findByRoomID.executeQuery();
            while(rs.next()){
                result.add(new Raumreservierung(
                        rs.getInt("ReservierungID"),
                        rs.getTimestamp("Datum"),
                        rs.getInt("RaumID"),
                        rs.getString("Name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public void close() throws Exception {
        con.close();
    }

}
