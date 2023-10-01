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
import view.dataFactory.DataPanel;
import view.panelFactory.Panel;

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
                view.getLeftPanel().addBackButton();
               
                view.getDataPanel().getAddMetersRunButton().addActionListener(e1 ->{
                    String number = button.getText();
                    Model.getLoom(number).updateBecauseMetersRun(view.getDataPanel().getMetersRun());
                    db.updateMetersToGo(Model.getLoom(number));
                    view.getDataPanel().adjournMetersToGo(Model.getLoom(number).getMetersToGo());
                    
                });
                
                view.getDataPanel().getDeleteButton().addActionListener(e2 ->{
                    view.getLeftPanel().restore();
                    db.removeLoom(Model.getLoom(button.getText()));
                    Model.removeLoom(Integer.parseInt(button.getText()));
                    view.remove(view.getDataPanel());
                    view.getDataPanel().setVisible(false);
                    view.getPanel().removeIconButton(button.getText());
                    view.getPanel().setVisible(true);
                    view.getPanel().getScrollPane().setVisible(true);
                    view.getCentralPanel().add(view.getPanel());
                });
            });
        }
    }
    
    static void iconListener(MainView view, ClientDAOImpl cdb){
        
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("client", button.getText());
                view.getLeftPanel().addBackButton();
                    
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
                
                view.getDataPanel().getDeleteButton().addActionListener(e2 ->{
                    view.getLeftPanel().restore();
                    cdb.removeClient(Model.getClient(button.getText()));
                    Model.removeClient(button.getText());
                    view.remove(view.getDataPanel());
                    view.getDataPanel().setVisible(false);
                    view.getPanel().removeIconButton(button.getText());
                    view.getPanel().setVisible(true);
                    view.getPanel().getScrollPane().setVisible(true);
                    view.getCentralPanel().add(view.getPanel());
                });
                
                
            });
        }
    }
    
    static void iconListener(MainView view, ItemDAOImpl idb){
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("item", button.getText());
                view.getLeftPanel().addBackButton();
                
                view.getDataPanel().getDeleteButton().addActionListener(e2 ->{
                    view.getLeftPanel().restore();
                    idb.removeItem(Model.getItem(button.getText()));
                    Model.removeItem(button.getText());
                    view.remove(view.getDataPanel());
                    view.getDataPanel().setVisible(false);
                    view.getPanel().removeIconButton(button.getText());
                    view.getPanel().setVisible(true);
                    view.getPanel().getScrollPane().setVisible(true);
                    view.getCentralPanel().add(view.getPanel());
                });
            });
        }
    }
    
    
    
}
