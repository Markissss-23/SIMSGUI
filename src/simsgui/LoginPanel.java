/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author marku
 */
class LoginPanel extends JPanel {

    private MainController mainController;

    public LoginPanel(MainController mainController) {
        this.mainController = mainController;
        setLayout(new BorderLayout());
        setSize(200, 100);
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent e) -> {
            mainController.getLoginController().handleLogin(
                    usernameField.getText(),
                    new String(passwordField.getPassword()));
        });
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener((e -> mainController.showRegistration()));
        buttonPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

};
