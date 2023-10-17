/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dataFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import view.Dialog;
import view.Label;

/**
 *
 * @author Matilde
 */
public abstract class DataPanel extends JPanel{
    private GridBagConstraints gbc = new GridBagConstraints();
    private int metersRunGBCX = 0;
    private int metersRunGBCY = 0;
    private int endGBCX = 0;
    private int endGBCY = 0;
    private int refGBCX = 0;
    private int refGBCY = 0;
    private int phoneGBCX = 0;
    private int phoneGBCY = 0;
    JButton addMetersRun = new JButton("add");
    JButton modify = new JButton("modify");
    JButton addRef = new JButton("add");
    JButton addPhone = new JButton("add");
    JButton delete = new JButton("delete");
    JButton end = new JButton("end");
    List<JTextArea> text = new ArrayList<>();
    List<Label> label = new ArrayList<>();
    List<List<JTextArea>> textList = new ArrayList<>();
    int ref = 0;
    int phone = 0;
    
    public void createDataPanel(List<Label> label, List<JTextArea> text){
        
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        this.text = text;
        this.label = label;
        
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        
        for(int i = 0; i < this.label.size(); i++){
            add(this.label.get(i), this.gbc);
            if(this.label.get(i).getText().equals("meters run:")){
                this.metersRunGBCX = gbc.gridx;
                this.metersRunGBCY = gbc.gridy;
            }
            this.gbc.gridx++;
            add(this.text.get(i), this.gbc);
            this.gbc.gridx--;
            this.gbc.gridy++;
        }
        
        this.gbc.gridx = 1;
        this.gbc.gridy++;
        add(this.delete, this.gbc);
        
        this.endGBCX = this.gbc.gridx;
        this.endGBCY = this.gbc.gridy;
        
    }
    
    public void createDataPanelWithList(List<Label> label, List<List<JTextArea>> text){
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        
        this.textList = text;
        this.label = label;
        
        this.gbc.gridx = 0;
        this.gbc.gridy = -1;
        
        for(int s=0; s < this.textList.size(); s++){
            if(this.textList.get(s).isEmpty()){
                this.textList.get(s).add(new JTextArea(""));
            }
        }
        
        for(int i = 0; i< this.label.size(); i++){
            this.gbc.gridy++;
            add(this.label.get(i), this.gbc);
            if(this.label.get(i).getText().equals("referent:")){
                this.refGBCX = this.gbc.gridx;
                this.refGBCY = this.gbc.gridy;
                add(this.label.get(i), this.gbc);
            }
            if(this.label.get(i).getText().equals("phone number:")){
                this.gbc.gridx = 3;
                this.gbc.gridy = this.refGBCY;
                this.phoneGBCX = this.gbc.gridx;
                this.phoneGBCY = this.gbc.gridy;
                add(this.label.get(i), this.gbc);
            }
            if(this.label.get(i).getText().equals("item:")){
                this.gbc.gridx = 0;
                add(this.label.get(i), this.gbc);
            }
            this.gbc.gridx++;
            for(int j=0; j < this.textList.get(i).size(); j++){
                add(this.textList.get(i).get(j), this.gbc);
                this.gbc.gridy++;
            }
            
            gbc.gridx--;
        }
        
        this.gbc.gridy++;
        this.gbc.gridx++;
        add(this.delete, this.gbc);
        
        this.endGBCX = this.gbc.gridx;
        this.endGBCY = this.gbc.gridy + 1;
    }
    
    public void addMetersRunButton(){
        this.gbc.gridx = this.metersRunGBCX + 2;
        this.gbc.gridy = this.metersRunGBCY;
        add(this.addMetersRun, gbc);
    }
    
    public JButton getAddMetersRunButton(){
        return this.addMetersRun;
    }
    
    public int getMetersRun(){
        String metersRun = new String();
        for(int i = 0; i < this.label.size(); i++){
            if(this.label.get(i).getText().equals("meters run:")){
                metersRun = this.text.get(i).getText();
            }
        }
        return Integer.parseInt(metersRun);
    }
    
    public void adjournMetersToGo(int value){
        for(int i = 0; i < this.label.size(); i++){
            if(this.label.get(i).getText().equals("meters to go:")){
                this.text.get(i).setText(Integer.toString(value));
            }
            else if(this.label.get(i).getText().equals("meters run:")){
                this.text.get(i).setText(Integer.toString(0));
            }
        }
    }
    
    public void adjournExpectedEndDate(LocalDate expectedEndDate){
        for(int i = 0; i < this.label.size(); i++){
            if(this.label.get(i).getText().equals("expected end date:")){
                this.text.get(i).setText(expectedEndDate.toString());
            }
        }
    }
    
    public void addModifyButton(){
        this.gbc.gridx = this.endGBCX;
        this.gbc.gridy = this.endGBCY;
        
        add(this.modify, gbc);
    }
    
    public JButton getModifyButton(){
        return this.modify;
    }
    
    public void addModifyComponents(){
        remove(this.delete);
        this.makeTextEditable();
        this.modify.setText("save");
        
        gbc.gridx = 2;
        gbc.gridy = this.refGBCY;
        add(addRef, gbc);
        
        gbc.gridy = this.phoneGBCY;
        
        remove(this.label.get(2));
        this.gbc.gridx = 3;
        add(this.label.get(2), this.gbc);
        for(int i=0; i < this.textList.get(2).size(); i++){
            remove(this.textList.get(2).get(i));
            this.gbc.gridx++;
            add(this.textList.get(2).get(i), this.gbc);
            this.gbc.gridy++;
        }
        
        gbc.gridx = 5;
        gbc.gridy = this.phoneGBCY;
        add(addPhone, gbc);
        
        revalidate();
        repaint();
        
    }
    
    public void makeTextEditable(){
        float[] lighterGrayRGB = Color.RGBtoHSB(192, 192, 192, null);
        Color lighterGray = Color.getHSBColor(lighterGrayRGB[0], lighterGrayRGB[1], lighterGrayRGB[2]);
        
        for(int i = 1; i < this.textList.size() - 1; i++){
            {
                for(int j=0; j < this.textList.get(i).size(); j++){
                    this.textList.get(i).get(j).setEditable(true);
                    this.textList.get(i).get(j).setBackground(lighterGray);
                }
            }
        }
        
    }
    
    public void refListener(){
            float[] lighterGrayRGB = Color.RGBtoHSB(192, 192, 192, null);
            Color lighterGray = Color.getHSBColor(lighterGrayRGB[0], lighterGrayRGB[1], lighterGrayRGB[2]);
        
            //remove stuff
            removeAll();
            
            //add new text area
            JTextArea refArea = new JTextArea();
            refArea.setBackground(lighterGray);
            refArea.setEditable(true);
            refArea.setOpaque(true);
            refArea.setPreferredSize(new Dimension(150, 20));
            this.textList.get(1).add(refArea);
            
            
            //add stuff
            ref = 0;
            phone = 0;
            
            for(int i = 0; i< this.label.size(); i++){
                switch (i) {
                    case 0:
                        this.gbc.gridx = 0;
                        this.gbc.gridy = 0;
                        add(this.label.get(i), this.gbc);
                        this.gbc.gridx++;
                        break;
                    case 1:
                        this.gbc.gridx = 0;
                        this.gbc.gridy = 1;
                        add(this.label.get(i), this.gbc);
                        this.gbc.gridx = 2;
                        add(addRef, this.gbc);
                        this.gbc.gridx--;
                        break;
                    case 2:
                        this.gbc.gridx = 3;
                        this.gbc.gridy = this.phoneGBCY - 1;
                        add(this.label.get(i), this.gbc);
                        this.gbc.gridx = 5;
                        add(addPhone, gbc);
                        this.gbc.gridx--;
                        break;
                    case 3:
                        this.gbc.gridx = 0;
                        if(phone > ref){
                            this.gbc.gridy = phone + 1;
                        }else{
                            this.gbc.gridy = this.refGBCY + ref + 1;
                        }
                        add(this.label.get(i), this.gbc);
                        break;
                    default:
                        break;
                }
                for(int j=0; j < this.textList.get(i).size(); j++){
                    add(this.textList.get(i).get(j), this.gbc);
                    this.gbc.gridy++;
                    if(i == 1){
                        ref++;
                    }
                    if(i == 2){
                        phone++;
                    }
                }
            }
            gbc.gridx++;
            add(this.modify, gbc);
           
            revalidate();
            repaint();
    }
    
    public void phoneListener(){
        float[] lighterGrayRGB = Color.RGBtoHSB(192, 192, 192, null);
        Color lighterGray = Color.getHSBColor(lighterGrayRGB[0], lighterGrayRGB[1], lighterGrayRGB[2]);
        
        //remove stuff
            for(int i = 2; i < this.label.size(); i++){
                remove(this.label.get(i));
                for(int j = 0; j < this.textList.get(i).size(); j++){
                    remove(this.textList.get(i).get(j));
                }
            }
            remove(this.modify);
            
            JTextArea pArea = new JTextArea();
            pArea.setBackground(lighterGray);
            pArea.setEditable(true);
            pArea.setOpaque(true);
            pArea.setPreferredSize(new Dimension(150, 20));
            this.textList.get(2).add(pArea);
           
            
            //add stuff
            
            for(int i = 2; i< this.label.size(); i++){
                switch(i){
                    case 2:
                        this.gbc.gridx = 3;
                        this.gbc.gridy = this.phoneGBCY - 1;
                        add(this.label.get(i), this.gbc);
                        this.gbc.gridx = 5;
                        add(addPhone, gbc);
                        this.gbc.gridx--;
                        break;
                    case 3:
                        this.gbc.gridx = 0;
                        if(phone > ref){
                            this.gbc.gridy = phone + 1;
                        }else{
                            this.gbc.gridy = this.refGBCY + ref + 1;
                        }
                        add(this.label.get(i), this.gbc);
                        break;
                    default:
                        break;
                        
                }
                for(int j=0; j < this.textList.get(i).size(); j++){
                    add(this.textList.get(i).get(j), this.gbc);
                    this.gbc.gridy++;
                }
                gbc.gridx--;
            }
            this.gbc.gridx = 1;
            add(this.modify, gbc);
            
            revalidate();
            repaint();
            
    }
    
    public List<String> getReferentsList(){
        List<String> referents = new ArrayList<>();
        for(int i = 1; i < this.textList.size(); i ++){
            for(int j = 0; j < this.textList.get(i).size(); j++){
                if( i == 1 && !this.textList.get(i).get(j).getText().isBlank()){
                    referents.add(this.textList.get(i).get(j).getText());
                }
            }
        }
        return referents;
    }
    
    public List<String> getPhoneList(){
        List<String> phone = new ArrayList<>();
        for(int i = 1; i < this.textList.size(); i ++){
            for(int j = 0; j < this.textList.get(i).size(); j++){
                if( i == 2 && !this.textList.get(i).get(j).getText().isBlank()){
                    phone.add(this.textList.get(i).get(j).getText());
                }
            }
        }
        return phone;
    }
    
    public void restoreComponents(){
        removeAll();
        
        //add
        this.gbc.gridy = this.refGBCY;
        this.gbc.gridx = this.refGBCX;
        
        for(int i = 1; i < this.textList.size(); i ++){
            for(int j = this.textList.get(i).size() - 1; j >= 0; j--){
                if(this.textList.get(i).get(j).getText().isBlank()){
                    this.textList.get(i).remove(j);
                }
            }
            if(this.textList.get(i).isEmpty()){
                this.textList.get(i).add(new JTextArea());
            }
            
        }
        
        int s = 0 ;
        
        for(int i=0; i < this.label.size(); i++){
            switch(i){
                case 0:
                    this.gbc.gridx = 0;
                    this.gbc.gridy = 0;
                    add(this.label.get(i), this.gbc);
                    this.gbc.gridx++;
                    break;
                case 1:
                    this.gbc.gridx = 0;
                    this.gbc.gridy = this.refGBCY;
                    add(this.label.get(i), this.gbc);
                    this.gbc.gridx++;
                    break;
                case 2:
                    this.gbc.gridx = 2;
                    this.gbc.gridy = this.phoneGBCY;
                    add(this.label.get(i), this.gbc);
                    this.gbc.gridx++;
                    break;
                case 3:
                    this.gbc.gridx = 0;
                    this.gbc.gridy = this.phoneGBCY + s + 1;
                    add(this.label.get(i), this.gbc);
                    this.gbc.gridx++;
                    break;
                default:
                    break;
            }
            
            for(int j = 0; j < this.textList.get(i).size(); j++){
                add(this.textList.get(i).get(j), this.gbc);
                if(i == 2){
                    s++;
                }
                this.gbc.gridy++;
            }
        }
        this.gbc.gridx = 1;
        add(this.delete, this.gbc);
        this.gbc.gridy++;
        this.modify.setText("modify");
        add(this.modify, this.gbc);
            
        for(int i = 1; i < this.textList.size(); i++){
            for(int j=0; j < this.textList.get(i).size(); j++){
                this.textList.get(i).get(j).setEditable(false);
                this.textList.get(i).get(j).setBackground(Color.WHITE);
            } 
        }
        revalidate();
        repaint();
    }
    
    public JButton getDeleteButton(){
        return this.delete;
    }
    
    public void addEndButton(){
        this.gbc.gridx = this.endGBCX;
        this.gbc.gridy = this.endGBCY;
        add(this.end, this.gbc);
    }
    
    public JButton getEndButton(){
        return this.end;
    }
    
    public void error(){
        Dialog.showErrorDialog("ERROR: you cannot insert a value of meters higher than the meters to go");
    }
    
    public JButton getAddPhone(){
        return this.addPhone;
    }
    
    public JButton getAddRef(){
        return this.addRef;
    }
}


