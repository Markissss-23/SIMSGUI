/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.sql.*;
import java.util.*;

/**
 *
 * @author marku
 */

/* 
A lot of the logic and methods here are very similar to StudentDAO, but configured to a user context

 */
public class UserDAO {

    private final Connection conn;

    public UserDAO() {
        this.conn = DBHandler.getInstance().getConnection();
    }

    // Function to create a default admin user. Cannot be modified externally
    public void createDefaultAdmin() {
        String defaultUsername = "admin";
        String defaultPassword = "admin123";
        String defaultLevel = "admin";

        // Checks if admin already exists
        if (!userExists(defaultUsername)) {
            UserInfo admin = new UserInfo(defaultUsername, defaultPassword, defaultLevel);
            addUser(admin);
            System.out.println("Default admin created: " + defaultUsername);
        }
    }

    public void addUser(UserInfo user) {
        String sql = "INSERT INTO Users (username, password, level) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getLevel());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to add user:", ex);
        }
    }

    public UserInfo getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new UserInfo(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("level")
                );
            }

            return null; // just to return something
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to retrieve user", ex);
        }
    }

    public boolean userExists(String username) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            } else {
                return false; // shouldn't ever pass
            }
        } catch (SQLException ex) {
            throw new RuntimeException("failed to check user existence", ex);
        }
    }

    public List<UserInfo> getAllUsers() {
        List<UserInfo> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                users.add(new UserInfo(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("level")
                ));
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return users;
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM Users WHERE username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateUser(String username, String newPassword, String newLevel) {
        String sql = "UPDATE Users SET password = ?, level = ? WHERE username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, newLevel);
            pstmt.setString(3, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Mostly for testing
    public void deleteAllUsers() throws SQLException {
        String sql = "DELETE FROM Users";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
}
