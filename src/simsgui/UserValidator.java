/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */

/* This class is mostly AI genned, with some methods tweaked to fit the current project*/
public class UserValidator {
    private UserDAO userDAO;

    // Constructor with dependency injection
    public UserValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Validate registration details (username, password, confirmPassword)
    public boolean validateRegistration(String username, String password, String confirmPassword) {
        return (
            isValidUsername(username) &&
            isValidPassword(password) &&
            password.equals(confirmPassword) &&
            !userDAO.userExists(username) // Username must be unique
        );
    }

    // Validate login details (username, password)
    public boolean validateLogin(String username, String password) {
        UserInfo user = userDAO.getUserByUsername(username);
        return (
            user != null && 
            user.getPassword().equals(password) 
        );
    }

    // Check username format (5-25 characters, letters/numbers)
    private boolean isValidUsername(String username) {
        return username != null && 
               username.length() >= 5 && 
               username.length() <= 25 && 
               username.matches("^[a-zA-Z0-9_]+$"); // Only letters, numbers, underscores
    }

    // Check password format (8+ characters, at least 1 letter and 1 number)
    private boolean isValidPassword(String password) {
        return password != null && 
               password.length() >= 8 && 
               password.matches("^(?=.*[A-Za-z])(?=.*\\d).+$");
    }

    // Get error messages for registration
    public String getRegistrationErrors(String username, String password, String confirmPassword) {
        StringBuilder errors = new StringBuilder();

        if (!isValidUsername(username)) {
            errors.append("Username must be 5-25 characters (letters, numbers, underscores).\n");
        }
        if (!isValidPassword(password)) {
            errors.append("Password must be at least 8 characters with letters and numbers.\n");
        }
        if (!password.equals(confirmPassword)) {
            errors.append("Passwords do not match.\n");
        }
        if (userDAO.userExists(username)) {
            errors.append("Username already exists.\n");
        }

        return errors.toString().trim();
    }
    
    
}
