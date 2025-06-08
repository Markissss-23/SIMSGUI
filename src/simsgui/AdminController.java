/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
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

    public void deleteUser(String username) {
        if (!username.equals("admin")) {
            userDAO.deleteUser(username);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "User Deleted", "Success", 1);
        } else {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Admin cannot be deleted", "Error", 0);
        }
    }

    public void updateUser(String username, String newPassword, String confirmPassword, String newLevel) {

        if (UpdateCheck(username)) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "User cannot be updated", "Validation Failed", 0);
            return;
        }

        String errors = userValidator.getUpdateErrors(username, newPassword, confirmPassword);
        if (!errors.isEmpty()) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), errors, "Validation Failed", 0);
            return;
        }

        if (!newPassword.isEmpty()) {
            userDAO.updateUser(username, newPassword, newLevel);
        } else {
            userDAO.updateUser(username, null, newLevel);
        }

        new MessageDialogue(mainController.getMainFrame().getParentFrame(), "User Updated", "Success", 1);
    }

    private boolean UpdateCheck(String username) {
        if (username.equals("admin") || username.equals(username)) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Cannot update account", "Error", 0);
            return false;
        }
        return true;
    }
}
