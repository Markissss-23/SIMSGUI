/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBHandler {

    //private static final String USER_NAME = "simsguiDB"; //your DB username
    //private static final String PASSWORD = "simsguiDB"; //your DB password
    private static final String URL = "jdbc:derby:simsguiDB;create=true";  //url of the DB host
    private static DBHandler instance;
    private Connection conn;

    private DBHandler() {
        establishConnection();
        createTablesIfNonExistent();
    }

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Derby driver not found!");
        }
    }
    

    public static DBHandler getInstance() { // Singleton
        if (instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }

    public Connection getConnection() {
        if (conn == null) {
            establishConnection();
        }
        return this.conn;
    }

    public void establishConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL);
                System.out.println("Connected to " + URL + " successfully...");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } 
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void createTablesIfNonExistent() {
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE Students ("
                    + "id INT PRIMARY KEY,"
                    + "name VARCHAR(25),"
                    + "degree VARCHAR(25),"
                    + "grade VARCHAR(2))";
            stmt.execute(sql);
            System.out.println("Students Table created successfully");
        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists")) {
                System.err.println("Table creation failed: " + e.getMessage());
            }
        }
    }

}
