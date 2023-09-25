package com.ubs;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Injector injector = Guice.createInjector(new AppModule());

        //YourService yourService = injector.getInstance(YourService.class);

        // Use yourService to interact with the MySQL database
    }
}
