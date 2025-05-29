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

    private final UserDAO userDAO = new UserDAO();
    private MainController mainControl;

    public LoginController(MainController mainControl) {
        this.mainControl = mainControl;
    }

    public UserInfo authentication(String username, String password) {
        UserInfo user = userDAO.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } 
        return null;
    }

    public void handleLogin(String username, String password) {
        UserInfo user = authentication(username, password);
        if (user!= null) {
            mainControl.showMainMenu(user);
        } else {
            // send error message
        }
    }


}
