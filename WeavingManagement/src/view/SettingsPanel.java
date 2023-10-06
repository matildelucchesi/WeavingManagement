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
public class SettingsPanel extends JPanel{
    private JButton changePassword = new JButton();
    private JButton addUser = new JButton();
    private JButton save1 = new JButton("save");
    private JButton save2 = new JButton("save");
    private JButton back = new JButton("back");
    private GridBagConstraints gbc = new GridBagConstraints();
    private List<JTextField> password = new ArrayList<>();
     private List<JTextField> credentials = new ArrayList<>();
    
    public SettingsPanel(){
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        
        this.changePassword = ButtonUtility.createButton("Change password", "././icon/settings.png");
        this.addUser = ButtonUtility.createButton("Add new user", "././icon/settings.png");
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        add(this.changePassword, this.gbc);
        this.gbc.gridy++;
        add(this.addUser, this.gbc);
    }
    
    public JButton getChangePasswordButton(){
        return this.changePassword;
    }
    
    public JButton getAddUserButton(){
        return this.addUser;
    }
    
    public JButton getSave1Button(){
        return this.save1;
    }
    
    public JButton getSave2Button(){
        return this.save2;
    }
    
    public JButton getBackButton(){
        return this.back;
    }
    
    public void changePassword(){
        removeAll();
        List<Label> label = new ArrayList<>();
        label.add(new Label("old password:", 150));
        label.add(new Label("new password:", 150));
        
        for(int i=0; i < label.size(); i++){
            this.password.add(new JTextField());
            Dimension labelSize = new Dimension(150, 20);
            this.password.get(i).setPreferredSize(labelSize);
        }
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        for(int i = 0; i < label.size(); i++){
            add(label.get(i), this.gbc);
            this.gbc.gridx++;
            add(this.password.get(i), this.gbc);
            this.gbc.gridx--;
            this.gbc.gridy++;
        }
        
        add(this.save1, this.gbc);
        this.gbc.gridx++;
        add(this.back, this.gbc);
        revalidate();
        repaint();
    }
    
    public void addUser(){
        removeAll();
        List<Label> label = new ArrayList<>();
        label.add(new Label("username:", 150));
        label.add(new Label("password:", 150));
        
        for(int i=0; i < label.size(); i++){
            this.credentials.add(new JTextField());
            Dimension labelSize = new Dimension(150, 20);
            this.credentials.get(i).setPreferredSize(labelSize);
        }
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        for(int i = 0; i < label.size(); i++){
            add(label.get(i), this.gbc);
            this.gbc.gridx++;
            add(this.credentials.get(i), this.gbc);
            this.gbc.gridx--;
            this.gbc.gridy++;
        }
        
        add(this.save2, this.gbc);
        this.gbc.gridx++;
        add(this.back, this.gbc);
        revalidate();
        repaint();
    }
    
    public List<String> getData1(){
        List<String> data = new ArrayList<>();
        for(int i = 0; i < this.password.size(); i++){
            data.add(this.password.get(i).getText());
        }
        return data;
    }
    
    public List<String> getData2(){
        List<String> data = new ArrayList<>();
        for(int i = 0; i < this.credentials.size(); i++){
            data.add(this.credentials.get(i).getText());
            System.out.print(data.get(i));
        }
        return data;
    }
    
    
    public void restore(){
        removeAll();
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        add(this.changePassword, this.gbc);
        this.gbc.gridy++;
        add(this.addUser, this.gbc);
        revalidate();
        repaint();
    }
}
    

