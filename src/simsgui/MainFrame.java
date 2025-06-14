/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author marku
 */

public class MainFrame extends JFrame {
    JPanel currentPanel;
    JFrame parentFrame;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLayout(new BorderLayout());
    }
    
    public void switchPanel(JPanel panel) {
        currentPanel = panel;
        // Removes current content
        getContentPane().removeAll();
        
        add(currentPanel, BorderLayout.CENTER);
        
        
        revalidate();
        repaint();
    }
    
    public JFrame getParentFrame() {
        return parentFrame;
    }
    
    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }
    
}
