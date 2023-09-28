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
    public DataPanel createDataPanel(String type){
        if(type.equals("loom")){
            return new LoomDataPanel();
        }
        if(type.equals("item")){
            return new ItemDataPanel();
        }
        if(type.equals("client")){
            return new ClientDataPanel();
        }
        
        return null;
    }
}
