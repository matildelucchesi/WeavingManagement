/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Matilde
 */
public class ClientForm extends JPanel{
    private JTextField tName;
    private List<JTextField> tReferents = new ArrayList<>();
    private List<JTextField> tPhone = new ArrayList<>();
    private JLabel name;
    private JLabel referents;
    private JLabel phone;
    
    private JButton save;
    private JButton addReferents;
    private JButton addPhone;
    
    private int gbcRefX;
    private int gbcRefY;
    private int gbcPhoneX;
    private int gbcPhoneY;

    
    public ClientForm(JScrollPane scroll){
        setBackground(Color.WHITE);
        
        FormUtility.setScroll(scroll);
        
        this.name = new Label("name:", 200);
        this.referents = new Label("referents:", 200);
        this.phone = new Label("phone number:", 200);
        
        this.save = new JButton("save");
        this.addReferents = new JButton("add");
        this.addPhone = new JButton("add");
        
        this.tName = new JTextField();
        this.tReferents.add(new JTextField());
        this.tPhone.add(new JTextField());
        
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
    
        Dimension textFieldSize = new Dimension(200, 30);
        this.tName.setPreferredSize(textFieldSize);
        this.tReferents.get(0).setPreferredSize(textFieldSize);
        this.tPhone.get(0).setPreferredSize(textFieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(name, gbc);

        gbc.gridy++;
        add(referents, gbc);

        gbc.gridy++;
        add(phone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(this.tName, gbc);

        gbc.gridy++;
        add(this.tReferents.get(0), gbc);
        this.gbcRefX = gbc.gridx;
        this.gbcRefY = gbc.gridy;

        gbc.gridx++;
        add(this.addReferents, gbc);
        gbc.gridx--;
        
        gbc.gridy++;
        add(this.tPhone.get(0), gbc);
        this.gbcPhoneX = gbc.gridx;
        this.gbcPhoneY = gbc.gridy;
        
        gbc.gridx++;
        add(this.addPhone, gbc);
        gbc.gridx--;
       
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(this.save, gbc);
        
    }
    
    public JButton getAddReferentsButton(){
        return this.addReferents;
    }
    
    public JButton getAddPhoneButton(){
        return this.addPhone;
    }
    
    public JButton getSaveButton(){
        return this.save;
    }
    
    public JTextField getTName(){
        return this.tName;
    }
    
    public List<String> getReferentsList(){
        List<String> ref = new ArrayList<>();
        
        for(int i=0; i < this.tReferents.size(); i++){
            ref.add(this.tReferents.get(i).getText());
        }
        
        return ref;
    }
    
    public List<String> getPhoneList(){
        List<String> p = new ArrayList<>();
        
        for(int i=0; i < this.tPhone.size(); i++){
            p.add(this.tPhone.get(i).getText());
        }
        
        return p;
    }
    
    public void addReferentsText(){
        Dimension textFieldSize = new Dimension(200, 30);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        this.tReferents.add(new JTextField());
        this.tReferents.get(this.tReferents.size() - 1).setPreferredSize(textFieldSize);
        remove(this.addReferents);
        remove(this.tPhone.get(0));
        remove(this.save);
        remove(this.phone);
        remove(this.addPhone);
        
        gbc.gridx = this.gbcRefX;
        gbc.gridy = this.gbcRefY;
        
        for(int i = 0; i < this.tReferents.size(); i++){
            add(this.tReferents.get(i), gbc);
            gbc.gridy++;
        }
        
        gbc.gridx++;
        gbc.gridy--;
        add(this.addReferents, gbc);
        gbc.gridy++;
        gbc.gridx--;
        
        gbc.gridx--;
        add(this.phone, gbc);
        gbc.gridx++;
        
        for(int k = 0; k < this.tPhone.size(); k++){
            if(k == 0){
                this.gbcPhoneX = gbc.gridx;
                this.gbcPhoneY = gbc.gridy;
            }
            add(this.tPhone.get(k), gbc);
            gbc.gridy++;
        }
        
        gbc.gridx++;
        gbc.gridy--;
        add(this.addPhone,gbc);
        gbc.gridx--;
        gbc.gridy++;
        
        add(this.save, gbc);
        
        revalidate();
        repaint();
        
    }
    
    public void addPhoneText(){
        Dimension textFieldSize = new Dimension(200, 30);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        this.tPhone.add(new JTextField());
        this.tPhone.get(this.tPhone.size() - 1).setPreferredSize(textFieldSize);
        remove(this.addPhone);
        remove(this.save);
        
        gbc.gridx = this.gbcPhoneX;
        gbc.gridy = this.gbcPhoneY;
        
        for(int i = 0; i < this.tPhone.size(); i++){
            add(this.tPhone.get(i), gbc);
            gbc.gridy++;
        }
        
        gbc.gridx++;
        gbc.gridy--;
        add(this.addPhone, gbc);
        gbc.gridx--;
        gbc.gridy++;
        
        add(this.save, gbc);
        
        revalidate();
        repaint();
        
    }
}
