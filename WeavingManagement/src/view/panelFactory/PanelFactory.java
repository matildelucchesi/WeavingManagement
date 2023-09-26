/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.panelFactory;

/**
 *
 * @author Matilde
 */
public class PanelFactory {
    public Panel createPanel(String type){
        if(type.equals("item")){
            return new ItemPanel();
        }
        if(type.equals("loom")){
            return new LoomPanel();
        }
        if(type.equals("client")){
            return new ClientPanel();
        }
        
        return null;
    }
}
