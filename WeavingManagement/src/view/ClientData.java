/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import SQLite.AssociationDB;
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
import javax.swing.JTextArea;
import model.Client;

/**
 *
 * @author Matilde
 */
public class ClientData extends JPanel{
    
    private Client client;
    private List<String> itemList = new ArrayList<>();
    private AssociationDB listGetter = new AssociationDB();
    
    private JLabel name;
    private JTextArea textName;
    private JLabel referents;
    private List<JTextArea> textReferents = new ArrayList<>();
    private JLabel phone;
    private List<JTextArea> textPhone = new ArrayList<>();
    private JLabel item;
    private List<JTextArea> textItem = new ArrayList<>();
    private List<String> referentsList = new ArrayList<>();
    private List<String> phoneNumberList = new ArrayList<>();
    private int gbcRefX;
    private int gbcRefY;
    private int gbcPX;
    private int gbcPY;
    
    private JButton addRef = new JButton("add");
    private JButton addPhone = new JButton("add");
    
    private JButton modify = new JButton("modify");
    private JButton delete = new JButton("delete");
    
    public ClientData(Client client, JScrollPane scroll){
        this.client = client;
        FormUtility.setScroll(scroll);
        
        referentsList = this.client.getReferents();
        phoneNumberList = this.client.getPhoneNumber();
        
        this.itemList = listGetter.getItemAssociationClientList(this.client.getName());
        
        this.name = new Label("name:", 300);
        this.textName = new NonEditableTextArea(this.client.getName());
        this.referents = new Label("referents:", 300);
        
        if(referentsList.isEmpty()){
            this.textReferents.add(new NonEditableTextArea(""));
        }else{
            for(int i = 0; i < referentsList.size(); i++){
                this.textReferents.add(new NonEditableTextArea(referentsList.get(i)));
            }
        }
        
        this.phone = new Label("phone:", 300);
        
        if(phoneNumberList.isEmpty()){
            this.textPhone.add(new NonEditableTextArea(""));
        }else{
            for(int i = 0; i < phoneNumberList.size(); i++){
                this.textPhone.add(new NonEditableTextArea(phoneNumberList.get(i)));
            }
        }
        
        this.item = new Label("item:", 300);
        
        
        for(int i = 0; i < this.itemList.size(); i++){
            this.textItem.add(new NonEditableTextArea(this.itemList.get(i)));
        }
        
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(this.name, gbc);
        
        gbc.gridx++;
        add(this.textName, gbc);
        gbc.gridx--;
        
        gbc.gridy++;
        add(this.referents, gbc);
        
        gbc.gridx++;
        this.gbcRefX = gbc.gridx;
        this.gbcRefY = gbc.gridy;
        
        for(int i = 0; i < this.textReferents.size(); i++){
            add(this.textReferents.get(i), gbc);
            gbc.gridy++;
        }
        
        gbc.gridx--;
        add(this.phone, gbc);
        
        gbc.gridx++;
        this.gbcPX = gbc.gridx;
        this.gbcPY = gbc.gridy;
        
        for(int i = 0; i < this.textPhone.size(); i++){
                add(this.textPhone.get(i), gbc);
                gbc.gridy++;
            }
        gbc.gridx--;
        
        
        add(this.item, gbc);
        gbc.gridx++;
        
        if(this.itemList.isEmpty()){
            gbc.gridy++;
        }else{
            for(int i = 0; i < this.itemList.size(); i++){
                add(this.textItem.get(i), gbc);
                gbc.gridy++;
            }
        }
        
        gbc.gridy++;
        add(this.modify, gbc);
        
        gbc.gridy++;
        add(this.delete, gbc);
        
    }
    
    public JButton getModify(){
        return this.modify;
    }
    
    public void makeTextEditable(){
        float[] lighterGrayRGB = Color.RGBtoHSB(192, 192, 192, null);
        Color lighterGray = Color.getHSBColor(lighterGrayRGB[0], lighterGrayRGB[1], lighterGrayRGB[2]);
        
        for(int i = 0; i < this.textReferents.size(); i++){
            this.textReferents.get(i).setEditable(true);
            this.textReferents.get(i).setBackground(lighterGray);
            this.textReferents.get(i).setOpaque(true);
        }
        
        for(int i = 0; i < this.textPhone.size(); i++){
            this.textPhone.get(i).setEditable(true);
            this.textPhone.get(i).setBackground(lighterGray);
            this.textPhone.get(i).setOpaque(true);
        }
        
    }
    
    public JTextArea getTextName(){
        return this.textName;
    }
    
    public List<String> getReferentsList(){
        List<String> ref = new ArrayList<>();
        for(int i = 0; i < this.textReferents.size(); i++){
            ref.add(this.textReferents.get(i).getText());
        }
        return ref;
    }
    
    public List<String> getPhoneNumberList(){
        List<String> phone = new ArrayList<>();
        for(int i = 0; i < this.textPhone.size(); i++){
            phone.add(this.textPhone.get(i).getText());
        }
        return phone;
    }
    
    public void addModifyComponents(){
        remove(this.delete);
        this.makeTextEditable();
        float[] lighterGrayRGB = Color.RGBtoHSB(192, 192, 192, null);
        Color lighterGray = Color.getHSBColor(lighterGrayRGB[0], lighterGrayRGB[1], lighterGrayRGB[2]);
        
        this.modify.setText("save");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridx = this.gbcRefX + 2;
        gbc.gridy = this.gbcRefY;
        add(this.addRef, gbc);
        
        gbc.gridx = this.gbcPX + 2;
        gbc.gridy = this.gbcPY;
        add(this.addPhone, gbc);
        
        this.addRef.addActionListener(e ->{
            //remove stuff
            remove(this.addRef);
            for(int i=0; i < this.textReferents.size(); i++){
                remove(this.textReferents.get(i));
            }
            remove(this.addPhone);
            remove(this.phone);
            for(int i=0; i < this.textPhone.size(); i++){
                remove(this.textPhone.get(i));
            }
            remove(this.item);
            for(int i=0; i < this.textItem.size(); i++){
                remove(this.textItem.get(i));
            }
            remove(this.modify);
            
            //add new text area
            JTextArea refArea = new JTextArea();
            refArea.setBackground(lighterGray);
            refArea.setEditable(true);
            refArea.setOpaque(true);
            refArea.setPreferredSize(new Dimension(200, 30));
            this.textReferents.add(refArea);
            
            //add stuff
            gbc.gridy = this.gbcRefY;
            gbc.gridx = this.gbcRefX;
            gbc.gridx++;
            add(this.addRef, gbc);
            gbc.gridx--;
            this.gbcRefX = gbc.gridx;
            this.gbcRefY = gbc.gridy;
            for(int i=0; i < this.textReferents.size(); i++){
                add(this.textReferents.get(i), gbc);
                gbc.gridy++;
            }
            
            gbc.gridx--;
            add(this.phone, gbc);
            gbc.gridx = gbc.gridx + 2;
            add(this.addPhone, gbc);
            gbc.gridx--;
            this.gbcPX = gbc.gridx;
            this.gbcPY = gbc.gridy;
            for(int i=0; i < this.textPhone.size(); i++){
                add(this.textPhone.get(i), gbc);
                gbc.gridy++;
            }
            gbc.gridx--;
            add(this.item, gbc);
            gbc.gridx++;
            for(int i=0; i < this.textItem.size(); i++){
                add(this.textItem.get(i), gbc);
                gbc.gridy++;
            }
            
            add(this.modify, gbc);
           
            revalidate();
            repaint();
        });
        
        this.addPhone.addActionListener(e1 ->{
            remove(this.addPhone);
            for(int i=0; i < this.textPhone.size(); i++){
                remove(this.textPhone.get(i));
            }
            remove(this.item);
            for(int i=0; i < this.textItem.size(); i++){
                remove(this.textItem.get(i));
            }
            remove(this.modify);
            
            JTextArea pArea = new JTextArea();
            pArea.setBackground(lighterGray);
            pArea.setEditable(true);
            pArea.setOpaque(true);
            pArea.setPreferredSize(new Dimension(200, 30));
            this.textPhone.add(pArea);
           
            gbc.gridx = this.gbcPX;
            gbc.gridy = this.gbcPY;
            
            gbc.gridx++;
            add(addPhone, gbc);
            
            gbc.gridx--;
            for(int i=0; i < this.textPhone.size(); i++){
                add(this.textPhone.get(i), gbc);
                gbc.gridy++;
            }
            
            gbc.gridx--;
            add(this.item, gbc);
            
            gbc.gridx++;
            for(int i=0; i < this.textItem.size(); i++){
                add(this.textItem.get(i), gbc);
                gbc.gridy++;
            }
            
            add(this.modify, gbc);
            
            revalidate();
            repaint();
            
        });
    }
    
    public void restoreComponents(){
        remove(this.addPhone);
        remove(this.addRef);
        this.modify.setText("modify");
        for(int i=0; i < this.textReferents.size(); i++){
                remove(this.textReferents.get(i));
        }
        for(int i=0; i < this.textPhone.size(); i++){
                remove(this.textPhone.get(i));
        }
        remove(this.phone);
        
        //add
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = this.gbcRefY;
        gbc.gridx = this.gbcRefX;
        
        for(int i=0; i < this.textReferents.size(); i++){
            if(this.textReferents.get(i).getText().isBlank()){
                remove(this.textReferents.get(i));
                this.textReferents.remove(i);
            }else{
                add(this.textReferents.get(i), gbc);
                gbc.gridy++;
            }
        }
            
        gbc.gridx--;
        add(this.phone, gbc);
        gbc.gridx++;
        
        for(int i=0; i < this.textPhone.size(); i++){
            if(this.textPhone.get(i).getText().isBlank()){
                remove(this.textPhone.get(i));
                this.textPhone.remove(i);
            }else{
                add(this.textPhone.get(i), gbc);
                gbc.gridy++;
            }
        }
    
        gbc.gridx--;
        add(this.item, gbc);
        gbc.gridx++;
        for(int i=0; i < this.textItem.size(); i++){
            add(this.textItem.get(i), gbc);
            gbc.gridy++;
        }
            
        add(this.modify, gbc);
        gbc.gridy++;
    
        add(this.delete, gbc);
            
            for(int i = 0; i < this.textReferents.size(); i++){
                this.textReferents.get(i).setEditable(false);
                this.textReferents.get(i).setBackground(Color.WHITE);
                this.textReferents.get(i).setOpaque(false);
            }
            
            for(int i = 0; i < this.textPhone.size(); i++){
               this.textPhone.get(i).setEditable(false);
            this.textPhone.get(i).setBackground(Color.WHITE);
            this.textPhone.get(i).setOpaque(false);
        }
            
            revalidate();
            repaint();
    }
    
    public JButton getDelete(){
        return this.delete;
    }
}
