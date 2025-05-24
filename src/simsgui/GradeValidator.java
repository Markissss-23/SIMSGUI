/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
public class GradeValidator extends Validator<String>{
    
    @Override
    public boolean validate(String grade) {
        // Taken from assignment 1
        return switch (grade) {
            case "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D" -> true;
            default -> false;
        };
    }
    
}
