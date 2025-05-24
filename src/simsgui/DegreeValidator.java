/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
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
        if (!degree.matches("^[a-zA-z]+$")) {
            System.err.println("Name can only contain letters and spaces");
            return false;
        }
        
        return true;
    }
}
