/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.MainView;
import model.Model;
import SQLite.*;
import javax.swing.JButton;
       

/**
 *
 * @author Matilde
 */
public class LoomController {
    
    public LoomController(MainView view, Model model, LoomDAOImpl db, ItemDAOImpl idb){
        Model.setLoomList(db.getLoomList());
        view.addPanel("loom");
        
        view.getPanel().getPlusButton().addActionListener(e0 -> {
            view.addForm("loom");
            
            view.getFormPanel().getSaveButton().addActionListener(e1 ->{
                Model.addLoom(view.getFormPanel().getData());
                db.insertLoom(Model.getLoomList().get(Model.getLoomList().size() - 1));
                view.addPanel("loom");
            });
        });
        
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("loom", button.getText());
            });
        }
        
        
        
    }
}