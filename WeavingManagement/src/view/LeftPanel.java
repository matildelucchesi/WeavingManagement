/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Matilde
 */
public class LeftPanel extends JPanel{
    private JButton backButton = ButtonFactory.createBackButton();
    private JButton loomButton = new JButton("Loom");
    private JButton itemButton = new JButton("Item");
    private JButton clientButton = new JButton("Client");
    private JButton chronologyButton = new JButton("Chronology");
    private JButton forecastsButton = new JButton("Forecasts");
    List<JButton> saveLeftButton = new ArrayList<>();
    
   public LeftPanel(JFrame frame) {
        setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));
        setBackground(Color.BLUE);
    }
    
    public void addBackButton(){
        removeAll();
        
        add(Box.createVerticalStrut(10));
        this.backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(this.backButton);
        
        revalidate();
        repaint();
    }
    
    public void restore(){
        removeAll();
        
        add(Box.createVerticalStrut(20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        for (Component button : this.saveLeftButton) {
            add(button);
            add(Box.createVerticalStrut(10));

        }
        
        revalidate();
        repaint();
    }
    
    public JButton getBackButton(){
        return this.backButton;
    }
    
    public JButton getItemButton(){
        return this.itemButton;
    }
    
    public JButton getClientButton(){
        return this.clientButton;
    }
    
    public JButton getChronologyButton(){
        return this.chronologyButton;
    }
    
    public JButton getForecastsButton(){
        return this.forecastsButton;
    }
    
    public JButton getLoomButton(){
        return this.loomButton;
    }
    
    public void seeComponents(){
        this.itemButton.setSize(30, 20);
        this.clientButton.setSize(30, 20);
        
        add(Box.createVerticalStrut(20));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.loomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(this.loomButton);

        add(Box.createVerticalStrut(10));
        
        this.itemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(this.itemButton);

        add(Box.createVerticalStrut(10));

        this.clientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(this.clientButton);
        
        add(Box.createVerticalStrut(10));
        this.chronologyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(this.chronologyButton);
        
        add(Box.createVerticalStrut(10));
        this.forecastsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(this.forecastsButton);
        
        this.saveLeftButton.add(this.loomButton);
        this.saveLeftButton.add(this.itemButton);
        this.saveLeftButton.add(this.clientButton);
        this.saveLeftButton.add(this.chronologyButton);
        this.saveLeftButton.add(this.forecastsButton);
        
        revalidate();
        repaint();
    }
}
