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
    Slightly different as this time its taking a StudentDAO input, 
    entirely because it checks if the id exists within the database    
*/

public class IdValidator extends Validator<Integer> {

    private final StudentDAO dao;

    public IdValidator(StudentDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean validate(Integer id) {
        if (id == null) return false; 
        if (id < 10000000 || id > 99999999) { // Must be exactly 8 digits
            return false;
        }
        return !dao.IdExists(id); // Check uniqueness
    }
}
