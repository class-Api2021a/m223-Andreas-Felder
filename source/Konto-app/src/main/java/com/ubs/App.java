package com.ubs;
import com.ubs.data.dao.KundeDAO;
import com.ubs.data.dto.Kunde;

import com.ubs.data.dto.Raumreservierung;
import com.ubs.data.interfaces.ISQLOperator;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;
public class App {

    @Inject
    @Named("kundeDAO")
    private KundeDAO kundeDAO;

    @Inject
    @Named("raumreservierungDAO")
    private ISQLOperator raumreservierungDAO;

    public void run() {
        raumreservierungDAO.create(new Raumreservierung(new Timestamp(new Date().getTime()), 1, "Test"));
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //Weld weld = new Weld();
        //WeldContainer container = weld.initialize();
//
        //App application = container.select(App.class).get();
        //application.run();
        //weld.shutdown();
        Raumreservierung r = new Raumreservierung(new Timestamp(new Date().getTime()), 1, "Test");
        Raumreservierung r2 = new Raumreservierung(new Timestamp(1695478820000), 2, "Test2");
        //System.out.println(new LocalDate(r.getDatum().getTime()).equals(new java.sql.Date(r2.getDatum().getTime())));
        System.out.println(convertToLocalDateViaSqlDate(r.getDatum()));
        System.out.println(convertToLocalDateViaSqlDate(r2.getDatum()));
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

}
