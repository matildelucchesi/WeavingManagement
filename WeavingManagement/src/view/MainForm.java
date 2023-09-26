/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.util.List;
import model.*;

/**
 *
 * @author Matilde
 */
public class MainForm extends JFrame {
    private JButton button = new JButton("Button");
    private JPanel centralPanel;
    List<JButton> saveButton = new ArrayList<>();

    
    public MainForm(){
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        setMinimumSize(new Dimension(450, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel leftPanel = new LeftPanel(this);
        
        this.centralPanel = new JPanel();
        this.centralPanel.setLayout(new BoxLayout(this.centralPanel, BoxLayout.Y_AXIS));
        
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(getWidth() / 6, getHeight()));
        rightPanel.setBackground(Color.BLUE);
        
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(this.centralPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        add(mainPanel);
        setVisible(true);
    }  
    
    public JPanel getCentralPanel(){
        return this.centralPanel;
    }
    
   
       
}
 
