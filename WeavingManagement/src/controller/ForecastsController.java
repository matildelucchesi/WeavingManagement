/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Forecasts;
import model.Item;
import model.Model;
import view.ForecastsPanel;
import view.MainView;

/**
 *
 * @author Matilde
 */
public class ForecastsController {
    private MainView view;
    private ForecastsPanel forecasts;
    static boolean calculate = false;
    
    public ForecastsController(MainView view){
        this.view = view;
    }
    
    public void handleAction(){
        this.view.getCentralPanel().removeAll();
        this.forecasts = new ForecastsPanel();
        this.view.getCentralPanel().add(this.forecasts);
        this.view.getCentralPanel().revalidate();
        this.view.getCentralPanel().repaint();
        
        this.forecasts.getAddButton().addActionListener(e0 ->{
            this.forecasts.addLoomFields();
        });
        
        this.forecasts.getCalculateButton().addActionListener(e1 ->{
            if(!this.forecasts.controlErrors()){
                if(!calculate){
                    Item item = Model.getForecastsItem(this.forecasts.getItemData());
                    Model.addForecasts(new Forecasts(item, Model.getForecastsLoom(this.forecasts.getLoomData(), item)));
                    this.forecasts.seeResult(Model.getForecastsList().get(Model.getForecastsList().size() - 1).getExpectedEndDate().toString());
                    calculate = true;
                }else{
                    this.forecasts.restore();
                calculate = false;
                }
            }
         });
    }
        
         
    
}
