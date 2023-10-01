/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.formFactory;

import javax.swing.JScrollPane;

/**
 *
 * @author Matilde
 */
public class FormPanelFactory {
    
    public FormPanel createFormPanel(String type){
        if(type.equals("loom")){
            return new LoomFormPanel();
        }
        if(type.equals("item")){
            return new ItemFormPanel();
        }
        if(type.equals("client")){
            return new ClientFormPanel();
        }
        
        return null;
    }
}
