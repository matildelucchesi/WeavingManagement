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
public class ControllerUtility {
    static void iconListener(MainView view, LoomDAOImpl db){
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("loom", button.getText());
                view.getDataPanel().getAddMetersRunButton().addActionListener(e1 ->{
                    String number = button.getText();
                    Model.getLoom(number).updateBecauseMetersRun(view.getDataPanel().getMetersRun());
                    db.updateMetersToGo(Model.getLoom(number));
                    view.getDataPanel().adjournMetersToGo(Model.getLoom(number).getMetersToGo());
                });
            });
        }
    }
    
    static void iconListener(MainView view, String type){
        
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                if(type.equals("client")){
                    view.addData("client", button.getText());
                    
                    view.getDataPanel().getModifyButton().addActionListener(e1 ->{
                        boolean modify = false;
                        
                        if(!modify){
                            view.getDataPanel().addModifyComponents();
                            modify = true;
                        }else{
                            //in update mettere liste nuove
                            Model.getClient(button.getText()).update());
                            cdb.changeData(this.client.getName(), clientData.getReferentsList(), clientData.getPhoneNumberList());
                                        this.client.setReferents(this.clientData.getReferentsList());
                                        this.client.setPhoneNumber(this.clientData.getPhoneNumberList());
                                        clientData.restoreComponents();
                                        ClientController.modify = false;*/
                                    }
                    });
                }
                if(type.equals("item")){
                    view.addData("item", button.getText());  
                }
                
            });
        }
    }
}
