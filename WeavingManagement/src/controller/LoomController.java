/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Loom;
import view.MainView;
import model.Model;
import SQLite.*;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import view.LoomData;
import view.panelFactory.PanelFactory;
       

/**
 *
 * @author Matilde
 */
public class LoomController {
    
    static boolean isLoomPanelVisible;
    private Loom l;
    private JPanel loomPanel;
    private LoomData data;
    static boolean isDataPanelVisible;
    private List<Loom> loomList;
    private PanelFactory factory ;
    
    public LoomController(MainView view, Model model, LoomDAOImpl db, ItemDAOImpl idb){
        Model.setLoomList(db.getLoomList());
        view.addPanel("loom");
        
        view.getPanel().getPlusButton().addActionListener(e0 -> {
            view.addForm("loom");
            
            view.getFormPanel().getSaveButton().addActionListener(e1 ->{
                Model.addLoom(view.getFormPanel().getData());
                db.insertLoom(Model.getLoomList().get(Model.getLoomList().size() - 1));
            });
        });
        
    }
}