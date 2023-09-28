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
            
            view.getPanel().getPlusButton().addActionListener(e1 ->{
                view.addForm("client");
                
                view.getFormPanel().getAddPhone().addActionListener(e2 ->{
                    view.getFormPanel().updateField("phone");
                });
                
                view.getFormPanel().getAddRef().addActionListener(e3 ->{
                    view.getFormPanel().updateField("referents");
                });
                
                view.getFormPanel().getSaveButton().addActionListener(e4 ->{
                    Model.addClient(view.getFormPanel().getData("client"));
                    cdb.insertClient(Model.getClientList().get(Model.getClientList().size() - 1));
                    view.addPanel("client");
                });
                
            });
            
            for(JButton button : view.getPanel().getSaveIconButtonList()){
                button.addActionListener(e ->{
                    view.addData("client", button.getText());
                });
            }
            
        });
        
        }
}

