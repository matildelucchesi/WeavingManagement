/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.UsersDB;
import SQLite.ChronologyDB;
import SQLite.ClientDAOImpl;
import SQLite.ItemDAOImpl;
import SQLite.LoomDAOImpl;
import model.Model;
import view.Authentication;
import view.Dialog;
import view.MainView;


/**
 *
 * @author Matilde
 */
public class AuthenticationController {
    Authentication panel;
    private boolean access  = false;
    private MainView view;
    private Model model;
    private ItemDAOImpl idb; 
    private LoomDAOImpl ldb;
    private ClientDAOImpl cdb;
    private ChronologyDB c;
    private UsersDB u;
    
    public AuthenticationController(MainView view, Model model, ItemDAOImpl idb, LoomDAOImpl ldb, ClientDAOImpl cdb, ChronologyDB c, UsersDB u){
        this.panel = new Authentication();
        this.view = view;
        this.view.getCentralPanel().add(panel);
        this.view.getCentralPanel().revalidate();
        this.view.getCentralPanel().repaint();
        this.model = model;
        this.idb = idb;
        this.cdb = cdb;
        this.ldb = ldb;
        this.c = c;
        this.u = u;
    }
    
    public void authenticateAndProceed(){
        this.panel.getLogInButton().addActionListener(e1 ->{
            if(UsersDB.authenticate(panel.getUsername(), panel.getPassword())){
                this.access = true;
                this.view.getLeftPanel().seeComponents();
                this.view.getCentralPanel().remove(this.panel);
                this.view.getCentralPanel().revalidate();
                this.view.getCentralPanel().repaint();
                
                ChronologyController chronology = new ChronologyController(view, model, c);
                ItemController item = new ItemController(view, model, idb, ldb, cdb, c);
                LoomController loom = new LoomController(view,model, ldb, idb );
                ClientController client = new ClientController(view, model, cdb);
                ForecastsController forecasts = new ForecastsController(view);
                SettingsController settings = new SettingsController(view, u);
                LeftPanelController left = new LeftPanelController(view, loom, item, client, chronology, forecasts, settings);
            }
            else{
                Dialog.showErrorDialog("ERROR: wrong credentials");
                this.access = false;
            }
        });
    }
}
