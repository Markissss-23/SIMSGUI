/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import javax.swing.*;

/**
 *
 * @author marku
 */


/*
    RUN THIS CLASS OR THE PROJECT
*/
public class Main {
    public static void main(String[] args) {
        DBHandler.getInstance();
                
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = new JFrame("SIMS");
            parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parentFrame.setSize(1000,700);
            
            
            new MainController(parentFrame);
        });
    }
}
