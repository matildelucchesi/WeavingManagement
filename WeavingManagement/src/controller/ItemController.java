/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.AssociationDB;
import SQLite.ItemDAOImpl;
import SQLite.LoomDAOImpl;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import model.Item;
import model.Model;
import view.ErrorDialog;
import view.ItemData;
import view.MainView;

/**
 *
 * @author Matilde
 */
public class ItemController {
    public ItemController(MainView view, Model model, LoomDAOImpl db, ItemDAOImpl idb){
        Model.setItemList(idb.getItemList());
        view.getLeftPanel().getItemButton().addActionListener(e0 ->{
            view.addPanel("item");
            
            view.getPanel().getPlusButton().addActionListener(e1 ->{
                view.addForm("item");
                
                view.getFormPanel().getSaveButton().addActionListener(e2 ->{
                    Model.addItem(view.getFormPanel().getData());
                    idb.insertItem(Model.getItemList().get(Model.getItemList().size() - 1));
                });
                
            });
        });
        
    }
    
}
    

