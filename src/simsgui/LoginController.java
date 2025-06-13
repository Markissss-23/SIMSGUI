/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
public class LoginController {

    private MainController mainController;
    private UserValidator userValidator;
    private UserDAO userDAO;

    public LoginController(MainController mainController) {
        this.mainController = mainController;
        this.userDAO = mainController.getUserDAO();
        this.userValidator = new UserValidator(userDAO);
        
    }

    public void handleLogin(String username, String password) {

        if (userValidator.validateLogin(username, password)) {
            UserInfo user = userDAO.getUserByUsername(username);
            mainController.showMainMenu(user, mainController);
        } else {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Invalid username or password.", "Login Failed.", 0);
        }
    }
}
