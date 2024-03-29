/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.panelFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import model.Model;
import view.ButtonFactory;
import view.ButtonUtility;

/**
 *
 * @author Matilde
 */
public abstract class Panel extends JPanel {
    private List<JButton> saveIconButton = new ArrayList<>();
    private JButton plusButton;
    private String type;
    private JScrollPane scrollPane;
    
    void createPanel(String layout, String buttonType){
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setBackground(Color.WHITE);
        this.plusButton = ButtonFactory.createPlusButton();
        
        if(layout.equals("grid")){
           setLayout(new GridLayout(0, 5, 90, 90));
        }
        if(layout.equals("flex")){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }
        
        this.type = buttonType;
        if(this.type.equals("item")){
            if(!Model.getItemList().isEmpty()){
                for (int i = 0; i < Model.getItemList().size(); i++) {
                    JButton iButton = ButtonFactory.createItemButton(Model.getItemList().get(i).getName());
                    add(iButton);
                    this.saveIconButton.add(iButton);
                }
           }
            add(this.plusButton);
        }
        if(this.type.equals("loom")){
                if(!Model.getLoomList().isEmpty()){
                    JButton lButton = new JButton();
                    for (int i = 0; i < Model.getLoomList().size(); i++) {
                        if(Model.getLoomList().get(i).getMetersToGo() == 0){
                            lButton = ButtonFactory.createEndLoomButton(Model.getLoomList().get(i).getNumber());
                            add(lButton);
                        }else{
                            lButton = ButtonFactory.createLoomButton(Model.getLoomList().get(i).getNumber());
                            add(lButton);
                        }
                        
                        this.saveIconButton.add(lButton);
                    }
               }
            add(this.plusButton);
        }
        if(this.type.equals("client")){
                if(!Model.getClientList().isEmpty()){
                    for (int i = 0; i < Model.getClientList().size(); i++) {
                        JButton cButton = ButtonFactory.createClientButton(Model.getClientList().get(i).getName());
                        add(cButton);
                        this.saveIconButton.add(cButton);
                    }
               }
            add(this.plusButton);
        }
        
        if(this.type.equals("chronology")){
            if(!Model.getChronologyList().isEmpty()){
                    for (int i = 0; i < Model.getChronologyList().size(); i++) {
                        JButton button = ButtonFactory.createItemButton(Model.getChronologyList().get(i).getItemName());
                        button.setBorder(new EmptyBorder(15, 15, 0, 0));
                        add(button);
                        this.saveIconButton.add(button);
                    }
               }
        }
        
        setVisible(true);
        
        scrollPane.setViewportView(this);
        
        revalidate();
        repaint();
        
    }
    
    public JButton getPlusButton(){
        return this.plusButton;
    }
    
    void updateButton(){
        removeAll();
        
        for (Component item : this.saveIconButton) {
            add(item);
        }

        add(this.plusButton);

        revalidate();
        repaint();
    }
    
    public void addIconButton(){
        if(this.type.equals("item")){
            JButton iButton = ButtonFactory.createItemButton(Model.getItemList().get(Model.getItemList().size() - 1).getName());
            this.saveIconButton.add(iButton);
        }
        if(this.type.equals("loom")){
            JButton lButton = ButtonFactory.createLoomButton(Model.getLoomList().get(Model.getLoomList().size() - 1).getNumber());
            this.saveIconButton.add(lButton);
        }
        if(this.type.equals("client")){
            JButton cButton = ButtonFactory.createClientButton(Model.getClientList().get(Model.getClientList().size() - 1).getName());
            this.saveIconButton.add(cButton);
        }
        
        this.updateButton();
    }
    
    public void restore(){
        removeAll();
        
        for (Component item : this.saveIconButton) {
            add(item);
        }
        add(this.plusButton);
        revalidate();
        repaint();
    }
    
    public List<JButton> getSaveIconButtonList(){
        return this.saveIconButton;
    }
    
    public void removeIconButton(String name){
        int s=0;
        for(int i=0; i < this.saveIconButton.size(); i++){
            if(this.saveIconButton.get(i).getText().equals(name)){
                s = i;
            }
        }
        this.saveIconButton.remove(s);
        this.restore();
        
    }
    
    public void removeIconButton(int number){
        int s=0;
        for(int i=0; i < this.saveIconButton.size(); i++){
            if(this.saveIconButton.get(i).getText().equals(Integer.toString(number))){
                s = i;
            }
        }
        this.saveIconButton.remove(s);
        this.restore();
    }
    
    public JScrollPane getScrollPane(){
        return this.scrollPane;
    }
    
    public void updateLoomIcon(JButton button){
        int s=0;
        for(int i=0; i < this.saveIconButton.size(); i++){
            if(this.saveIconButton.get(i).getText().equals(button.getText())){
                s = i;
            }
        }
        
        button = ButtonUtility.createButton(button.getText(),  "././icon/endloom.png");
        this.saveIconButton.set(s, button);
        
        revalidate();
        repaint();
    }
   
   
}
