/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDate;
import view.*;
import model.*;
import SQLite.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Matilde
 */
public class CentralController {
    private MainForm view;
    private Model model;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public CentralController(MainForm view, Model model, LoomDAOImpl db, ItemDAOImpl idb) {
        this.view = view;
        this.model = model;
        
        view.initialize(db.getLoomList(idb.getItemList()));
        view.setVisible(true);
        
        
    }
}
