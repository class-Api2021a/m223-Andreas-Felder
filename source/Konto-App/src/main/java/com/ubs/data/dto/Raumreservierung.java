package com.ubs.data.dto;

import java.sql.Timestamp;

public class Raumreservierung {
    private int ReservierungID;
    private Timestamp Datum;
    private int RaumID;
    private String Name;


    public Raumreservierung() {
    }

    public Raumreservierung(int reservierungID) {
        ReservierungID = reservierungID;
    }
    public Raumreservierung(Timestamp datum, int raumID, String name) {
        Datum = datum;
        RaumID = raumID;
        Name = name;
    }

    public Raumreservierung(int reservierungID, Timestamp datum, int raumID, String name) {
        ReservierungID = reservierungID;
        Datum = datum;
        RaumID = raumID;
        Name = name;
    }

    public int getReservierungID() {
        return ReservierungID;
    }

    public void setReservierungID(int reservierungID) {
        ReservierungID = reservierungID;
    }

    public Timestamp getDatum() {
        return Datum;
    }

    public void setDatum(Timestamp datum) {
        Datum = datum;
    }

    public int getRaumID() {
        return RaumID;
    }

    public void setRaumID(int raumID) {
        RaumID = raumID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
