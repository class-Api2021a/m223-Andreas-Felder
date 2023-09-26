package com.ubs;

import com.ubs.data.interfaces.ISQLOperator;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {

    @Inject
    @Named("raumreservierungDAO")
    private ISQLOperator raumreservierungDAO;

    public void run() {
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        Main application = container.select(Main.class).get();
        application.run();
        weld.shutdown();
    }
}
