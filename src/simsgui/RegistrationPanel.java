/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author marku
 */
public class RegistrationPanel extends JPanel {

    private MainController mainController;

    public RegistrationPanel(MainController mainController) {
        this.mainController = mainController;
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        // Title message
        JLabel title = new JLabel("SIMS Registration");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        // Form Panel Layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Username
        JLabel usernameText = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        formPanel.add(usernameText);
        formPanel.add(usernameField);

        // Password
        JLabel passwordText = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        formPanel.add(passwordText);
        formPanel.add(passwordField);

        // Confirm Password
        JLabel confirmPasswordText = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);
        formPanel.add(confirmPasswordText);
        formPanel.add(confirmPasswordField);

        // Container to center the panel
        JPanel formContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formContainer.add(formPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener((ActionEvent e) -> {
            //System.out.println("Entered password: " + new String(passwordField.getPassword()));
            //System.out.println("Entered username: " + usernameField.getText());

            mainController.getRegistrationController().handleRegistration(
                    usernameField.getText(),
                    new String(passwordField.getPassword()),
                    new String(confirmPasswordField.getPassword()));
        });
        buttonPanel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener((e -> mainController.showLogin()));
        buttonPanel.add(backButton);

        add(formContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}
