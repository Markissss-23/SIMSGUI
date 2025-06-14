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
    Although its only really checking for one case, being an empty input
    this is becuase I implemented the grade as drop down box with preset choices
*/
public class GradeValidator extends Validator<String>{    
    @Override
    public boolean validate(String grade) {
        return switch (grade) {
            case "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D" -> true;
            default -> false;
        };
    }
}
