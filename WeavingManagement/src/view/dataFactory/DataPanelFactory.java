/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dataFactory;

/**
 *
 * @author Matilde
 */
public class DataPanelFactory {
    public DataPanel createDataPanel(String type, String buttonText){
        if(type.equals("loom")){
            return new LoomDataPanel(buttonText);
        }
        if(type.equals("item")){
            return new ItemDataPanel(buttonText);
        }
        if(type.equals("client")){
            return new ClientDataPanel(buttonText);
        }
        
        return null;
    }
}
