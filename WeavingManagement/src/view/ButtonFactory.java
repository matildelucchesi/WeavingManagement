/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Matilde
 */
public class ButtonFactory {
    
    
    public static JButton createLoomButton(int loomCode){
        String text = Integer.toString(loomCode);
        String iconPath = "././icon/loom.png";
        return ButtonUtility.createButton(text, iconPath);
    }
    
    public static JButton createItemButton(String itemName){
        JButton item = ButtonUtility.createButton(itemName, "././icon/textile.png");
        return item;
    }
    
    public static JButton createPlusButton(){
        return ButtonUtility.createButton("", "././icon/plus.png");
    }
    
    public static JButton createBackButton(){
        return ButtonUtility.createButton("", "././icon/back.png");
    }
    
    public static JButton createClientButton(String name){
        JButton client = ButtonUtility.createButton(name, "././icon/company.png");
        client.setBorder(new EmptyBorder(0, 15, 0, 0));
        return client;
    }
}
