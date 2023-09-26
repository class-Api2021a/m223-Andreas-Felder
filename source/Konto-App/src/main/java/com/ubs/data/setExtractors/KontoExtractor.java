package com.ubs.data.setExtractors;

import com.ubs.data.dto.Konto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class KontoExtractor {

     protected Optional<Konto> extract(ResultSet rs) throws SQLException {
        if(rs.next()){
            Konto konto = new Konto();
            konto.setKontonummer(rs.getDouble("kontonummer"));
            konto.setKontostand(rs.getDouble("kontostand"));
            konto.setEröffnungsdatum(rs.getDate("eroeffnungsdatum"));
            return Optional.of(konto);
        }else{
            return Optional.empty();
        }
    }
}
