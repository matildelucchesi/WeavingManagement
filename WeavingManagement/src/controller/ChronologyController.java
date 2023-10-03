/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.ChronologyDB;
import model.Model;
import view.MainView;

/**
 *
 * @author Matilde
 */
public class ChronologyController {
    private MainView view;
    private Model model;
    private ChronologyDB c;
    
    public ChronologyController(MainView view, Model model, ChronologyDB c){
        Model.setChronologyList(c.getChronologyList());
         
        this.view = view;
        this.model = model;
        this.c = c;
    }
    
    public void handleAction(){
        view.addPanel("chronology");
        ControllerUtility.iconListener(view, c);
    }
}
