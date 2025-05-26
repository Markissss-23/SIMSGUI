/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

/**
 *
 * @author marku
 */
import simsgui.StudentDAO;

public class MockStudentDAO extends StudentDAO {
    private boolean exists = false;
    
    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    
    public boolean IDExists(int id) {
        return exists;
    }
    
}
