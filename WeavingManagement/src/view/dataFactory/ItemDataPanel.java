/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dataFactory;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import model.Model;
import view.Label;

/**
 *
 * @author Matilde
 */
public class ItemDataPanel extends DataPanel{
    List<Label> label = new ArrayList<>();
    List<List<JTextArea>> text = new ArrayList<>();
    
        public ItemDataPanel(String name){
            
            this.label.add(new Label("name:", 150));
            this.label.add(new Label("meters:", 150));
            this.label.add(new Label("meters to go:", 150));
            this.label.add(new Label("rows number:", 150));
            this.label.add(new Label("hits:", 150));
            this.label.add(new Label("total hits:", 150));
            this.label.add(new Label("client:", 150));
            this.label.add(new Label("delivery date:", 150));
            this.label.add(new Label("expected end date:", 150));
            this.label.add(new Label("loom at work:", 150));
            
            for(int i = 0; i < this.label.size(); i++){
                this.text.add(new ArrayList<>());
            }
            
            this.text.get(0).add(new NonEditableTextArea(Model.getItem(name).getName()));
            this.text.get(1).add(new NonEditableTextArea(Integer.toString(Model.getItem(name).getMeters())));
            this.text.get(2).add(new NonEditableTextArea(Integer.toString(Model.getItem(name).getMetersToGo())));
            this.text.get(3).add(new NonEditableTextArea(Integer.toString(Model.getItem(name).getRowNumber())));
            this.text.get(4).add(new NonEditableTextArea(Integer.toString(Model.getItem(name).getHits())));
            this.text.get(5).add(new NonEditableTextArea(Integer.toString(Model.getItem(name).getTotalHits())));
            this.text.get(6).add(new NonEditableTextArea(Model.getItem(name).getClient().getName()));
            this.text.get(7).add(new NonEditableTextArea(Model.getItem(name).getDeliveryDate().toString()));
            this.text.get(8).add(new NonEditableTextArea(Model.getItem(name).getExpectedEndDate().toString()));
            for(int i = 0; i < Model.getItem(name).getLoomAtWork().size(); i++){
                this.text.get(9).add(new NonEditableTextArea(Model.getItem(name).getLoomAtWork().get(i).toString()));
            }
            
            createDataPanelWithList(this.label, this.text);
            
            addEndButton();
        }
        
        
}
