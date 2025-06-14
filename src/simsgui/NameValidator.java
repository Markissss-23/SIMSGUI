/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */

/*
    Taken from assignment 1
    Only one part is genned with AI as an improvement on the design
    Making it so that the name cannot contain characters that arent letters or spaces
*/

public class NameValidator extends Validator<String>{
    @Override
    public boolean validate(String name) {
        if (name == null) {
            System.err.println("Name cannot be null.");
            return false;
        }
        if (name.isEmpty()) {
            System.err.println("Name cannot be empty.");
            return false;
        }
        
        // Genned with AI 
        if (!name.matches("^[a-zA-Z ]+$")) {
            System.err.println("Name can only contain letters and spaces");
            return false;
        }
        
        return true;
    }
}
