/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.ChronologyDB;
import SQLite.ClientDAOImpl;
import SQLite.ItemDAOImpl;
import SQLite.LoomDAOImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import model.Model;
import view.MainView;

/**
 *
 * @author Matilde
 */
public class ControllerUtility {
    static boolean modify = false;
    
    //for loom controller
    static void iconListener(MainView view, LoomDAOImpl db, ItemDAOImpl idb){
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("loom", button.getText());
                view.getLeftPanel().addBackButton();
               
                view.getDataPanel().getAddMetersRunButton().addActionListener(e1 ->{
                    String number = button.getText();
                    if(view.getDataPanel().getMetersRun() <= Model.getLoom(number).getMetersToGo()){
                    Model.getLoom(number).updateBecauseMetersRun(view.getDataPanel().getMetersRun());
                    db.updateMetersToGo(Model.getLoom(number));
                    if(Model.getLoom(number).getMetersToGo() == 0){
                        view.getPanel().updateLoomIcon(button);
                    }
                    view.getDataPanel().adjournMetersToGo(Model.getLoom(number).getMetersToGo());
                    view.getDataPanel().adjournExpectedEndDate(Model.getLoom(number).getExpectedEndDate());
                    db.updateExpectedEndDate(Model.getLoom(number));
                    idb.updateExpectedEndDate(Model.getLoom(number).getItem());
                    }else{
                       view.getDataPanel().error();
                    }
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
    
    //for client controller
    static void iconListener(MainView view, ClientDAOImpl cdb){
        
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("client", button.getText());
                view.getLeftPanel().addBackButton();
                    
                view.getDataPanel().getModifyButton().addActionListener(e1 ->{
                        
                    if(!modify){
                        view.getDataPanel().addModifyComponents();
                        //view.getDataPanel().addPhoneListener();
                        //view.getDataPanel().addRefListener();
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
                
                view.getDataPanel().getAddPhone().addActionListener(e3->{
                    view.getDataPanel().phoneListener();
                });
                
                view.getDataPanel().getAddRef().addActionListener(e3->{
                    view.getDataPanel().refListener();
                });
                
                
            });
        }
    }
    
    //for item controller
    static void iconListener(MainView view, ItemDAOImpl idb, LoomDAOImpl ldb, ChronologyDB c){
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
                
                view.getDataPanel().getEndButton().addActionListener(e3 ->{
                    Model.addChronology(Model.getItem(button.getText()).getName(), 
                            Model.getItem(button.getText()).getMeters(),
                            Model.getItem(button.getText()).getClient().getName(),
                            LocalDate.now(), 
                            Model.getItem(button.getText()).getLoomAtWork());
                    c.insertElement(Model.getItem(button.getText()));
                    
                    int[] integerString = new int[Model.getItem(button.getText()).getLoomAtWork().size()];
                    List<Integer> loomList = new ArrayList<>();
            
                    for(int i = 0; i < Model.getItem(button.getText()).getLoomAtWork().size(); i++){
                        integerString[i] = Model.getItem(button.getText()).getLoomAtWork().get(i);
                    }
            
                    for(int i=0; i < Model.getItem(button.getText()).getLoomAtWork().size(); i++){
                        ldb.removeLoom(Model.getLoom(integerString[i]));
                        Model.removeLoom(integerString[i]);
                    }
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
    
    static void iconListener(MainView view, ChronologyDB c){
        for(JButton button : view.getPanel().getSaveIconButtonList()){
            button.addActionListener(e ->{
                view.addData("chronology", button.getText());
                view.getLeftPanel().addBackButton();
                
                view.getDataPanel().getDeleteButton().addActionListener(e2 ->{
                    view.getLeftPanel().restore();
                    c.removeChronology(Model.getChronology(button.getText()));
                    Model.removeChronology(button.getText());
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
