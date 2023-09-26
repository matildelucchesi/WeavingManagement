/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Loom;
import view.MainForm;
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
    
    public LoomController(MainForm view, Model model, LoomDAOImpl db, ItemDAOImpl idb){
        this.factory = new PanelFactory();
        this.loomPanel = factory.createPanel("loom");
        this.loomPanel.setSize(view.getCentralPanel().getSize());
        ControllerUtility.changePanel(view, this.loomPanel);
    }
}