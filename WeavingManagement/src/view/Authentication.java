/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Matilde
 */
public class Authentication extends JPanel {
    private List<Label> label = new ArrayList<>();
    private List<JTextField> text = new ArrayList<>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton button = new JButton("log in");
    
    public Authentication(){
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        
        this.label.add(new Label("username:", 150));
        this.label.add(new Label("password:", 150));
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        
        for(int i = 0; i < this.label.size(); i++){
            add(this.label.get(i), this.gbc);
            this.gbc.gridx++;
            this.text.add(new JTextField());
            Dimension labelSize = new Dimension(150, 20);
            this.text.get(i).setPreferredSize(labelSize);
            add(this.text.get(i), this.gbc);
            this.gbc.gridx--;
            this.gbc.gridy++;
        }
        
        add(this.button, this.gbc);
    }
    
    public List<String> getData(){
        List<String> data = new ArrayList<>();
        for(int i = 0; i < this.text.size(); i++){
            data.add(this.text.get(i).getText());
        }
        
        return data;
    }
    
    public String getUsername(){
        return this.text.get(0).getText();
    }
    
     public String getPassword(){
        return this.text.get(1).getText();
    }
     
     public JButton getLogInButton(){
         return this.button;
     }
}
