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
    private MainController mainController;
    private UserValidator userValidator;
    
    public RegistrationController(MainController mainController) {
        this.mainController = mainController;
        this.userValidator = new UserValidator(mainController.getUserDAO());
    }
    
    public void handleRegistration(String username, String password, String confirmPassword) {
        if (userValidator.validateRegistration(username, password, confirmPassword)) {
            UserInfo newUser = new UserInfo(username, password, "user");
            
            mainController.getUserDAO().addUser(newUser);
           
            new MessageDialogue(mainController.getMainFrame().getParentFrame(), "Registration successful", "Success", 1);
            
            mainController.showLogin();
        } else {
            new MessageDialogue(
                    mainController.getMainFrame().getParentFrame(), 
                    userValidator.getRegistrationErrors(username, password, confirmPassword), 
                    "Registration failed", 
                    0);
        }
    }
}
