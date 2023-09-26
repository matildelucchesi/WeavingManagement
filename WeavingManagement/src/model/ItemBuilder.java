/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Matilde
 */
public class ItemBuilder {
    private String name;
    private int meters;
    private int rowNumber;
    private int hits;
    private LocalDate deliveryDate;
    private Client client;
    private LocalDate expectedEndDate;
    private int disponibility;
    private int metersToGo;
    
    public ItemBuilder setName(String name){
        this.name = name;
        return this;
    }
    
    public ItemBuilder setMeters(int meters){
        this.meters = meters;
        return this;
    }
    
     public ItemBuilder setMetersToGo(int meters){
        this.metersToGo = meters;
        return this;
    }
    
    public ItemBuilder setRowNumber(int rowNumber){
        this.rowNumber = rowNumber;
        return this;
    }
    
    public ItemBuilder setHits(int hits){
        this.hits = hits;
        return this;
        
    }
    
    public ItemBuilder setDeliveryDate(LocalDate deliveryDate){
        this.deliveryDate = deliveryDate;
        return this;
    }
    
    public ItemBuilder setExpectedEndDate(LocalDate expectedEndDate){
        this.expectedEndDate = expectedEndDate;
        return this;
    }
    
    public ItemBuilder setDisponibility(int disponibility){
        this.disponibility = disponibility;
        return this;
    }
   
    
    public ItemBuilder setClient(String clientName){
        if(!Model.getClientList().isEmpty()){
            for(int i=0; i < Model.getClientList().size(); i++){
                if(Model.getClientList().get(i).getName().equals(clientName)){
                    this.client = Model.getClientList().get(i);
                }
            }
        }else{
            this.client = new Client(clientName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            Model.getClientList().add(this.client);
        }
        return this;
    }
   
    public Item build(){
        return new Item(this.name, this.meters, this.metersToGo, this.disponibility, this.rowNumber, this.hits, this.deliveryDate, this.expectedEndDate, this.client);
    }
}
