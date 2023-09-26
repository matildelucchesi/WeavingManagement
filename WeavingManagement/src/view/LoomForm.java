/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Matilde
 */
public class LoomForm extends JPanel {
    private JButton save = new JButton();
    
    public LoomForm(){
        this.initialize();
    }
    
    public void initialize(){
        setBackground(Color.WHITE);
        
        JLabel number = new JLabel("loom number: ");
        JTextField tNumber = new JTextField();
        JLabel itemCode = new JLabel("item code: ");
        JTextField tItemCode = new JTextField();
        JLabel speed = new JLabel("loom speed: ");
        JTextField tSpeed = new JTextField();
        JLabel surrender = new JLabel("loom surrender: ");
        JTextField tSurrender = new JTextField();
        JLabel meters = new JLabel("meters to go: ");
        JTextField tMeters = new JTextField();
        
        this.save.setText("save");
        
        setLayout(new GridLayout(0, 2));
        
        add(number);
        add(tNumber);
        add(itemCode);
        add(tItemCode);
        add(speed);
        add(tSpeed);
        add(surrender);
        add(tSurrender);
        add(meters);
        add(tMeters);
        add(new JPanel());
        add(new JPanel());
        add(this.save);
        
    }
    
    public JPanel getPanel(){
        return this;
    }
    
    public JButton getButton(){
        return this.save;
    }
}
