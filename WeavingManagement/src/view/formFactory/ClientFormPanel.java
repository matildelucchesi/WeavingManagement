/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.formFactory;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import view.Label;
import view.TextField;

/**
 *
 * @author Matilde
 */
public class ClientFormPanel extends FormPanel{
    private List<Label> label = new ArrayList<>();
    private List<TextField> text = new ArrayList<>();
    private JButton addRef;
    private JButton addPhone;
    
    public ClientFormPanel(){
        this.label.add(new Label("name:", 150));
        this.label.add(new Label("referent:", 150));
        this.label.add(new Label("phone number:", 150));
        
        createFormPanel(this.label, "client");
     
    }
}
