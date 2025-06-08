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
            String studentTable = "CREATE TABLE Students ("
                    + "id INT PRIMARY KEY,"
                    + "name VARCHAR(25),"
                    + "degree VARCHAR(25),"
                    + "grade VARCHAR(2))";
            stmt.executeUpdate(studentTable);
            System.out.println("Students Table created successfully");
            
            
            String userTable = "CREATE TABLE Users ("
                    + "username VARCHAR(25) PRIMARY KEY,"
                    + "password VARCHAR(25),"
                    + "level VARCHAR(10))";
            stmt.executeUpdate(userTable);
            System.out.println("Users Table created sucessfully");
            
            // Creates default admin if none exists 
            UserDAO userDAO = new UserDAO();
            if (!userDAO.userExists("admin")) {
                userDAO.addUser(new UserInfo ("admin", "admin123", "admin"));
            }
        } catch (SQLException e) {
            if (!e.getMessage().contains("already exists")) {
                System.err.println("Table creation failed: " + e.getMessage());
            }
        }
    }

}
