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
            view.getPanel().setVisible(false);
            view.addForm("loom");
            
            view.getFormPanel().getSaveButton().addActionListener(e1 ->{
                if(!view.getFormPanel().controlErrors()){
                    Model.addLoom(view.getFormPanel().getData());
                    Model.getLoomList().get(Model.getLoomList().size() - 1).notifyDisponibility(Model.getLoomList().get(Model.getLoomList().size() - 1).getTotalMeters());
                    db.insertLoom(Model.getLoomList().get(Model.getLoomList().size() - 1));
                    idb.updateExpectedEndDate(Model.getLoomList().get(Model.getLoomList().size() - 1).getItem());
                    idb.updateDisponibility(Model.getLoomList().get(Model.getLoomList().size() - 1).getItem(), Model.getLoomList().get(Model.getLoomList().size() - 1).getTotalMeters());
                    view.remove(view.getFormPanel());
                    view.getFormPanel().setVisible(false);
                    view.getPanel().addIconButton();
                    view.getPanel().setVisible(true);
                }
                
                ControllerUtility.iconListener(view, db);
            });
            
        });
        
        ControllerUtility.iconListener(view, db);
    }
}