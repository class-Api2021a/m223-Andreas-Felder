package com.ubs.data.dao;

import com.ubs.configuration.DAOBuilder;
import com.ubs.data.interfaces.ISQLOperator;
import junit.framework.TestCase;
import org.junit.BeforeClass;

import java.sql.SQLException;


public class RaumreservierungDAOTest extends TestCase {


    private ISQLOperator raumreservierungDAO;

    @BeforeClass
    public void setUp() {

        try {
            raumreservierungDAO = DAOBuilder.produceRaumreservierungDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void testCreate() {
        //raumreservierungDAO.create(new Raumreservierung(null, 1, "Test"));
    }

    public void testRead() {
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }

    public void testGetAll() {
    }
}