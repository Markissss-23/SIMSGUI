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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DBHandler {

    private static final String USER_NAME = "simsguiDB"; //your DB username
    private static final String PASSWORD = "simsguiDB"; //your DB password
    private static final String URL = "jdbc:derby:simsguiDB;create=true";  //url of the DB host
    private static DBHandler instance;
    private Connection conn;

    private DBHandler() {
        establishConnection();
        createTablesIfNonExistent();
    }
    
    public static void main(String[] args) {
        DBHandler dbManager = new DBHandler();
        System.out.println(dbManager.getConnection());
    }

    public static DBHandler getInstance() { // Singleton
        if (instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void establishConnection() {
        if (conn == null) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");

                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("Connected to " + URL + " successfully...");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
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
            System.err.println(e.getMessage());
        }
    }
    // MOVE TO StudentDAO
    public List<StudentInfo> queryStudents() {
        List<StudentInfo> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new StudentInfo(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("degree"),
                        rs.getString("grade")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return students;
    }
}
