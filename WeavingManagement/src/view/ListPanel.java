/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Loom;
import view.dataFactory.NonEditableTextArea;

/**
 *
 * @author Matilde
 */
public class ListPanel extends JPanel{
    private JScrollPane scrollPane;
    private List<Loom> loomList = new ArrayList<>();
    private List<Label> label = new ArrayList<>();
    private List<List<JTextArea>> text = new ArrayList<>();
    private List<JPanel> contentList = new ArrayList<>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagConstraints principal = new GridBagConstraints();
    private JButton print;
    
    
    public ListPanel(List<Loom> loomList){
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setBackground(Color.WHITE);
        
        setLayout(new GridBagLayout());
        
        this.print = new JButton("print");
        
        this.loomList = loomList;
        
        
        for(int i=0; i < this.loomList.size(); i++){
            this.text.add(new ArrayList<>());
            for(int h = 0; h < 4; h++){
                this.text.get(i).add(new NonEditableTextArea(Integer.toString(this.loomList.get(i).getNumber())));
                this.text.get(i).add(new NonEditableTextArea(this.loomList.get(i).getItem().getName()));
                this.text.get(i).add(new NonEditableTextArea(this.loomList.get(i).getItem().getClient().getName()));
                this.text.get(i).add(new NonEditableTextArea(Integer.toString(this.loomList.get(i).getItem().getRowNumber())));
        
            }
        }
        
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.anchor = GridBagConstraints.CENTER;
        Insets panelInsets = new Insets(10, 10, 10, 10);
        this.principal.gridx = 0;
        this.principal.gridy = 0;
        
        for(int k = 0; k < this.loomList.size(); k++){
            this.contentList.add(new JPanel());
            this.contentList.get(k).setLayout(new GridLayout(1, 8));
            this.contentList.get(k).setBackground(Color.WHITE);
            this.contentList.get(k).setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(panelInsets.top, panelInsets.left, panelInsets.bottom, panelInsets.right)
        ));
            
            this.gbc.gridx = 0;
            this.gbc.gridy = 0;
            this.label = new ArrayList<>();
            label.add(new Label("number:", 150));
                label.add(new Label("item:", 150));
                label.add(new Label("client:", 150));
                label.add(new Label("rows number:", 150));
            for(int s = 0; s < this.label.size(); s++){
                this.contentList.get(k).add(this.label.get(s), this.gbc);
                this.gbc.gridx++;
                this.contentList.get(k).add(this.text.get(k).get(s), this.gbc);
                this.gbc.gridx++;
            }
            
            add(this.contentList.get(k), this.principal);
            this.principal.gridy++;
        }
        
        add(this.print, this.principal);
        
    }
    
    public JButton getPrintButton(){
        return this.print;
    }
    
    public List<List<String>> getDataToPrint(){
        List<List<String>> dataToPrint = new ArrayList<>();
        for(int i = 0; i < this.loomList.size(); i++){
            dataToPrint.add(new ArrayList<>());
        }
        for(int j = 0; j < dataToPrint.size(); j++){
            for(int k = 0; k < this.label.size(); k++){
                dataToPrint.get(j).add(this.label.get(k).getText());
                dataToPrint.get(j).add(this.text.get(j).get(k).getText());
            }
        }
        
        return dataToPrint;
    }
}
