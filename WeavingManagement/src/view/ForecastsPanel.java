/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.Model;

/**
 *
 * @author Matilde
 */
public class ForecastsPanel extends JPanel{
    private List<Label> label = new ArrayList<>();
    private List<Label> startLabel = new ArrayList<>();
    int index = 1;
    private List<JTextArea> text = new ArrayList<>();
    private List<JTextArea> startText = new ArrayList<>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton add = new JButton("add");
    private JButton calculate = new JButton("calculate");
    private int endGBCX = 0;
    private int endGBCY = 0;
    private int loomGBCY = 0;
    private Component box = Box.createVerticalStrut(20);
    private Color lightGray = new Color(211, 211, 211);
    
    public ForecastsPanel(){
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        //item data
        this.label.add(new Label("Item", 150));
        this.label.add(new Label("meters:", 150));
        this.label.add(new Label("row number:", 150));
        this.label.add(new Label("hits:", 150));
        //loom data
        this.label.add(new Label("Loom " + index, 150));
        this.label.add(new Label("speed:", 150));
        this.label.add(new Label("surender:", 150));
        this.label.add(new Label("total meters:", 150));
        
        this.startLabel = new ArrayList<>(this.label);
        
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        
        for(int i = 0; i < this.label.size(); i++){
            if(this.label.get(i).getText().equals("Item") || (this.label.get(i).getText().equals("Loom " + index))){
                this.text.add(null);
            }
            else{
                JTextArea textArea = new JTextArea();
                textArea.setPreferredSize(new Dimension(150, 20));
                textArea.setBackground(lightGray);
                this.text.add(textArea);
            }
        }
        
        this.startText = new ArrayList<>(this.text);
        
        for(int k = 0; k < this.label.size(); k++){
            if(this.label.get(k).getText().equals("Item") || this.label.get(k).getText().equals("Loom " + index)){
                this.gbc.gridx = 0;
                add(this.label.get(k), this.gbc);
                this.gbc.gridx++;
                if(this.label.get(k).getText().equals("Loom "+ index)){
                    this.gbc.gridx = 3;
                    add(this.add, this.gbc);
                    this.gbc.gridx = 1;
                }
            }
            else if(this.text.get(k) != null){
                add(this.label.get(k), this.gbc);
                this.gbc.gridx++;
                add(this.text.get(k), this.gbc);
                this.gbc.gridy++;
                this.gbc.gridx--;
            }
        }
        this.loomGBCY = this.gbc.gridy;
        
        add(this.box, this.gbc);
        this.gbc.gridy++;
        add(this.calculate, this.gbc);
        this.endGBCX = this.gbc.gridx;
        this.endGBCY = this.gbc.gridy;
        
    }
    
    public JButton getAddButton(){
        return this.add;
    }
    
    public JButton getCalculateButton(){
        return this.calculate;
    }
    
    public void addLoomFields(){
        List<Label> label1 = new ArrayList<>();
        List<JTextArea> text1 = new ArrayList<>();
        
        index++;
        label1.add(new Label("Loom " + index, 150));
        label1.add(new Label("speed:", 150));
        label1.add(new Label("surrender:", 150));
        label1.add(new Label("total meters:", 150));
        
        this.gbc.gridx = 0;
        this.gbc.gridy = this.loomGBCY;
        
        remove(this.box);
        remove(this.calculate);
        
        for(int i = 0; i < label1.size(); i++){
            if(label1.get(i).getText().equals("Loom " + index)){
                text1.add(null);
            }
            else{
                JTextArea area = new JTextArea();
                area.setPreferredSize(new Dimension(150, 20));
                area.setBackground(lightGray);
                text1.add(area);
            }
        }
        
        
        for(int k = 0; k < label1.size(); k++){
            if(label1.get(k).getText().equals("Loom " + index)){
                add(label1.get(k), this.gbc);
                this.gbc.gridx = 3;
                add(this.add, this.gbc);
                this.gbc.gridx = 1;
            }else{
                System.out.print(k);
                add(label1.get(k), this.gbc);
                this.gbc.gridx++;
                add(text1.get(k), this.gbc);
                this.gbc.gridy++;
                this.gbc.gridx--;
            }

        }
        
        this.loomGBCY = this.gbc.gridy;
        this.label.addAll(label1);
        this.text.addAll(text1);
        
        add(this.box, this.gbc);
        this.gbc.gridy++;
        add(this.calculate, this.gbc);
        this.endGBCX = this.gbc.gridx;
        this.endGBCY = this.gbc.gridy;
        
        revalidate();
        repaint();
    }
    
    public List<Integer> getItemData(){
        List<Integer> data = new ArrayList();
        for(int i=0; i < this.text.size(); i++){
            if(this.label.get(i).getText().equals("Loom "+ 1)){
                break;
            }
            if(this.text.get(i) != null){
                data.add(Integer.valueOf(this.text.get(i).getText().trim()));
            }
        }
        return data;
    }
    
    public List<List<Integer>> getLoomData(){
        List<List<Integer>> data = new ArrayList();
        for(int k = 0; k < index; k++){
            data.add(new ArrayList());
            for(int j = 4; j < this.text.size(); j = j+4){
                for(int i = j; i < j + 4; i++){
                    if(this.text.get(i) != null){
                        data.get(k).add(Integer.valueOf(this.text.get(i).getText().trim()));
                    }
                }
            }
        }
        
        return data;
    }
    
    public void seeResult(String expectedEndDate){
        for(int i=0; i < this.text.size(); i++){
            if(this.text.get(i)!= null){
                this.text.get(i).setEditable(false);
                this.text.get(i).setBackground(Color.WHITE);
            }
        }
        
        remove(this.add);
        this.calculate.setText("new forecast");
        
        this.gbc.gridx = this.endGBCX;
        this.gbc.gridy = this.endGBCY;
        
        
        this.gbc.gridy++;
        this.gbc.gridx = 0;
        
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("The expected end date is: " + expectedEndDate);
        add(area, this.gbc);
        
        revalidate();
        repaint();
    }
    
    public void restore(){
        removeAll();
        this.index = 1;
        
        for(int i = 0; i < this.startText.size(); i++){
            if(this.startText.get(i) != null){
                this.startText.get(i).setEditable(true);
                this.startText.get(i).setText(" ");
                this.startText.get(i).setBackground(lightGray);
            }
            
        }
        
        for(int k = 0; k < this.startLabel.size(); k++){
            if(this.startLabel.get(k).getText().equals("Item") || this.startLabel.get(k).getText().equals("Loom " + this.index)){
                this.gbc.gridx = 0;
                add(this.startLabel.get(k), this.gbc);
                this.gbc.gridx++;
                if(this.startLabel.get(k).getText().equals("Loom "+ this.index)){
                    this.gbc.gridx = 3;
                    add(this.add, this.gbc);
                    this.gbc.gridx = 1;
                }
            }
            else if(this.startText.get(k) != null){
                add(this.startLabel.get(k), this.gbc);
                this.gbc.gridx++;
                add(this.startText.get(k), this.gbc);
                this.gbc.gridy++;
                this.gbc.gridx--;
            }
        }
        this.loomGBCY = this.gbc.gridy;
        
        add(this.box, this.gbc);
        this.gbc.gridy++;
        this.calculate.setText("calculate");
        add(this.calculate, this.gbc);
        this.endGBCX = this.gbc.gridx;
        this.endGBCY = this.gbc.gridy;
        
        revalidate();
        repaint();
    }
    
    public boolean controlErrors(){
        boolean anyConditionMet = false;
        for(int i = 0; i < this.label.size(); i++){
            if(this.text.get(i) != null && this.text.get(i).getText().isBlank()){
                ErrorDialog.showErrorDialog("ERROR: the value cannot be null");
                anyConditionMet = true;
            }
        }
        return anyConditionMet;
    }
    
    
}