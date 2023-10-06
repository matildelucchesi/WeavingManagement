/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.MainView;

/**
 *
 * @author Matilde
 */
public class LeftPanelController {
    private LoomController loom;
    private ItemController item;
    private ClientController client;
    private ForecastsController forecasts;
    
    public LeftPanelController(MainView view, LoomController loom, ItemController item, ClientController client, ChronologyController chronology, ForecastsController forecasts, SettingsController settings){
        
        
        view.getLeftPanel().getLoomButton().addActionListener(e0 -> loom.handleAction());
        view.getLeftPanel().getItemButton().addActionListener(e1 -> item.handleAction());
        view.getLeftPanel().getClientButton().addActionListener(e2 -> client.handleAction());
        view.getLeftPanel().getChronologyButton().addActionListener(e3 -> chronology.handleAction());
        view.getLeftPanel().getForecastsButton().addActionListener(e4 -> forecasts.handleAction());
        view.getLeftPanel().getSettingsButton().addActionListener(e5 -> settings.handleAction());
        
       
        view.getLeftPanel().getBackButton().addActionListener(e->{
            view.getLeftPanel().restore();
            if(view.getDataPanel() != null){
                view.remove(view.getDataPanel());
                view.getDataPanel().setVisible(false);
            }
            if(view.getFormPanel() != null){
                view.remove(view.getFormPanel());
                view.getFormPanel().setVisible(false);
            }
            view.getPanel().setVisible(true);
            view.getPanel().restore();
            view.getCentralPanel().add(view.getPanel());
        });
    }
}
