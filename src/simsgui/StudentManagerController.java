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
    MainFrame mainFrame;
    StudentDAO studentDAO = new StudentDAO();
    IdValidator idValidator = new IdValidator(studentDAO);
    StudentValidator studentValidator = new StudentValidator(studentDAO);
    
    
    public StudentManagerController(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    
    public void addStudent(StudentInfo student) {
        if (studentValidator.validate(student)) {
            studentDAO.addStudent(student);
            new MessageDialogue(mainFrame.getParentFrame(), "Student added", "Success", 1);
        } else {
            new MessageDialogue(mainFrame.getParentFrame(), studentValidator.getValidationErrors(student),"Failed", 0);
        }
    }
    
    public void updateStudent(StudentInfo student) {
        if (studentValidator.validate(student)) {
            studentDAO.updateStudent(student);
            new MessageDialogue(mainFrame.getParentFrame(), "Student updated", "Success", 1);
        } else {
            new MessageDialogue(mainFrame.getParentFrame(), studentValidator.getValidationErrors(student), "Failed", 0);
        }
    }
    
    public void deleteStudent(int id) {
        if (idValidator.validate(id)) {
            studentDAO.deleteStudent(id);
            new MessageDialogue(mainFrame.getParentFrame(), "Student deleted", "Success", 1);
        } else {
            new MessageDialogue(mainFrame.getParentFrame(), "Invalid or non-existent ID", "Failed", 0);
        }
    }
    
    public List<StudentInfo> searchStudents(String query) {
        return studentDAO.searchStudent(query);
    }
    
}
