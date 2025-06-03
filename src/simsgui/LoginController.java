/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import javax.swing.*;

/**
 *
 * @author marku
 */
public class LoginController {

    UserValidator userValidator = new UserValidator(new UserDAO());
    private MainFrame mainFrame;

    public LoginController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.userValidator = new UserValidator(new UserDAO());
    }

    public void handleLogin(String username, String password) {
        if (userValidator.validateLogin(username, password)) {
            UserInfo user = new UserDAO().getUserByUsername(username);
            mainFrame.getMainController().showMainMenu(user);
        } else {
            new MessageDialogue(mainFrame.getParentFrame(), "Invalid Username or password.", "Login Failed.", 0);
        }
    }
}
