/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Matilde
 */
public class RightPanel extends JPanel{
    
    public RightPanel(JFrame frame){
        setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));
        setBackground(Color.BLUE);
        
    }
}
