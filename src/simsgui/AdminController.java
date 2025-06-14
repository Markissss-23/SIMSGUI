/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.util.*;

/**
 *
 * @author marku
 */

/*

Fundamentally I tried to make the structure and style to be the same as
StudentManagerController but due to the overall varying structure from
the generated StudentValidator class, It will be a bit different. 
The overall structure is overall identical, just with slightly 
different methodologies

 */
public class AdminController {

    private MainController mainController;
    private UserDAO userDAO;
    private UserValidator userValidator;

    public AdminController(MainController mainController) {
        this.mainController = mainController;
        this.userDAO = mainController.getUserDAO();
        this.userValidator = new UserValidator(userDAO);
    }

    public void addUser(String username, String password, String confirmPassword, String level) {
        // Validates user information with information provided
        if (userValidator.validateRegistration(username, password, confirmPassword)) {
            // Creates user with information provided
            UserInfo user = new UserInfo(username, password, level);

            userDAO.addUser(user);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "User added", "Success", 1);
        } else {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(),
                    userValidator.getRegistrationErrors(username, password, confirmPassword),
                    "Failed", 0);
        }
    }

    public void deleteUser(String username) {
        // Makes it so that the user cannot delete pregenerated admin
        // or themselves
        String currentUser = mainController.getCurrentUser().getUsername();

        UserInfo targetUser = userDAO.getUserByUsername(username);

        if (username.equals("admin") || username.equals(currentUser) || targetUser.getLevel().equalsIgnoreCase("admin")) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "You cannot delete admin users or yourself", "Error", 0);
            return;
        }

        userDAO.deleteUser(username);
        new MessageDialogue(mainController.getMainFrame().getParentFrame(), "User Deleted", "Success", 1);
    }

    public void updateUser(String username, String password, String confirmPassword, String level) {
        // Creates user information
        UserInfo targetUser = userDAO.getUserByUsername(username);

        // Takes the username of the active user
        String currentUser = mainController.getCurrentUser().getUsername();

        // Checks if the user tries to update their own level
        if (targetUser.getUsername().equals(currentUser) && !targetUser.getLevel().equals(level)) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(),
                    "You cannot change your own level.", "Access Denied", 0);
            return;
        }

        // Checks if the user tries to update the level of another admin
        if (targetUser.getLevel().equalsIgnoreCase("admin") && !level.equalsIgnoreCase("admin")) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(),
                    "You cannot change the level of another admin.", "Access Denied", 0);
            return;
        }

        // checks if the user information to be updated is valid
        if (userValidator.validateUpdate(username, password, confirmPassword, level)) {
            userDAO.updateUser(username, password, level);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "User updated", "Success", 1);
        } else {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(),
                    userValidator.getUpdateErrors(username, password, confirmPassword), "Failed", 0);
        }
    }

    public List<UserInfo> getAllUsers() {
        return userDAO.getAllUsers();
    }

}
