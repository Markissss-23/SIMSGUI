/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
public class AdminController {
    MainFrame mainFrame;
    UserDAO userDAO = new UserDAO();
    StudentDAO studentDAO = new StudentDAO();
    IdValidator idValidator = new IdValidator(studentDAO);
    
    public AdminController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    
    public void deleteUser(String username) {
        if (!username.equals("admin")) {
            userDAO.deleteUser(username);
            new MessageDialogue(mainFrame.getParentFrame(), "User Deleted", "Success", 1);
        } else {
            new MessageDialogue(mainFrame.getParentFrame(), "Admin cannot be deleted", "Error", 0);
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
}
