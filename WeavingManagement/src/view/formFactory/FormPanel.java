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
    private List<TextField> text = new ArrayList<>();
    private List<List<TextField>> clientText = new ArrayList<>();
    private List<Label> label = new ArrayList<>();
    private JButton addRef;
    private JButton addPhone;
    private GridBagConstraints gbc = new GridBagConstraints();
    String type = new String();
    
    void createFormPanel(List<Label> label, String type){
        this.label = label;
        this.addRef = new JButton("add");
        this.addPhone = new JButton("add");
        this.type = type;
        
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        this.save = new JButton("save");
        
        
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        
        for(int i = 0; i < this.label.size(); i++){
            add(label.get(i), this.gbc);
            this.gbc.gridy++;
        }
        
        if(!type.equals("client")){
            for(int i=0; i < this.label.size(); i++){
                this.text.add(new TextField());
            }
            
            this.gbc.gridx = 1;
            this.gbc.gridy = 0;
        
            for(int k = 0; k < this.text.size(); k++){
                add(this.text.get(k), this.gbc);
                this.gbc.gridy++;
            }
        
        }else{
            for(int i=0; i < this.label.size(); i++){
                List<TextField> list = new ArrayList<>();
                list.add(new TextField());
                this.clientText.add(list);
            }
            
            this.gbc.gridx = 1;
            this.gbc.gridy = 0;
            
            for(int k = 0; k < this.clientText.size(); k++){
                for(int j = 0; j < this.clientText.get(k).size(); j++){
                    add(this.clientText.get(k).get(j), this.gbc);
                    if(k == 1){
                        this.gbc.gridx++;
                        add(this.addRef, this.gbc);
                        this.gbc.gridx--;
                    }
                    if(k == 2){
                        this.gbc.gridx++;
                        add(this.addPhone, this.gbc);
                        this.gbc.gridx--;
                    }
                    this.gbc.gridy++;
                }
            }
        }
        
        
        add(this.save, this.gbc);
        
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
    
    public List<List<String>> getData(String type){
        List<List<String>> data = new ArrayList();
        for(int i=0; i < this.clientText.size(); i++){
            data.add(new ArrayList<>());
            for(int k =0; k < this.clientText.get(i).size(); k++){
                data.get(i).add(this.clientText.get(i).get(k).getText());
            }
        }
        return data;
    }
    
    public JPanel getPanel(){
        return this;
    }
    
    public List<TextField> getText(){
        return this.text;
    }
    
    public List<Label> getLabel(){
        return this.label;
    }
    
    public JButton getAddRef(){
        return this.addRef;
    }
    
    public JButton getAddPhone(){
        return this.addPhone;
    }
    
    public void updateField(String type){
        removeAll();
        if(type.equals("referents")){
            this.clientText.get(1).add(new TextField());
        }
        if(type.equals("phone")){
           this.clientText.get(2).add(new TextField());
        }
       
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
            
            for(int k = 0; k < this.clientText.size(); k++){
                
                for(int j = 0; j < this.clientText.get(k).size(); j++){
                    if(k==0 && j == 0){
                        this.gbc.gridx--;
                        add(this.label.get(k), this.gbc);
                        this.gbc.gridx++;
                    }
                    if(k==1 && j == 0){
                        this.gbc.gridx--;
                        add(this.label.get(k), this.gbc);
                        this.gbc.gridx = 2;
                        add(this.addRef, this.gbc);
                        this.gbc.gridx--;
                    }
                    if(k==2 && j == 0){
                        this.gbc.gridx--;
                        add(this.label.get(k), this.gbc);
                        this.gbc.gridx = 2;
                        add(this.addPhone, this.gbc);
                        this.gbc.gridx--;
                    }
                    
                    add(this.clientText.get(k).get(j), this.gbc);
                    
                    this.gbc.gridy++;
                }
            }
            add(this.save, this.gbc);
            revalidate();
            repaint();
    }
}
