/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */

// AI GENNED MOCK USER DAO
public class MockUserDAO extends UserDAO {
    private boolean exists = false;
    private UserInfo testUser;

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public void setTestUser(UserInfo user) {
        this.testUser = user;
    }

    @Override
    public boolean userExists(String username) {
        return exists; // Return predefined value
    }

    @Override
    public UserInfo getUserByUsername(String username) {
        return testUser; // Return predefined user
    }

    @Override
    public void addUser(UserInfo user) {
        // Do nothing (fake method)
    }

    @Override
    public void deleteAllUsers() {
        // Do nothing (fake method)
    }
}
