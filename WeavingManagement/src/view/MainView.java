/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import view.dataFactory.DataPanelFactory;
import view.formFactory.FormPanelFactory;
import view.panelFactory.PanelFactory;

/**
 *
 * @author Matilde
 */
public class MainView extends JFrame {
    private JButton button = new JButton("Button");
    private JPanel centralPanel;
    private LeftPanel leftPanel;
    private view.panelFactory.Panel panel;
    private view.formFactory.FormPanel formPanel;
    private view.dataFactory.DataPanel dataPanel;
    private List<JButton> saveButton = new ArrayList<>();

    
    public MainView(){
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        setMinimumSize(new Dimension(450, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        this.leftPanel = new LeftPanel(this);
        
        this.centralPanel = new JPanel();
        this.centralPanel.setLayout(new BoxLayout(this.centralPanel, BoxLayout.Y_AXIS));
        this.centralPanel.setBackground(Color.WHITE);
        
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(getWidth() / 6, getHeight()));
        rightPanel.setBackground(Color.BLUE);
        
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(this.centralPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        add(mainPanel);
        setVisible(true);
    }  
    
    public JPanel getCentralPanel(){
        return this.centralPanel;
    }
    
    public LeftPanel getLeftPanel(){
        return this.leftPanel;
    }
    
    public void addPanel(String type){
        PanelFactory factory = new PanelFactory();
        this.centralPanel.removeAll();
        
        if(type.equals("loom")){
            this.panel = factory.createPanel("loom");
        }
        if(type.equals("item")){
            this.panel = factory.createPanel("item");
        }
        if(type.equals("client")){
            this.panel = factory.createPanel("client");
        }
        if(type.equals("chronology")){
            this.panel = factory.createPanel("chronology");
        }
        
        this.centralPanel.add(this.panel.getScrollPane());
        this.centralPanel.revalidate();
        this.centralPanel.repaint();
        
    }  
    
    public view.panelFactory.Panel getPanel(){
            return this.panel;
    }
    
    public void addForm(String type){
        FormPanelFactory factory = new FormPanelFactory();
        
        if(type.equals("loom")){
            this.formPanel = factory.createFormPanel("loom");
        }
        if(type.equals("item")){
            this.formPanel = factory.createFormPanel("item");
        }
        if(type.equals("client")){
            this.formPanel = factory.createFormPanel("client");
        }
        
        this.centralPanel.add(this.formPanel);
        this.centralPanel.revalidate();
        this.centralPanel.repaint();
    }
    
    public view.formFactory.FormPanel getFormPanel(){
        return this.formPanel;
    }
    
    public void addData(String type, String buttonText){
        DataPanelFactory factory = new DataPanelFactory();
        this.centralPanel.removeAll();
        
        if(type.equals("loom")){
            this.dataPanel = factory.createDataPanel("loom", buttonText);
        }
        if(type.equals("item")){
            this.dataPanel = factory.createDataPanel("item", buttonText);
        }
        if(type.equals("client")){
            this.dataPanel = factory.createDataPanel("client", buttonText);
        }
        if(type.equals("chronology")){
            this.dataPanel = factory.createDataPanel("chronology", buttonText);
        }
        
        this.centralPanel.add(this.dataPanel);
        this.centralPanel.revalidate();
        this.centralPanel.repaint();
    }
    
    public view.dataFactory.DataPanel getDataPanel(){
        return this.dataPanel;
    }

}
 
