/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marku
 */
public class AdminPanel extends JPanel {

    private MainController mainController;
    private AdminController adminController;

    private JTable userTable;
    private DefaultTableModel tableModel;
    private JTextField usernameField, passwordField, confirmPasswordField;
    private JComboBox<String> levelComboBox;

    public AdminPanel(MainController mainController) {
        this.mainController = mainController;
        this.adminController = mainController.getAdminController();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        initUI();
        populateTable(adminController.getAllUsers());
    }

    private void initUI() {
        // Table
        tableModel = new DefaultTableModel(new Object[]{"Username", "Password", "Level"}, 0);
        userTable = new JTable(tableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Same as student panel. Except this was created entirely by 
        // infering details into the admin version
        userTable.getSelectionModel().addListSelectionListener(e -> {

            if (e.getValueIsAdjusting()) {
                return;
            }

            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }

            // retrieves user data from row that is selected
            String username = userTable.getValueAt(selectedRow, 0).toString();
            String password = userTable.getValueAt(selectedRow, 1).toString();
            String level = userTable.getValueAt(selectedRow, 2).toString();

            // sets fields to the retrieved data
            usernameField.setText(username);
            passwordField.setText(password);
            confirmPasswordField.setText(password);
            levelComboBox.setSelectedItem(level);

            // prevents username modification
            // this is because the username is the pk of the users table
            usernameField.setEditable(false);

        });

        // Form
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        usernameField = new JTextField();
        passwordField = new JTextField();
        confirmPasswordField = new JTextField();
        // Presets Levels, limiting it to only user and admin
        levelComboBox = new JComboBox<>(new String[]{"","user", "admin"});

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Confirm Password:"));
        formPanel.add(confirmPasswordField);

        formPanel.add(new JLabel("Level:"));
        formPanel.add(levelComboBox);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(e -> handleAdd());
        updateButton.addActionListener(e -> handleUpdate());
        deleteButton.addActionListener(e -> handleDelete());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> mainController.showMainMenu(mainController.getCurrentUser(), mainController));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleAdd() {
        // Retrieves user data from input fields

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        String level = levelComboBox.getSelectedItem().toString();

        adminController.addUser(username, password, confirmPassword, level);

        // Repopulate table with new user list
        populateTable(mainController.getUserDAO().getAllUsers());

        // Clears input fields
        clearFields();
    }

    private void handleUpdate() {

        // Checks if row is selected
        if (userTable.getSelectedRow() == -1) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Select a user to update.", "No Selection", 0);
            return;
        }

        // Retrieves new user data from input fields
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        String level = levelComboBox.getSelectedItem().toString();

        adminController.updateUser(username, password, confirmPassword, level);

        // Repopulate table with updated user list
        populateTable(mainController.getUserDAO().getAllUsers());

        // Clears input fields
        clearFields();
    }

    private void handleDelete() {

        // Checks if Row is selected
        if (userTable.getSelectedRow() == -1) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Select a user to delete.", "No Selection", 0);
            return;
        }

        // Confirms if user wants to delete
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Retrieves username from selected row
        String username = userTable.getValueAt(userTable.getSelectedRow(), 0).toString();
        
        adminController.deleteUser(username);
        
        // Repopulate table with updated user list
        populateTable(mainController.getUserDAO().getAllUsers());
        
        // Clears input fields
        clearFields();
    }

    private void clearFields() {
        
        // Sets all fields to be empty
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        levelComboBox.setSelectedIndex(0);
        usernameField.setEditable(true);
        userTable.clearSelection();
    }

    private void populateTable(List<UserInfo> users) {
        tableModel.setRowCount(0);
        // adds rows filled with user data
        for (UserInfo user : users) {
            tableModel.addRow(new Object[]{user.getUsername(), user.getPassword(), user.getLevel()});
        }
    }
}
