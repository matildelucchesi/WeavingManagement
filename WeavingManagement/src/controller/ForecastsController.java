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
    private static ForecastsPanel forecasts;
    static boolean calculate = false;
    
    public ForecastsController(MainView view){
        this.view = view;
        forecasts = new ForecastsPanel();
    }
    
    public void handleAction(){
        this.view.getCentralPanel().removeAll();
        this.view.getCentralPanel().add(forecasts);
        this.view.getCentralPanel().revalidate();
        this.view.getCentralPanel().repaint();
        
        forecasts.getAddButton().addActionListener(e0 ->{
            forecasts.addLoomFields();
        });
        
        forecasts.getCalculateButton().addActionListener(e1 ->{
            if(!forecasts.controlErrors()){
                if(!calculate){
                    Item item = Model.getForecastsItem(forecasts.getItemData());
                    Model.addForecasts(new Forecasts(Model.getForecastsList().size(), item, Model.getForecastsLoom(forecasts.getLoomData(), item)));
                    System.out.print(Model.getForecastsList().get(0).getLoomList().get(0).getMetersToGo());
                    forecasts.seeResult(Model.getForecastsList().get(Model.getForecastsList().size() - 1).getExpectedEndDate().toString());
                    calculate = true;
                }else{
                    forecasts.restore();
                    calculate = false;
                }
            }
         });
    }
        
         
    
}
