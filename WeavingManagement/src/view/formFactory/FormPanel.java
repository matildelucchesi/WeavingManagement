/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.formFactory;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import view.Label;
import view.TextField;

/**
 *
 * @author Matilde
 */
public abstract class FormPanel extends JPanel{
    private JButton save;
    private List<TextField> text;
    
    void createFormPanel(List<Label> label, List<TextField> text){
        this.text = text;
        
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        this.save = new JButton("save");
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        for(int i = 0; i < label.size(); i++){
            add(label.get(i), gbc);
            gbc.gridy++;
        }
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        for(int k = 0; k < this.text.size(); k++){
            add(this.text.get(k), gbc);
            gbc.gridy++;
        }
        
        add(this.save, gbc);
    }
    
    public JButton getSaveButton(){
        return this.save;
    }
    
    public List<String> getData(){
        List<String> data = new ArrayList<>();
        for(int i=0; i < this.text.size(); i++){
            data.add(this.text.get(i).getText());
        }
        return data;
    }
}
