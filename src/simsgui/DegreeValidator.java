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
    Making it so that the degree cannot contain characters that arent letters or spaces
*/
public class DegreeValidator extends Validator<String>{
    @Override
    public boolean validate(String degree) {
        if (degree == null) {
            System.err.println("Degree cannot be null.");
            return false;
        }
        if (degree.isEmpty()) {
            System.err.println("Degree cannot be empty.");
            return false;
        }
        
        // Genned with AI 
        if (!degree.matches("^[a-zA-Z ]+$")) {
            System.err.println("Degree can only contain letters and spaces.");
            return false;
        }
        
        return true;
    }
}
