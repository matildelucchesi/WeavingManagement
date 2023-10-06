/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.UsersDB;
import view.Dialog;
import view.MainView;
import view.SettingsPanel;

/**
 *
 * @author Matilde
 */
public class SettingsController {
    private MainView view;
    private SettingsPanel panel;
    private UsersDB u;
    
    public SettingsController(MainView view, UsersDB u){
        this.view = view;
        this.panel = new SettingsPanel();
        this.u = u;
    }
    
    public void handleAction(){
        this.view.getCentralPanel().removeAll();
        this.view.getCentralPanel().add(this.panel);
        this.view.getCentralPanel().revalidate();
        this.view.getCentralPanel().repaint();
        
        this.panel.getChangePasswordButton().addActionListener(e1->{
            this.panel.changePassword();
            
            this.panel.getSave1Button().addActionListener(e11->{
                if(u.changePassword(this.panel.getData1())){
                    Dialog.showDialog("Success");
                    this.panel.restore();
                }else{
                    Dialog.showErrorDialog("ERROR: wrong old password");
                }
            });
            
            this.panel.getBackButton().addActionListener(e12->{
                this.panel.restore();
            });
        });
        
        this.panel.getAddUserButton().addActionListener(e2->{
            this.panel.addUser();
            
            this.panel.getSave2Button().addActionListener(e21->{
                u.addUser(this.panel.getData2());
                this.panel.restore();
            });
            
            this.panel.getBackButton().addActionListener(e12->{
                this.panel.restore();
            });
        });
    }
}
