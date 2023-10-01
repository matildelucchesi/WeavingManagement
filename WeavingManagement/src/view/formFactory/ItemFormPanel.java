/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.formFactory;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
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
        this.label.add(new Label("name:", 150));
        this.label.add(new Label("meters:", 150));
        this.label.add(new Label("rows number:", 150));
        this.label.add(new Label("hits:", 150));
        this.label.add(new Label("delivery date:", 150));
        this.label.add(new Label("client name:", 150));
        
        createFormPanel(this.label, "item");
    }
}
