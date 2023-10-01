/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.formFactory;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Model;
import view.ErrorDialog;
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
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        this.save = new JButton("save");
        
        this.label = label;
        this.addRef = new JButton("add");
        this.addPhone = new JButton("add");
        this.type = type;
        
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
    
    public boolean controlErrors(){
        boolean anyConditionMet = false;
        int s = 0;
        String item  = new String();
        
        if(this.type.equals("loom")){
            for(int i = 0; i < this.label.size(); i++){
                if(this.label.get(i).getText().equals("number:")){
                    if(this.text.get(i).getText().isBlank()){
                        ErrorDialog.showErrorDialog("ERROR: the number cannot be null");
                        anyConditionMet = true;
                    }else{
                        boolean isUnique = true;
                        for(int k = 0; k < Model.getLoomList().size(); k++){
                            if(Model.getLoomList().get(k).getNumber() == Integer.parseInt(this.text.get(i).getText())){
                                isUnique = false;
                            }
                        }
                        if(isUnique == false){
                            ErrorDialog.showErrorDialog("ERROR: the loom must be unique");
                            anyConditionMet = true;
                        }
                    }
                }
                else if(this.label.get(i).getText().equals("item name:")){
                    if(this.text.get(i).getText().isBlank()){
                        ErrorDialog.showErrorDialog("ERROR: the item cannot be null");
                        anyConditionMet = true;
                    }else{
                        boolean exist = false;
                        for(int k = 0; k < Model.getItemList().size(); k++){
                            if(Model.getItemList().get(k).getName().equals(this.text.get(i).getText())){
                                exist = true;
                                item = this.text.get(i).getText();
                                s = k;
                            }
                        }
                        if(exist == false){
                            ErrorDialog.showErrorDialog("ERROR: the item must exist");
                            anyConditionMet = true;
                        }else{
                            
                        }
                    }
                }
                else if(this.label.get(i).getText().equals("total meters:")){
                    if(this.text.get(i).getText().isBlank()){
                        ErrorDialog.showErrorDialog("ERROR: the value cannot be null");
                        anyConditionMet = true;
                    }else{
                        if(item != null && Integer.parseInt(this.text.get(i).getText()) > Model.getItemList().get(s).getDisponibility()){
                            ErrorDialog.showErrorDialog("ERROR: the value is greater than the item's disponibility; there are" + Model.getItemList().get(s).getDisponibility() + "meters available");
                            anyConditionMet = true;
                        }
                    }
                }
                else{
                    if(this.text.get(i).getText().isBlank()){
                        ErrorDialog.showErrorDialog("ERROR: the value cannot be null");
                        anyConditionMet = true;
                    }else{
                        if(Integer.parseInt(this.text.get(i).getText()) < 0){
                            ErrorDialog.showErrorDialog("ERROR: the value cannot be less than zero");
                        anyConditionMet = true;
                        }
                }
                }
            }
        }
        
        if(this.type.equals("item")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for(int i = 0; i < this.label.size(); i++){
                if(this.label.get(i).getText().equals("delivery date:")){
                    if(this.text.get(i).getText().isBlank()){
                        ErrorDialog.showErrorDialog("ERROR: the value cannot be null");
                        anyConditionMet = true;
                    }else{
                        if(LocalDate.parse(this.text.get(i).getText(), formatter).isBefore(LocalDate.now())){
                            ErrorDialog.showErrorDialog("ERROR: delivery date cannot be before today");
                            anyConditionMet = true;
                        }
                    }
                }
                else if(this.label.get(i).getText().equals("client name:") || this.label.get(i).getText().equals("name:")){
                    if(this.text.get(i).getText().isBlank()){
                        ErrorDialog.showErrorDialog("ERROR: the value cannot be null");
                        anyConditionMet = true;
                    }
                }
                else{
                    if(this.text.get(i).getText().isBlank()){
                        ErrorDialog.showErrorDialog("ERROR: the value cannot be null");
                        anyConditionMet = true;
                    }else{
                        if(Integer.parseInt(this.text.get(i).getText()) < 0){
                            ErrorDialog.showErrorDialog("ERROR: the value cannot be less than zero");
                            anyConditionMet = true;
                    }
                }
            }
                
            }
            
        }
        
        if(this.type.equals("client")){
            if(this.clientText.get(0).get(0).getText().isBlank()){
                    ErrorDialog.showErrorDialog("ERROR: the value cannot be null");
                    anyConditionMet = true;           
            }
        }
        
        return anyConditionMet;
    }
    
    public void updateSize(JPanel panel){
        setSize(panel.getSize());
        revalidate();
        repaint();
    }
    
}
