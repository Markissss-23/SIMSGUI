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
    MainController mainControl;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        mainControl = new MainController(this);
    }
    
    public void switchPanel(JPanel panel) {
        currentPanel = panel;
        getContentPane().removeAll();
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    public MainController getMainController() {
        return mainControl;
    }
    
    public JFrame getParentFrame() {
        return parentFrame;
    }
    
    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }
    
}
