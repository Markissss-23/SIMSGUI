/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marku
 */
public class StudentPanel extends JPanel {

    private MainController mainController;
    private StudentManagerController studentManagerController;

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, degreeField;
    private JComboBox<String> gradeComboBox;

    public StudentPanel(MainController mainController) {
        this.mainController = mainController;
        this.studentManagerController = mainController.getStudentManagerController();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        initUI();
        populateTable(studentManagerController.getStudents());
    }

    private void initUI() {
        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        // Search Functionality
        searchButton.addActionListener((ActionEvent e) -> {
            List<StudentInfo> results = studentManagerController.searchStudents(searchField.getText().trim());
            populateTable(results);
        });

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Degree", "Grade"}, 0);
        studentTable = new JTable(tableModel);
        // Sets studentTable to be scrollable
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // student Table functionality
        // Idea proposed by AI and general skeleton provided
        // However I did the functionality and aesthetic implementation
        studentTable.getSelectionModel().addListSelectionListener(e -> {

            if (e.getValueIsAdjusting()) {
                return;
            }

            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }

            // retrieves student data from row that is selected
            String id = studentTable.getValueAt(selectedRow, 0).toString();
            String name = studentTable.getValueAt(selectedRow, 1).toString();
            String degree = studentTable.getValueAt(selectedRow, 2).toString();
            String grade = studentTable.getValueAt(selectedRow, 3).toString();

            // sets fields to the retrieved data
            idField.setText(id);
            nameField.setText(name);
            degreeField.setText(degree);
            gradeComboBox.setSelectedItem(grade);

            // prevent ID modification
            // this is because the ID is the pk of the student table
            idField.setEditable(false);

        });

        // Form
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        idField = new JTextField();
        nameField = new JTextField();
        degreeField = new JTextField();
        // Presets Student Grades, which in hindsight makes GradeValidator redundant
        gradeComboBox = new JComboBox<>(new String[]{"","A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D"});

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Degree:"));
        formPanel.add(degreeField);

        formPanel.add(new JLabel("Grade:"));
        formPanel.add(gradeComboBox);

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
        try {
            // Retrieves Student data from input fields
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String degree = degreeField.getText().trim();
            String grade = gradeComboBox.getSelectedItem().toString();

            studentManagerController.addStudent(id, name, degree, grade);

            // Repopulate table with new student list
            populateTable(studentManagerController.getStudents());

            // Clears input fields
            clearFields();
        } catch (NumberFormatException ex) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "ID must be an integer.", "Input Error", 0);
        }
    }

    private void handleUpdate() {

        // Checks if row is selected
        if (studentTable.getSelectedRow() == -1) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Select a student to update.", "No Selection", 0);
            return;
        }

        try {
            // Retrieves new student data from input fields
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String degree = degreeField.getText().trim();
            String grade = gradeComboBox.getSelectedItem().toString();

            studentManagerController.updateStudent(id, name, degree, grade);

            // Repopulate table with updated student list
            populateTable(studentManagerController.getStudents());

            // Clears inout fields
            clearFields();
        } catch (NumberFormatException ex) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "ID must be an integer.", "Input Error", 0);
        }
    }

    private void handleDelete() {

        // Checks if Row is selected
        if (studentTable.getSelectedRow() == -1) {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Select a student to delete.", "No Selection", 0);
            return;
        }

        // Confirms if user wants to delete
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Retrieves student ID from selected row
        int id = Integer.parseInt(studentTable.getValueAt(studentTable.getSelectedRow(), 0).toString());

        studentManagerController.deleteStudent(id);

        // Repopulate table with updated student list
        populateTable(studentManagerController.getStudents());

        // Clears inout fields
        clearFields();
    }

    private void clearFields() {
        
        // Sets all fields to be empty
        idField.setText("");
        nameField.setText("");
        degreeField.setText("");
        gradeComboBox.setSelectedIndex(0);
        idField.setEditable(true);
        studentTable.clearSelection();
    }

    private void populateTable(List<StudentInfo> students) {
        tableModel.setRowCount(0);
        // adds rows filled with student data
        for (StudentInfo s : students) {
            tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getDegree(), s.getGrade()});
        }
    }
}
