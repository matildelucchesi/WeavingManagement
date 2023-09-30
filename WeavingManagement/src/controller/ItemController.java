/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.ClientDAOImpl;
import SQLite.ItemDAOImpl;
import SQLite.LoomDAOImpl;
import javax.swing.JButton;
import model.Model;
import view.MainView;

/**
 *
 * @author Matilde
 */
public class ItemController {
    public ItemController(MainView view, Model model, LoomDAOImpl db, ItemDAOImpl idb, ClientDAOImpl cdb){
        Model.setItemList(idb.getItemList());
        System.out.print("size: "+ Model.getItemList().get(0).getName());
        
        view.getLeftPanel().getItemButton().addActionListener(e0 ->{
            view.addPanel("item");
            view.getPanel().setVisible(true);
            
            view.getPanel().getPlusButton().addActionListener(e1 ->{
                view.getPanel().setVisible(false);
                view.addForm("item");
                
                view.getFormPanel().getSaveButton().addActionListener(e2 ->{
                    Model.addItem(view.getFormPanel().getData());
                    idb.insertItem(Model.getItemList().get(Model.getItemList().size() - 1), cdb);
                    view.remove(view.getFormPanel());
                    view.getFormPanel().setVisible(false);
                    view.getPanel().addIconButton();
                    view.getPanel().setVisible(true);
                    ControllerUtility.iconListener(view, idb);
                });
            });
            ControllerUtility.iconListener(view, idb);
        });
    }
        
}