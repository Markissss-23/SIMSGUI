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

// 
public class MessageDialogue extends JDialog {
    
    public MessageDialogue(JFrame parent, String message, String title, int messageType) {
        setLayout(new BorderLayout());
        createMessage(message, messageType);
    }
    
    private void createMessage(String message, int messageType) {
        // <HTML> wraps text
        JLabel messageLabel = new JLabel("<html>" + message + "</html>");
        
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        add(messageLabel, BorderLayout.CENTER);
        
        JButton button = new JButton("OK");
        button.addActionListener(e -> dispose());
        add(button, BorderLayout.SOUTH);
               
        switch (messageType) {
            case 0 -> // Error type
                messageLabel.setForeground(Color.red);
            case 1 -> // Successful type
                messageLabel.setForeground(Color.green);
            case 2 -> // Warning type
                messageLabel.setForeground(Color.orange);
        }
        
        setSize(500, 200);
        setResizable(false);
        setVisible(true);
    }
}
