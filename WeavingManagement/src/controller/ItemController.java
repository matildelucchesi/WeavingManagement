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
    private MainView view;
    private Model model;
    private ClientDAOImpl cdb;
    private ItemDAOImpl idb;
    
    public ItemController(MainView view, Model model, ItemDAOImpl idb, ClientDAOImpl cdb){
        Model.setItemList(idb.getItemList());
         
        this.view = view;
        this.model = model;
        this.idb = idb;
        this.cdb = cdb;
    }
    
    public void handleAction(){
        view.addPanel("item");
            view.getPanel().setVisible(true);
            
            view.getPanel().getPlusButton().addActionListener(e1 ->{
                view.getPanel().setVisible(false);
                view.getPanel().getScrollPane().setVisible(false);
                view.addForm("item");
                view.getLeftPanel().addBackButton();
                
                view.getFormPanel().getSaveButton().addActionListener(e2 ->{
                    if(!view.getFormPanel().controlErrors()){
                        Model.addItem(view.getFormPanel().getData());
                        idb.insertItem(Model.getItemList().get(Model.getItemList().size() - 1), cdb);
                        view.remove(view.getFormPanel());
                        view.getFormPanel().setVisible(false);
                        view.getPanel().addIconButton();
                        view.getPanel().setVisible(true);
                        view.getPanel().getScrollPane().setVisible(true);
                        view.getLeftPanel().restore();
                        ControllerUtility.iconListener(view, idb);
                    }
                });
            });
            ControllerUtility.iconListener(view, idb);
    }
        
}