/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.formFactory;

import java.util.ArrayList;
import java.util.List;
import view.Label;
import view.TextField;

/**
 *
 * @author Matilde
 */
public class ItemFormPanel extends FormPanel{
    List<Label> label = new ArrayList<>();
    List<TextField> text = new ArrayList<>();
    
    public ItemFormPanel(){
        this.label.add(new Label("name:"));
        this.label.add(new Label("meters:"));
        this.label.add(new Label("rows number:"));
        this.label.add(new Label("hits:"));
        this.label.add(new Label("delivery date:"));
        this.label.add(new Label("client name:"));
        
        for(int i=0; i < this.label.size(); i++){
            this.text.add(new TextField());
        }
        
        createFormPanel(this.label, this.text);
    }
}
