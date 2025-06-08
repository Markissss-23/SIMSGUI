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

    private MainFrame mainFrame;
    private UserDAO userDAO;
    private StudentDAO studentDAO;
    private UserInfo currentUser;
    
    public MainController(JFrame parent) {
        this.mainFrame = new MainFrame();
        this.userDAO = new UserDAO();
        this.studentDAO = new StudentDAO();
        this.currentUser = null;
        
        mainFrame.setParentFrame(parent);
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
        this.currentUser = user;
        mainFrame.switchPanel(new MainMenuPanel(user));
    }
    
    public void showStudentPanel() {
        mainFrame.switchPanel(new StudentPanel());
    }
    
    public void showAdminPanel() {
        if (currentUser.getLevel().equals("admin")) {
            mainFrame.switchPanel(new AdminPanel(this));
        } else {
            new MessageDialogue(mainFrame.getParentFrame(), "Access Deniend", "Error", 0);
        }
    }
    
    public void exitApp() {
        DBHandler.getInstance().closeConnection();
        System.exit(0);
    }
    
    public UserInfo getCurrentUser() {
        return currentUser;
    }
    
    public UserDAO getUserDAO() {
        return userDAO;
    }
    
    public StudentDAO getStudentDAO() {
        return studentDAO;
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
