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
    static boolean modify = false;
    
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
    
    static void iconListener(MainView view, ClientDAOImpl cdb){
        
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("client", button.getText());
                    
                view.getDataPanel().getModifyButton().addActionListener(e1 ->{
                        
                    if(!modify){
                        view.getDataPanel().addModifyComponents();
                        modify = true;
                    }else{
                        Model.getClient(button.getText()).update(view.getDataPanel().getReferentsList(), view.getDataPanel().getPhoneList());
                        cdb.changeData(Model.getClient(button.getText()));
                        view.getDataPanel().restoreComponents();
                        modify = false;
                    }
                });
                
                
            });
        }
    }
    
    static void iconListener(MainView view){
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("item", button.getText());
            });
        }
    }
}
