/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author marku
 */
public class MainMenuPanel extends JPanel {

    private UserInfo currentUser;
    private MainController mainController;

    public MainMenuPanel(UserInfo user, MainController mainController) {
        this.currentUser = user;
        this.mainController = mainController;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        initUI();
    }

    private void initUI() {

        // Welcome Text
        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getUsername());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        // Button Layout
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 25)); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));


        // Manage Students Button
        JButton manageStudents = new JButton("Manage Students");
        manageStudents.setMaximumSize(new Dimension(250, 75));
        manageStudents.addActionListener(e -> mainController.showStudentPanel());
        buttonPanel.add(manageStudents);

        // Checks if User is an admin
        if (currentUser.getLevel().equals("admin")) {
            JButton adminButton = new JButton("Admin Features");
            adminButton.setMaximumSize(new Dimension(250, 75));
            adminButton.addActionListener(e -> mainController.showAdminPanel());
            buttonPanel.add(adminButton);
        }

        // Logout Button
        JButton logout = new JButton("Logout");
        logout.setMaximumSize(new Dimension(250, 75));
        logout.addActionListener(e -> mainController.showLogin());
        buttonPanel.add(logout);

        add(buttonPanel, BorderLayout.CENTER);

    }

}
