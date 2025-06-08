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
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        formPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 20));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 20));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Confirm Password:"));
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(200, 20));
        formPanel.add(confirmPasswordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener((ActionEvent e) -> {
            mainController.getRegistrationController().handleRegistration(
                    usernameField.getText(),
                    new String(passwordField.getPassword()),
                    new String(confirmPasswordField.getPassword()));
        });
        buttonPanel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener((e -> mainController.showLogin()));
        buttonPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}
