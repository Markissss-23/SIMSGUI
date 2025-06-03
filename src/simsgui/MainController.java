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
public class MainController {

    MainFrame mainFrame;
    UserDAO userDAO = new UserDAO();
    DBHandler dbHandler = DBHandler.getInstance();
    
    public MainController(JFrame parent) {
        mainFrame = new MainFrame();
        mainFrame.setSize(1000,700);
        mainFrame.setVisible(true);
        showLogin();
    }
    
    public void showLogin() {
        mainFrame.switchPanel(new LoginPanel());
    }
    
    public void showRegistration() {
        mainFrame.switchPanel(new RegistrationPanel());
    }
    
    public void showMainMenu(UserInfo user) {
        mainFrame.switchPanel(new MainMenuPanel(user));
    }
    
    public void showStudentPanel() {
        mainFrame.switchPanel(new StudentPanel());
    }
    
    public void showAdminPanel() {
        mainFrame.switchPanel(new AdminPanel());
    }
    
    public void exitApp() {
        dbHandler.closeConnection();
        System.exit(0);
    }
}
