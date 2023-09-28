/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import view.dataFactory.NonEditableTextArea;
import SQLite.AssociationDB;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Item;
import javax.swing.JTextArea;
import java.util.List;


/**
 *
 * @author Matilde
 */


public class ItemData extends JPanel {
    private JLabel name;
    private JTextArea textName;
    private JLabel code;
    private JTextArea textCode;
    private JLabel meters;
    private JTextArea textMeters;
    private JLabel metersToGo;
    private JTextArea textMetersToGo;
    private JLabel rowNumber;
    private JTextArea textRowNumber;
    private JLabel hits;
    private JTextArea textHits;
    private JLabel totalHits;
    private JTextArea textTotalHits;
    private JLabel client;
    private JTextArea textClient;
    private JLabel creationDate;
    private JTextArea textCreationDate;
    private JLabel deliveryDate;
    private JTextArea textDeliveryDate;
    private JLabel expectedEndDate;
    private JTextArea textExpectedEndDate;
    private JLabel loom;
    private List<JTextArea> stringList = new ArrayList<>();
    private List<String> loomList = new ArrayList<>();
    private AssociationDB listGetter = new AssociationDB();
    private int metersGBCX;
    private int metersGBCY;
    private int endGBCX;
    private int endGBCY;
    private Item item;
    private JButton delete;
    
    
    public ItemData(Item item, JScrollPane scroll){
        this.item = item;
        FormUtility.setScroll(scroll);
        
        this.loomList = listGetter.getAssociationList(item.getCode());
        
        this.name = new Label("name:", 300);
        this.textName = new NonEditableTextArea(item.getName());
        this.code = new Label("code:", 300);
        this.textCode = new NonEditableTextArea(item.getCode());
        this.meters = new Label("meters:", 300);
        this.textMeters = new NonEditableTextArea(Integer.toString(item.getMeters()));
        this.metersToGo = new Label("meters to go:", 300);
        this.textMetersToGo = new NonEditableTextArea(Integer.toString(item.getMetersToGo()));
        this.rowNumber = new Label("row number:", 300);
        this.textRowNumber = new NonEditableTextArea(Integer.toString(item.getRowNumber()));
        this.hits = new Label("hits:", 300);
        this.textHits = new NonEditableTextArea(Integer.toString(item.getHits()));
        this.totalHits = new Label("total hits:", 300);
        this.textTotalHits = new NonEditableTextArea(Integer.toString(item.getTotalHits()));
        this.client = new Label("client:", 300);
        this.textClient = new NonEditableTextArea(item.getClient());
        this.creationDate = new Label("creation date:", 300);
        this.textCreationDate = new NonEditableTextArea((item.getCreationDate().toString()));
        this.deliveryDate = new Label("delivery date:", 300);
        this.textDeliveryDate = new NonEditableTextArea(item.getDeliveryDate().toString());
        this.expectedEndDate = new Label("expected end date:", 300);
        this.textExpectedEndDate = new NonEditableTextArea(item.getExpectedEndDate().toString());
        this.loom = new Label("loom at work:", 300);
        this.delete =  new JButton("delete");
        
        for(int i=0; i < loomList.size(); i++){
            stringList.add(new NonEditableTextArea(loomList.get(i)));
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

        gbc.gridy++;
        add(this.code, gbc);

        gbc.gridy++;
        add(this.meters, gbc);
        
        gbc.gridy++;
        add(this.metersToGo, gbc);

        gbc.gridy++;
        add(this.rowNumber, gbc);

        gbc.gridy++;
        add(this.hits, gbc);
        
        gbc.gridy++;
        add(this.totalHits, gbc);
        
        gbc.gridy++;
        add(this.client, gbc);
        
        gbc.gridy++;
        add(this.creationDate, gbc);
        
        gbc.gridy++;
        add(this.deliveryDate, gbc);
        
        gbc.gridy++;
        add(this.expectedEndDate, gbc);
        
        gbc.gridy++;
        add(this.loom, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(this.textName, gbc);

        gbc.gridy++;
        add(this.textCode, gbc);

        gbc.gridy++;
        add(this.textMeters, gbc);
        
        gbc.gridy++;
        add(this.textMetersToGo, gbc);
        this.metersGBCX = gbc.gridx;
        this.metersGBCY = gbc.gridy;

        gbc.gridy++;
        add(this.textRowNumber, gbc);

        gbc.gridy++;
        add(this.textHits, gbc);
        
        gbc.gridy++;
        add(this.textTotalHits, gbc);
        
        gbc.gridy++;
        add(this.textClient, gbc);
        
        gbc.gridy++;
        add(this.textCreationDate, gbc);
        
        gbc.gridy++;
        add(this.textDeliveryDate, gbc);
        
        gbc.gridy++;
        add(this.textExpectedEndDate, gbc);
        this.endGBCX = gbc.gridx;
        this.endGBCY = gbc.gridy;
        
        gbc.gridy++;
        for(int i=0; i < stringList.size(); i++){
            add(stringList.get(i), gbc);
            gbc.gridy++;
        }
        
        gbc.gridy++;
        add(this.delete, gbc);

    
    }
    
    public void adjournStringList(int number){
        this.stringList.add(new NonEditableTextArea(Integer.toString(number)));
    }

    public void updateMeters(){
        remove(this.textMetersToGo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = this.metersGBCX;
        gbc.gridy = this.metersGBCY;
        add(this.textMetersToGo, gbc);
    }
    
    public void updateExpectedEndDate(){
        remove(this.textExpectedEndDate);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = this.endGBCX;
        gbc.gridy = this.endGBCY;
        this.textExpectedEndDate = new NonEditableTextArea(item.getExpectedEndDate().toString());
        System.out.print(item.getExpectedEndDate().toString());
        add(this.textExpectedEndDate, gbc);
    }
    
    public void update(){
        this.updateMeters();
        this.updateExpectedEndDate();
    }
}
