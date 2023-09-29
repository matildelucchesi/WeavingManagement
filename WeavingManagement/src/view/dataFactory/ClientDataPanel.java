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
public class ClientDataPanel extends DataPanel {
    List<Label> label = new ArrayList<>();
    List<List<JTextArea>> text = new ArrayList<>();
    
    public ClientDataPanel(String name){
        this.label.add(new Label("name:", 150));
        this.label.add(new Label("referent:", 150));
        this.label.add(new Label("phone number:", 150));
        this.label.add(new Label("item:", 150));
        
        for(int i = 0; i < this.label.size(); i++){
                this.text.add(new ArrayList<>());
            }
        
        this.text.get(0).add(new NonEditableTextArea(Model.getClient(name).getName()));
        for(int i = 0; i < Model.getClient(name).getReferents().size(); i++){
                this.text.get(1).add(new NonEditableTextArea(Model.getClient(name).getReferents().get(i)));
            }
        for(int h = 0; h < Model.getClient(name).getPhoneNumber().size(); h++){
                this.text.get(2).add(new NonEditableTextArea(Model.getClient(name).getPhoneNumber().get(h)));
            }
        for(int k = 0; k < Model.getClient(name).getItem().size(); k++){
                this.text.get(3).add(new NonEditableTextArea(Model.getClient(name).getItem().get(k).getName()));
            }
        
        createDataPanelWithList(this.label, this.text);
        addModifyButton();
    }
}
