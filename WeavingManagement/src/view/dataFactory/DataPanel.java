/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dataFactory;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import view.Label;

/**
 *
 * @author Matilde
 */
public abstract class DataPanel extends JPanel{
    private GridBagConstraints gbc = new GridBagConstraints();
    
    public void createDataPanel(List<Label> label, List<JTextArea> text){
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        
        for(int i = 0; i< label.size(); i++){
            add(label.get(i), this.gbc);
            this.gbc.gridx++;
            add(text.get(i), this.gbc);
            this.gbc.gridx--;
            this.gbc.gridy++;
        }
    }
    
    public void createDataPanelWithList(List<Label> label, List<List<JTextArea>> text){
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        
        for(int i = 0; i< label.size(); i++){
            add(label.get(i), this.gbc);
            this.gbc.gridx++;
            
            for(int k = 0; k < text.size(); k++){
                for(int j=0; j < text.get(i).size(); j++){
                    add(text.get(i).get(j), this.gbc);
                    this.gbc.gridy++;
                }
            }
            gbc.gridx--;
        }
    }
}
