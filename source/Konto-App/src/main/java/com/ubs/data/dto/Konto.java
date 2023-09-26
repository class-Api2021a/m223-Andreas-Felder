package com.ubs.data.dto;



import java.sql.Date;

public class Konto {
    private double Kontonummer;
    private double Kontostand;
    private Date Eröffnungsdatum;

    public double getKontonummer() {
        return Kontonummer;
    }

    public void setKontonummer(double kontonummer) {
        Kontonummer = kontonummer;
    }

    public double getKontostand() {
        return Kontostand;
    }

    public void setKontostand(double kontostand) {
        Kontostand = kontostand;
    }

    public Date getEröffnungsdatum() {
        return Eröffnungsdatum;
    }

    public void setEröffnungsdatum(Date eröffnungsdatum) {
        Eröffnungsdatum = eröffnungsdatum;
    }
}
