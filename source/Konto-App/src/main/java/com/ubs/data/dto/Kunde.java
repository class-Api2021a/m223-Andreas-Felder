package com.ubs.data.dto;

import java.util.Date;
import java.util.List;
import java.util.function.LongToDoubleFunction;

public class Kunde {
    private String Kontaktdaten; // email addresse
    private String KundenNamen;
    private Date Geburtsdatum;
    private List<Konto> Konten;

    public String getKontaktdaten() {
        return Kontaktdaten;
    }

    public void setKontaktdaten(String kontaktdaten) {
        Kontaktdaten = kontaktdaten;
    }

    public String getKundenNamen() {
        return KundenNamen;
    }

    public void setKundenNamen(String kundenNamen) {
        KundenNamen = kundenNamen;
    }

    public Date getGeburtsdatum() {
        return Geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        Geburtsdatum = geburtsdatum;
    }

    public List<Konto> getKonten() {
        return Konten;
    }

    public void setKonten(List<Konto> konten) {
        Konten = konten;
    }
}
