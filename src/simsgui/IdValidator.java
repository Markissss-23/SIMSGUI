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
    // AI Optimisation
    private boolean updateMode = false; // false = add mode (default), true = update mode

    public IdValidator(StudentDAO dao) {
        this.dao = dao;
    }

    public void setUpdateMode(boolean isUpdate) {
        this.updateMode = isUpdate;
    }

    @Override
    public boolean validate(Integer id) {
        if (id == null) {
            return false;
        }
        if (id < 10000000 || id > 99999999) { // Must be exactly 8 digits
            return false;
        }
        
        if (updateMode) {
            // ID must exist in DB when updating
            return dao.IdExists(id);
        } else {
            // ID must NOT exist in DB when adding
            return !dao.IdExists(id);
        }
    }
}
