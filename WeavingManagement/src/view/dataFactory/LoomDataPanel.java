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
public class LoomDataPanel extends DataPanel {
    List<Label> label = new ArrayList<>();
    List<JTextArea> text = new ArrayList<>();
    
    public LoomDataPanel(String number){
        
        this.label.add(new Label("number:", 150));
        this.label.add(new Label("item name:", 150));
        this.label.add(new Label("speed:", 150));
        this.label.add(new Label("surrender:", 150));
        this.label.add(new Label("total meters:", 150));
        this.label.add(new Label("meters to go:", 150));
        this.label.add(new Label("meters run:", 150));
        this.label.add(new Label("start date:", 150));
        this.label.add(new Label("expected end date:", 150));
        
        this.text.add(new NonEditableTextArea(Integer.toString(Model.getLoom(number).getNumber())));
        this.text.add(new NonEditableTextArea(Model.getLoom(number).getItem().getName()));
        this.text.add(new NonEditableTextArea(Integer.toString(Model.getLoom(number).getSpeed())));
        this.text.add(new NonEditableTextArea(Integer.toString(Model.getLoom(number).getSurrender())));
        this.text.add(new NonEditableTextArea(Integer.toString(Model.getLoom(number).getTotalMeters())));
        this.text.add(new NonEditableTextArea(Integer.toString(Model.getLoom(number).getMetersToGo())));
        this.text.add(new JTextArea(Integer.toString(Model.getLoom(number).getMetersRun())));
        this.text.add(new NonEditableTextArea((Model.getLoom(number).getStartDate().toString())));
        this.text.add(new NonEditableTextArea(Model.getLoom(number).getExpectedEndDate().toString()));
        
        createDataPanel(this.label, this.text);
        addMetersRunButton();
        
    }
}
