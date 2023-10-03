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
public class ChronologyDataPanel extends DataPanel{
    private List<Label> label = new ArrayList<>();
    List<JTextArea> text = new ArrayList<>();
    
    
    public ChronologyDataPanel(String buttonText){
        this.label.add(new Label("name:", 150));
        this.label.add(new Label("meters:", 150));
        this.label.add(new Label("client:", 150));
        this.label.add(new Label("end date:", 150));
        this.label.add(new Label("loom at work:", 150));
        
        
        this.text.add(new NonEditableTextArea(Model.getChronology(buttonText).getItemName()));
        this.text.add(new NonEditableTextArea(Integer.toString(Model.getChronology(buttonText).getMeters())));
        this.text.add(new NonEditableTextArea(Model.getChronology(buttonText).getClientName()));
        this.text.add(new NonEditableTextArea(Model.getChronology(buttonText).getEndDate().toString()));
        
        StringBuilder stringBuilder = new StringBuilder("[");
    
            for (int i = 0; i < Model.getChronology(buttonText).getLoomAtWork().size(); i++) {
                stringBuilder.append(Model.getChronology(buttonText).getLoomAtWork().get(i));
                if (i < Model.getChronology(buttonText).getLoomAtWork().size() - 1) {
                    stringBuilder.append(", ");
                }
            }
    
            stringBuilder.append("]");
        
        this.text.add(new NonEditableTextArea(stringBuilder.toString()));
        
        createDataPanel(this.label, this.text);
    }
}
