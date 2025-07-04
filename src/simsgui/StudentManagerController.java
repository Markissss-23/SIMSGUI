/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.util.*;

/**
 *
 * @author marku
 */
public class StudentManagerController {

    private MainController mainController;
    private StudentDAO studentDAO;
    private IdValidator idValidator;
    private StudentValidator studentValidator;

    public StudentManagerController(MainController mainController) {
        this.mainController = mainController;
        this.studentDAO = mainController.getStudentDAO();
        this.idValidator = new IdValidator(studentDAO);
        this.studentValidator = new StudentValidator(studentDAO);
    }

    public void addStudent(int id, String name, String degree, String grade) {
        // AI optimisation
        studentValidator.setUpdateMode(false);

        // Creates new student with information provided
        StudentInfo student = new StudentInfo(id, name, degree, grade);

        // Checks if the student information is valid
        if (studentValidator.validate(student)) {

            studentDAO.addStudent(student);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Student added", "Success", 1);
        } else {
            String errors = studentValidator.getValidationErrors(student);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), errors, "Failed", 0);
        }
    }

    public void updateStudent(int id, String name, String degree, String grade) {
        studentValidator.setUpdateMode(true); // update mode

        // Creates student information of the student to be updated
        StudentInfo student = new StudentInfo(id, name, degree, grade);

        if (studentValidator.validate(student)) {
            studentDAO.updateStudent(student);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Student updated", "Success", 1);
        } else {
            String errors = studentValidator.getValidationErrors(student);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), errors, "Failed", 0);
        }

        studentValidator.setUpdateMode(false); // resets mode to add

    }

    public void deleteStudent(int id) {
        if (!idValidator.validate(id)) {
            studentDAO.deleteStudent(id);
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Student deleted", "Success", 1);
        } else {
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Invalid or non-existent ID", "Failed", 0);
        }
    }

    public List<StudentInfo> searchStudents(String query) {
        return studentDAO.searchStudent(query);
    }

    public List<StudentInfo> getStudents() {
        return studentDAO.getStudents();
    }

}
