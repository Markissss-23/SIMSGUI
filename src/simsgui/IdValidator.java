/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
public class IdValidator extends Validator<Integer> {

    private final StudentDAO studentDAO;

    public IdValidator(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public boolean validate(Integer id) {
        if (id < 10000000 && id > 99999999) {
            return true;
        }

        return !studentDAO.IdExists(id);

    }
}
