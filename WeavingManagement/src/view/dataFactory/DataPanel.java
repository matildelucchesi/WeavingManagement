/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dataFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.value;
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
        float[] lighterGrayRGB = Color.RGBtoHSB(192, 192, 192, null);
        Color lighterGray = Color.getHSBColor(lighterGrayRGB[0], lighterGrayRGB[1], lighterGrayRGB[2]);
        remove(this.delete);
        this.makeTextEditable();
        this.modify.setText("save");
        
        gbc.gridx = this.refGBCX + 2;
        gbc.gridy = this.refGBCY;
        add(addRef, gbc);
        
        gbc.gridx = this.phoneGBCX + 2;
        gbc.gridy = this.phoneGBCY;
        add(addPhone, gbc);
        
        //listener for addRef button
        addRef.addActionListener(e ->{
            //remove stuff
            remove(addPhone);
            for(int i = 1; i < this.label.size(); i++){
                remove(this.label.get(i));
                for(int j = 0; j < this.textList.get(i).size(); j++){
                    remove(this.textList.get(i).get(j));
                }
            }
            remove(this.modify);
            
            //add new text area
            JTextArea refArea = new JTextArea();
            refArea.setBackground(lighterGray);
            refArea.setEditable(true);
            refArea.setOpaque(true);
            refArea.setPreferredSize(new Dimension(150, 20));
            this.textList.get(1).add(refArea);
            
            //add stuff
            gbc.gridx = 0;
            gbc.gridy = 1;
            for(int i = 1; i< this.label.size(); i++){
                add(this.label.get(i), this.gbc);
                if(i == 2){
                    this.phoneGBCY = this.gbc.gridy;
                    this.gbc.gridx = this.gbc.gridx + 2;
                    add(addPhone, gbc);
                    this.gbc.gridx = this.gbc.gridx - 2;
                }
                this.gbc.gridx++;
                for(int j=0; j < this.textList.get(i).size(); j++){
                    add(this.textList.get(i).get(j), this.gbc);
                    this.gbc.gridy++;
                }
                gbc.gridx--;
            }
            gbc.gridx++;
            add(this.modify, gbc);
           
            revalidate();
            repaint();
        });
        
        //listener for add phone button
        addPhone.addActionListener(e1 ->{
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
           
            gbc.gridx = this.phoneGBCX;
            gbc.gridy = this.phoneGBCY;

            for(int i = 2; i< this.label.size(); i++){
                add(this.label.get(i), this.gbc);
                this.gbc.gridx++;
                for(int j=0; j < this.textList.get(i).size(); j++){
                    add(this.textList.get(i).get(j), this.gbc);
                    this.gbc.gridy++;
                }
                gbc.gridx--;
            }
            gbc.gridx++;
            add(this.modify, gbc);
            
            revalidate();
            repaint();
            
        });
        
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
        remove(this.addPhone);
        remove(this.addRef);
        this.modify.setText("modify");
        
        for(int i = 1; i < this.label.size(); i++){
            remove(this.label.get(i));
            for(int j = 0; j < this.textList.get(i).size(); j++){
                remove(this.textList.get(i).get(j));
            }
        }
        
        //add
        this.gbc.gridy = this.refGBCY;
        this.gbc.gridx = this.refGBCX;
        
        for(int i = 1; i < this.textList.size(); i ++){
            for(int j = 0; j < this.textList.get(i).size(); j++){
                if(this.textList.get(i).get(j).getText().isBlank()){
                    this.textList.get(i).remove(j);
                }
            }
            if(this.textList.get(i).isEmpty()){
                this.textList.get(i).add(new JTextArea());
            }
        }
        
        for(int i=1; i < this.label.size(); i++){
            add(this.label.get(i), this.gbc);
            gbc.gridx++;
            for(int j = 0; j < this.textList.get(i).size(); j++){
                add(this.textList.get(i).get(j), this.gbc);
                this.gbc.gridy++;
            }
            this.gbc.gridx--;
        }
        this.gbc.gridx++;
        add(this.delete, this.gbc);
        this.gbc.gridy++;
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
    
}
