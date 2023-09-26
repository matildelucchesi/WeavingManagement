/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Loom;

/**
 *
 * @author Matilde
 */
public class LoomData extends JPanel {
    private JLabel number;
    private JTextArea textNumber;
    private JLabel itemCode;
    private JTextArea textItemCode;
    private JLabel speed;
    private JTextArea textSpeed;
    private JLabel surrender;
    private JTextArea textSurrender;
    private JLabel totalMeters;
    private JTextArea textTotalMeters;
    private JLabel metersToGo;
    private JTextArea textMetersToGo;
    private JLabel metersRun;
    private JTextArea textMetersRun;
    private JLabel startDate;
    private JTextArea textStartDate;
    private JLabel expectedEndDate;
    private JTextArea textExpectedEndDate;
    private JButton button;
    private JButton delete;
    private int gbcY;
    Loom loom;
    private int metersGBCX;
    private int metersGBCY;
    
    
    public LoomData(Loom loom, JScrollPane scroll){
        this.loom = loom;
        FormUtility.setScroll(scroll);
        
        this.button = new JButton("add meters");
        this.delete = new JButton("delete");
        
        this.number = new Label("number:", 300);
        this.textNumber = new NonEditableTextArea(Integer.toString(this.loom.getNumber()));
        this.itemCode = new Label("item code:", 300);
        this.textItemCode = new NonEditableTextArea(this.loom.getItemCode());
        this.speed = new Label("number:", 300);
        this.textSpeed = new NonEditableTextArea(Integer.toString(this.loom.getSpeed()));
        this.surrender = new Label("surrender:", 300);
        this.textSurrender = new NonEditableTextArea(Integer.toString(this.loom.getSurrender()));
        this.totalMeters = new Label("total meters:", 300);
        this.textTotalMeters = new NonEditableTextArea(Integer.toString(this.loom.getTotalMeters()));
        this.metersToGo = new Label("meters to go:", 300);
        this.textMetersToGo = new NonEditableTextArea(Integer.toString(this.loom.getMetersToGo()));
        this.metersRun = new Label("meters run:", 300);
        this.textMetersRun = new JTextArea(Integer.toString(this.loom.getMetersRun()));
        this.startDate = new Label("start date:", 300);
        this.textStartDate = new NonEditableTextArea((this.loom.getStartDate().toString()));
        this.expectedEndDate = new Label("expected end date:", 300);
        this.textExpectedEndDate = new NonEditableTextArea(this.loom.getExpectedEndDate().toString());
        
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;


        gbc.gridx = 0;
        gbc.gridy = 0;
        add(number, gbc);

        gbc.gridy++;
        add(itemCode, gbc);

        gbc.gridy++;
        add(speed, gbc);

        gbc.gridy++;
        add(surrender, gbc);
        
        gbc.gridy++;
        add(totalMeters, gbc);

        gbc.gridy++;
        add(metersToGo, gbc);
        
        gbc.gridy++;
        add(metersRun, gbc);
        
        gbc.gridy++;
        add(startDate, gbc);
        
        gbc.gridy++;
        add(expectedEndDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(this.textNumber, gbc);

        gbc.gridy++;
        add(this.textItemCode, gbc);

        gbc.gridy++;
        add(this.textSpeed, gbc);

        gbc.gridy++;
        add(this.textSurrender, gbc);
        
        gbc.gridy++;
        add(this.textTotalMeters, gbc);

        gbc.gridy++;
        this.metersGBCX = gbc.gridx;
        this.metersGBCY = gbc.gridy;
        add(this.textMetersToGo, gbc);
        
        gbc.gridy++;
        add(this.textMetersRun, gbc);
        
        gbc.gridx++;
        add(button, gbc);
        gbc.gridx--;
        
        gbc.gridy++;
        add(this.textStartDate, gbc);
        
        gbc.gridy++;
        add(this.textExpectedEndDate, gbc);
        
        this.gbcY = gbc.gridy;
        
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(this.delete, gbc);
        
    }
    
    public JButton getButton(){
        return this.button;
    }
    
    public JTextArea getTextMetersRun(){
        return this.textMetersRun;
    }
    
    public int getGBCY(){
        return this.gbcY;
    }
    
    public void updateMeters(){
        remove(this.textMetersToGo);
        remove(this.textMetersRun);
        this.textMetersToGo = new NonEditableTextArea(Integer.toString(loom.getMetersToGo()));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = this.metersGBCX;
        gbc.gridy = this.metersGBCY;
        add(this.textMetersToGo, gbc);
        gbc.gridy++;
        this.textMetersRun = new NonEditableTextArea(Integer.toString(0));
        add(this.textMetersRun, gbc);
    }
    
    public JButton getDeleteButton(){
        return this.delete;
    }
}
