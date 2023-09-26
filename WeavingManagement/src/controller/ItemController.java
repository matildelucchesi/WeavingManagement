/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.AssociationDB;
import SQLite.ItemDAOImpl;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import model.Item;
import model.Model;
import view.ErrorDialog;
import view.ItemData;
import view.ItemForm;
import view.ItemPanel;
import view.MainForm;

/**
 *
 * @author Matilde
 */
public class ItemController {
    
    private AssociationDB listGetter = new AssociationDB();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static boolean isItemFormVisible = false;
    static boolean isItemPanelVisible = false;
    private ItemPanel itemPanel;
    private ItemForm itemForm;
    private Item item;
    private ItemData itemData;
    static boolean isItemDataPanelVisible = false;
    
    public ItemController(MainForm view, Model model, ItemDAOImpl db){
        
        view.getLeftPanel().getItemButton().addActionListener(e -> {
            itemPanel = new ItemPanel(db.getItemList());
            itemPanel.setSize(view.getCentralPanel().getSize());
            itemPanel.setVisible(true);
            
            view.getCentralPanel().removeAll();
            view.getCentralPanel().add(itemPanel, BorderLayout.CENTER);
            view.getScroll().setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
            ItemController.isItemPanelVisible = true;
            view.getCentralPanel().revalidate();
            view.getCentralPanel().repaint();
            view.getLeftPanel().addBackButton();
            
            itemPanel.getButton().addActionListener(e1 -> {
                
                itemForm = new ItemForm(view.getScroll());
                itemForm.setSize(view.getCentralPanel().getSize());
                itemForm.setVisible(true);
            
                view.getCentralPanel().removeAll();
                view.getCentralPanel().add(itemForm);
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
                ItemController.isItemFormVisible = true;
                ItemController.isItemPanelVisible  = false;

                itemForm.getSaveButton().addActionListener(e2 -> {
                    
                    if(this.controlErrors(db) == false){
                        
                        Item item = new Item(
                        itemForm.getTName().getText(),
                        itemForm.getTCode().getText(),
                        Integer.parseInt(itemForm.getTMeters().getText()),
                        Integer.parseInt(itemForm.getTMeters().getText()),
                        Integer.parseInt(itemForm.getTMeters().getText()),
                        Integer.parseInt(itemForm.getTRowNumber().getText()),
                        Integer.parseInt(itemForm.getTHits().getText()),
                        itemForm.getTClient().getText(),
                        LocalDate.parse(itemForm.getTDeliveryDate().getText(), this.formatter)
                        );
                
                        model.addItem(item);
                    
                        db.insertItem(
                        model.getItem().getName(),
                        model.getItem().getCode(),
                        model.getItem().getMeters(),
                        model.getItem().getMetersToGo(),
                        model.getItem().getDisponibility(),
                        model.getItem().getRowNumber(),
                        model.getItem().getHits(), 
                        model.getItem().getClient(),
                        model.getItem().getDeliveryDate().toString(),
                        model.getItem().getExpectedEndDate().toString()
                        );
                        
                        view.getCentralPanel().remove(itemForm);
                        itemForm.setVisible(false);
                        itemPanel.changeButtonPosition(model.getItemList().size(), model.getItem().getName());
                        
                        itemPanel.getItemButtonList().get(itemPanel.getItemButtonList().size() - 1).addActionListener(e3 ->{
                            this.item = db.getItemList().get(db.getItemList().size() - 1);

                            itemData = new ItemData(this.item, view.getScroll());
                            itemData.setSize(view.getCentralPanel().getSize());
                            itemData.update();
                            itemData.setVisible(true);
                            ItemController.isItemDataPanelVisible = true;
                            ItemController.isItemPanelVisible = false;
                            view.getCentralPanel().removeAll();
                            view.getCentralPanel().add(itemData);
                            view.getCentralPanel().revalidate();
                            view.getCentralPanel().repaint();
                            view.getLeftPanel().addBackButton();
                            view.getLeftPanel().revalidate();
                            view.getLeftPanel().repaint();
                            
                            itemData.getDelete().addActionListener(e5 ->{
                                db.removeItem(this.item.getCode());
                                model.removeItem(this.item.getCode());
                                itemPanel.removeItemButton(this.item.getCode());
                                itemPanel.restore();
                                view.getCentralPanel().remove(this.itemData);
                                ItemController.isItemDataPanelVisible = false;
                                view.getCentralPanel().add(this.itemPanel);
                                ItemController.isItemPanelVisible = true;
                                view.getCentralPanel().revalidate();
                                view.getCentralPanel().repaint();
                            });
                        });
                    
                        view.getCentralPanel().add(itemPanel);
                        view.getCentralPanel().revalidate();
                        view.getCentralPanel().repaint();
                        ItemController.isItemFormVisible = false;
                        ItemController.isItemDataPanelVisible = false;
                        ItemController.isItemPanelVisible = true;
                        }
                });
              
            });
            
            for (JButton button : itemPanel.getItemButtonList()) {
            button.addActionListener(e4 -> {
                for(int k = 0; k < db.getItemList().size(); k++){
                        if(button.getText().equals(db.getItemList().get(k).getName())){
                            this.item = db.getItemList().get(k);
                            break;
                        }
                    }
                
                itemData = new ItemData(this.item, view.getScroll());
                this.item.updateExpectedEndDate();
                itemData.update();
                itemData.setSize(view.getCentralPanel().getSize());
                itemData.setVisible(true);
                view.getCentralPanel().removeAll();
                view.getCentralPanel().add(itemData);
                ItemController.isItemDataPanelVisible = true;
                ItemController.isItemFormVisible = false;
                ItemController.isItemPanelVisible = false;
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
                view.getLeftPanel().addBackButton();
                view.getLeftPanel().revalidate();
                view.getLeftPanel().repaint();
                
                itemData.getDelete().addActionListener(e5 ->{
                            db.removeItem(this.item.getCode());
                            model.removeItem(this.item.getCode());
                            itemPanel.removeItemButton(this.item.getCode());
                            view.getCentralPanel().remove(this.itemData);
                            ItemController.isItemDataPanelVisible = false;
                            view.getCentralPanel().add(this.itemPanel);
                            ItemController.isItemPanelVisible = true;
                            view.getCentralPanel().revalidate();
                            view.getCentralPanel().repaint();
                });
            });
        }
        
        view.getLeftPanel().getBackButton().addActionListener(e1 -> {
            if(ItemController.isItemFormVisible){
                view.getCentralPanel().remove(itemForm);
                itemForm.setVisible(false);
                ItemController.isItemFormVisible = false;
                view.getCentralPanel().add(itemPanel);
                ItemController.isItemPanelVisible = true;
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
                
            else if (ItemController.isItemPanelVisible){
                view.getCentralPanel().remove(itemPanel);
                ItemController.isItemPanelVisible = false;
                view.restoreCentralPanel();
                view.getLeftPanel().restore();
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
            
            else if(ItemController.isItemDataPanelVisible){
                view.getCentralPanel().remove(this.itemData);
                this.itemData.setVisible(false);
                ItemController.isItemDataPanelVisible = false;
                view.getCentralPanel().add(itemPanel);
                ItemController.isItemPanelVisible = true;
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
        });
            
        });
        
        
    }
    
    public boolean controlErrors(ItemDAOImpl db){
        boolean anyConditionMet = false;
        
        if(itemForm.getTName().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: the name cannot be null");
            anyConditionMet = true;
        }
        
        if(itemForm.getTCode().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: the code cannot be null");
            anyConditionMet = true;
        }else{
            boolean isUnique = true;
            for(int i = 0; i < db.getItemList().size(); i++){
                if(itemForm.getTCode().getText().equals(db.getItemList().get(i).getCode())){
                    isUnique = false;
                }
            }
            if(isUnique == false){
                ErrorDialog.showErrorDialog("ERROR: the code must be unique");
                anyConditionMet = true;
            }
        }
                 
        if(itemForm.getTMeters().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: meters cannot be null");
            anyConditionMet = true;
        }else{
            if(Integer.parseInt(itemForm.getTMeters().getText()) < 0){
                ErrorDialog.showErrorDialog("ERROR: meters cannot be negative");
            anyConditionMet = true;
            }
        }
                    
        if(itemForm.getTRowNumber().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: row number cannot be null");
            anyConditionMet = true;
        }else{
            if(Integer.parseInt(itemForm.getTRowNumber().getText()) < 0){
                ErrorDialog.showErrorDialog("ERROR: row number cannot be negative");
            anyConditionMet = true;
            }
        }
                    
        if(itemForm.getTHits().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: hits cannot be null");
            anyConditionMet = true;
        }else{
            if(Integer.parseInt(itemForm.getTHits().getText()) < 0){
                ErrorDialog.showErrorDialog("ERROR: hit number cannot be negative");
            anyConditionMet = true;
            }
        }
                    
        if(itemForm.getTDeliveryDate().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: delivery date cannot be null");
            anyConditionMet = true;
        }else{
            if(LocalDate.parse(itemForm.getTDeliveryDate().getText(), formatter).isBefore(LocalDate.now())){
                ErrorDialog.showErrorDialog("ERROR: delivery date cannot be before today");
                anyConditionMet = true;
            }
        }
        
        return anyConditionMet;
    }
    
    
}
    

