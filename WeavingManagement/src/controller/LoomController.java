/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDate;
import model.Loom;
import model.LoomBuilder;
import view.MainForm;
import model.Model;
import view.LoomForm;
import SQLite.*;
import java.lang.reflect.Array;
import java.util.List;
import javax.swing.JButton;
import view.ErrorDialog;
import view.LoomData;
       

/**
 *
 * @author Matilde
 */
public class LoomController {
    
    static boolean isLoomPanelVisible;
    private Loom l;
    private LoomForm loomPanel;
    private LoomData data;
    static boolean isDataPanelVisible;
    private List<Loom> loomList;
    
    public LoomController(MainForm view, Model model, LoomDAOImpl db, ItemDAOImpl idb){
        this.loomList = db.getLoomList();
        
        Model.setLoomList(this.loomList);
        
        LoomController.isLoomPanelVisible = false;
        LoomController.isDataPanelVisible = false;
        
        view.getButton().addActionListener(e -> {
            this.loomPanel = new LoomForm(view.getScroll());
            this.loomPanel.getPanel().setSize(view.getCentralPanel().getSize());
            this.loomPanel.setVisible(true);
            LoomController.isLoomPanelVisible = true;
            
            view.getCentralPanel().removeAll();
            view.getCentralPanel().add(loomPanel);
            view.getCentralPanel().revalidate();
            view.getCentralPanel().repaint();
            view.getLeftPanel().addBackButton();
            
            loomPanel.getButton().addActionListener(e2 -> {
                
                if(this.controlErrors(db, idb) == false){
                    Loom loom = new LoomBuilder()
                            .setNumber(Integer.parseInt(loomPanel.getTNumber().getText()))
                            .setSpeed(Integer.parseInt(loomPanel.getTSpeed().getText()))
                            .setStartDate(LocalDate.now())
                            .setSurrender(Integer.parseInt(loomPanel.getTSurrender().getText()))
                            .setTotalMeters(Integer.parseInt(loomPanel.getTMeters().getText()))
                            .setMetersToGo(Integer.parseInt(loomPanel.getTMeters().getText()))
                            .setItem(loomPanel.getTItemCode().getText())
                            .build();

                    loom.addObserver(loom.getItem());
                    model.addLoom(loom);
                
                    db.insertLoom(
                            model.getLoom().getNumber(),
                            model.getLoom().getSpeed(),
                            model.getLoom().getStartDate().toString(),
                            model.getLoom().getSurrender(),
                            model.getLoom().getTotalMeters(),
                            model.getLoom().getMetersToGo(), 
                            model.getLoom().getItemCode(),
                            model.getLoom().getExpectedEndDate().toString()
                            );
                    
                    loom.modifyItemExpectedEndDate();
                    idb.updateDisponibility(loom.getItem(), loom);
                
                    view.getCentralPanel().remove(loomPanel);
                    LoomController.isLoomPanelVisible = false;
                    view.changeButtonPosition(model.getLoomList().size(), model.getLoom().getNumber());
                    view.getLeftPanel().restore();
                
                    view.getLoomButtonList().get(view.getLoomButtonList().size() - 1).addActionListener(e3 ->{
                        view.getLeftPanel().restore();
                        this.l = this.loomList.get(this.loomList.size() - 1);

                        data = new LoomData(this.l, view.getScroll());
                        data.setSize(view.getCentralPanel().getSize());
                        data.setVisible(true);

                        view.getCentralPanel().removeAll();
                        view.getCentralPanel().add(data);
                        LoomController.isDataPanelVisible = true;
                        view.getCentralPanel().revalidate();
                        view.getCentralPanel().repaint();
                        view.getLeftPanel().addBackButton();
                        view.getLeftPanel().revalidate();
                        view.getLeftPanel().repaint();
                        
                        data.getDeleteButton().addActionListener(e4 ->{
                            
                            db.removeLoom(this.l.getNumber());
                            idb.setDisponibility();
                            model.removeLoom(this.l.getNumber());
                            view.removeLoomButton(this.l.getNumber());
                            view.getCentralPanel().remove(this.data);
                            LoomController.isDataPanelVisible = false;
                            view.restoreCentralPanel();
                            view.getLeftPanel().restore();
                            view.getCentralPanel().revalidate();
                            view.getCentralPanel().repaint();
                        });
                    });
                }
                
            });
            
        });
        
        
        for (JButton button : view.getLoomButtonList()) {
            button.addActionListener(e2 -> {
                    for(int k = 0; k < this.loomList.size(); k++){
                        if(button.getText().equals(Integer.toString(this.loomList.get(k).getNumber()))){
                            this.l = this.loomList.get(k);
                            break;
                        }
                    }
                
                data = new LoomData(this.l, view.getScroll());
                data.setSize(view.getCentralPanel().getSize());
                data.setVisible(true);
            
                view.getCentralPanel().removeAll();
                view.getCentralPanel().add(data);
                LoomController.isDataPanelVisible = true;
                LoomController.isLoomPanelVisible = false;
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
                view.getLeftPanel().addBackButton();
                view.getLeftPanel().revalidate();
                view.getLeftPanel().repaint();
                
                data.getButton().addActionListener(e3 -> {
                    data.getTextMetersRun().setEditable(false);
                    int newMetersRun = Integer.parseInt(data.getTextMetersRun().getText());
                    db.updateMetersToGo(this.l, newMetersRun);
                    data.updateMeters();
                });
                
                data.getDeleteButton().addActionListener(e4 ->{
                        db.removeLoom(this.l.getNumber());
                        idb.setDisponibility();
                        System.out.print("ciao" + this.l.getItem().getDisponibility());
                        view.removeLoomButton(this.l.getNumber());
                        view.getCentralPanel().remove(this.data);
                        LoomController.isDataPanelVisible = false;
                        view.restoreCentralPanel();
                        view.getLeftPanel().restore();
                        view.getCentralPanel().revalidate();
                        view.getCentralPanel().repaint();
                    });
                
            });
        }
        
        view.getLeftPanel().getBackButton().addActionListener(e3 -> {
            if(LoomController.isLoomPanelVisible){
                view.getCentralPanel().remove(this.loomPanel);
                LoomController.isLoomPanelVisible = false;
                view.restoreCentralPanel();
                view.getLeftPanel().restore();
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
            
            else if(LoomController.isDataPanelVisible){
                view.getCentralPanel().remove(this.data);
                LoomController.isDataPanelVisible = false;
                view.restoreCentralPanel();
                view.getLeftPanel().restore();
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
        });

    }
    
    public boolean controlErrors(LoomDAOImpl db, ItemDAOImpl idb){
        boolean anyConditionMet = false;
        
        if(loomPanel.getTNumber().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: the number cannot be null");
            anyConditionMet = true;
        }else{
            boolean isUnique = true;
            for(int i = 0; i < this.loomList.size(); i++){
                
                if(loomPanel.getTNumber().getText().equals(String.valueOf(this.loomList.get(i).getNumber()))){
                    isUnique = false;
                }
            }
            if(isUnique == false){
                ErrorDialog.showErrorDialog("ERROR: the number must be unique");
                anyConditionMet = true;
            }
        }
        
        if(loomPanel.getTSpeed().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: speed cannot be null");
            anyConditionMet = true;
        }else{
            if(Integer.parseInt(loomPanel.getTSpeed().getText()) < 0){
                ErrorDialog.showErrorDialog("ERROR: speed cannot be negative");
            anyConditionMet = true;
            }
        }
        
        if(loomPanel.getTSurrender().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: surrender cannot be null");
            anyConditionMet = true;
        }else{
            if(Integer.parseInt(loomPanel.getTSurrender().getText()) < 0){
                ErrorDialog.showErrorDialog("ERROR: surrender cannot be negative");
                anyConditionMet = true;
            }
        }
        
        boolean exist = false;
        if(loomPanel.getTItemCode().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: item cannot be null");
            anyConditionMet = true;
        }else{
            
            for(int i=0; i < idb.getItemList().size(); i++){
                if(loomPanel.getTItemCode().getText().equals(idb.getItemList().get(i).getCode())){
                    exist = true;
                }
            }
            if(exist == false){
                ErrorDialog.showErrorDialog("ERROR: item must exist");
                anyConditionMet = true;
            }
        }
        
        if(loomPanel.getTMeters().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: meters cannot be null");
            anyConditionMet = true;
        }else{
            if(Integer.parseInt(loomPanel.getTMeters().getText()) < 0){
                ErrorDialog.showErrorDialog("ERROR: meters cannot be negative");
            anyConditionMet = true;
            }else{
                if(exist){
                    int s = 0;
                    for(int k = 0; k < idb.getItemList().size(); k++){
                        if(loomPanel.getTItemCode().getText().equals(idb.getItemList().get(k).getCode())){
                            s = k;
                        }
                    }
                    
                    if(Integer.parseInt(loomPanel.getTMeters().getText()) > idb.getItemList().get(s).getDisponibility()){
                        ErrorDialog.showErrorDialog("ERROR: you only have " + idb.getItemList().get(s).getDisponibility() + " meters available ");
                        anyConditionMet = true;
                    }
                }
                
            }
        }
        
        
        return anyConditionMet;
    }
}
