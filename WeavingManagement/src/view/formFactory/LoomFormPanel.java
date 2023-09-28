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
public class LoomFormPanel extends FormPanel{
    private List<Label> label = new ArrayList<>();
    private List<TextField> text = new ArrayList<>();
    
    public LoomFormPanel(){
        this.label.add(new Label("number:", 150));
        this.label.add(new Label("item name:", 150));
        this.label.add(new Label("speed:", 150));
        this.label.add(new Label("surrender:", 150));
        this.label.add(new Label("total meters:", 150));
        
        createFormPanel(this.label, "loom");
    }
}
