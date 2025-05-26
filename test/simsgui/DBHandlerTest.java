/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package simsgui;

import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marku
 */
public class DBHandlerTest {

    static DBHandler dbHandler;
    static StudentDAO studentDAO;

    public DBHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dbHandler = DBHandler.getInstance();
        studentDAO = new StudentDAO();
    }

    @After
    public void tearDown() {
        dbHandler.closeConnection();
        dbHandler = DBHandler.getInstance();
    }

    @Test
    public void testDBConnection() {
        assertNotNull(dbHandler.getConnection());
    }

    @Test
    public void testStudentsTable() {
        try {
            Statement stmt = dbHandler.getConnection().createStatement();
            stmt.executeQuery("SELECT * FROM Students");
        } catch (SQLException e) {
            fail("Students table doesn't exist");
        }
    }

    /*@Test
    public void testCloseConnection() {
        dbHandler.closeConnection();
        assertNull(dbHandler.getConnection());

        Connection newConn = dbHandler.getConnection();
        assertNotNull(newConn);
    }*/

    @Test
    // AI GENERATED
    public void testCloseConnection() {
        // Get the original connection
        Connection original = dbHandler.getConnection();

        // Close the connection
        dbHandler.closeConnection();

        // Verify the original connection is closed
        try {
            original.createStatement(); // Should throw SQLException
            fail("Closed connection should not be usable!");
        } catch (SQLException e) {
            // Expected exception
        }

        // Reopen the connection and ensure it's a new instance
        Connection newConn = dbHandler.getConnection();
        assertNotNull(newConn);
        assertNotSame(original, newConn); // New connection after re-opening
    }

    @Test
    public void testConnectionReused() {
        DBHandler instance2 = DBHandler.getInstance();
        assertSame(dbHandler.getConnection(), instance2.getConnection());
    }

}
