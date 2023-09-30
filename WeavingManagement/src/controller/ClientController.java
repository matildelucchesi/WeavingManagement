/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.ClientDAOImpl;
import javax.swing.JButton;
import model.Model;
import view.MainView;


/**
 *
 * @author Matilde
 */
public class ClientController {
    
    public ClientController(MainView view, Model model, ClientDAOImpl cdb){
        Model.setClientList(cdb.getClientList());
        view.getLeftPanel().getClientButton().addActionListener(e0 ->{
            view.addPanel("client");
            view.getPanel().setVisible(true);
            
            view.getPanel().getPlusButton().addActionListener(e1 ->{
                view.getPanel().setVisible(false);
                view.addForm("client");
                
                view.getFormPanel().getAddPhone().addActionListener(e2 ->{
                    view.getFormPanel().updateField("phone");
                });
                
                view.getFormPanel().getAddRef().addActionListener(e3 ->{
                    view.getFormPanel().updateField("referents");
                });
                
                view.getFormPanel().getSaveButton().addActionListener(e4 ->{
                    if(!view.getFormPanel().controlErrors()){
                        Model.addClient(view.getFormPanel().getData("client"));
                        cdb.insertClient(Model.getClientList().get(Model.getClientList().size() - 1));
                        view.remove(view.getFormPanel());
                        view.getFormPanel().setVisible(false);
                        view.getPanel().addIconButton();
                        view.getPanel().setVisible(true);
                        ControllerUtility.iconListener(view, cdb);
                    }
                });
                
            });
            ControllerUtility.iconListener(view, cdb);
        });
        
        }
}
    
