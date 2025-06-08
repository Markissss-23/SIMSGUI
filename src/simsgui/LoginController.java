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

    public LoginController(MainController mainController) {
        this.mainController = mainController;
        this.userValidator = new UserValidator(mainController.getUserDAO());
    }

    public void handleLogin(String username, String password) {

        if (userValidator.validateLogin(username, password)) {
            UserInfo user = mainController.getUserDAO().getUserByUsername(username);
            mainController.showMainMenu(user);
        } else {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Invalid username or password.", "Login Failed.", 0);
        }
    }
}
