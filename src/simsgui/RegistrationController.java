/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
public class RegistrationController {
    MainFrame mainFrame; 
    UserValidator userValidator = new UserValidator(new UserDAO());
    
    public RegistrationController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    
    public void handleRegistration(String username, String password, String confirmPassword) {
        if (userValidator.validateRegistration(username, password, confirmPassword)) {
            UserInfo newUser = new UserInfo(username, password, "user");
            MessageDialogue messageDialogue = new MessageDialogue(mainFrame.getParentFrame(), "Registration successful", "Success", 1);
            mainFrame.getMainController().showLogin();
        } else {
            new MessageDialogue(
                    mainFrame.getParentFrame(), 
                    userValidator.getRegistrationErrors(username, password, confirmPassword), 
                    "Registration failed", 
                    0);
        }
    }
}
