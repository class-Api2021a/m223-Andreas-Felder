package com.ubs.configuration;

import com.ubs.data.dao.KontoDAO;
import com.ubs.data.dao.KundeDAO;
import com.ubs.data.dao.RaumreservierungDAO;
import com.ubs.data.interfaces.ISQLOperator;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.SQLException;


public class  DAOBuilder {

    @Produces
    @Named("kontoDAO")
    public static KontoDAO produceKontoDAO() {
        return new KontoDAO(DatabaseProducer.produceConnection());
    }

    @Produces
    @Named("kundeDAO")
    public static KundeDAO produceKundeDAO() throws SQLException {
        Connection con = DatabaseProducer.produceConnection();
        con.setAutoCommit(false);
        return new KundeDAO(con);
    }


    @Produces
    @Named("raumreservierungDAO")
    public static ISQLOperator produceRaumreservierungDAO() throws SQLException {
        Connection con = DatabaseProducer.produceConnection();
        con.setAutoCommit(false);
        return new RaumreservierungDAO(con);
    }
}
