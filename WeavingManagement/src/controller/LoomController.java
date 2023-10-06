/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.MainView;
import model.Model;
import SQLite.*;
       

/**
 *
 * @author Matilde
 */
public class LoomController {
    private MainView view;
    private Model model;
    private LoomDAOImpl db;
    private ItemDAOImpl idb;
    
    
    public LoomController(MainView view, Model model, LoomDAOImpl db, ItemDAOImpl idb){
        Model.setLoomList(db.getLoomList());
        this.view = view;
        this.model = model;
        this.db = db;
        this.idb = idb;
    }
    
    public void handleAction(){
         view.addPanel("loom");
         
            view.getPanel().getPlusButton().addActionListener(e0 -> {
                view.getPanel().setVisible(false);
                view.getPanel().getScrollPane().setVisible(false);
                view.addForm("loom");
                view.getLeftPanel().addBackButton();

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
                        view.getPanel().getScrollPane().setVisible(true);
                        view.getLeftPanel().restore();
                    }
                    ControllerUtility.iconListener(view, db);
                });

            });

            ControllerUtility.iconListener(view, db);
    }
}