/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private int availability;
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
    
    public ItemBuilder setDeliveryDate(String deliveryDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.deliveryDate = LocalDate.parse(deliveryDate, formatter);
        return this;
    }
    
    public ItemBuilder setExpectedEndDate(String expectedEndDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(expectedEndDate.isEmpty()){
            this.expectedEndDate = LocalDate.now();
        }else{
          this.expectedEndDate = LocalDate.parse(expectedEndDate, formatter);  
        }
        
        return this;
    }
    
    public ItemBuilder setAvailability(int availability){
        this.availability = availability;
        return this;
    }
   
    
    public ItemBuilder setClient(String clientName){
        if(!Model.getClientList().isEmpty()){
            for(int i=0; i < Model.getClientList().size(); i++){
                if(Model.getClientList().get(i).getName().equals(clientName)){
                    this.client = Model.getClientList().get(i);
                }
            }
            if(this.client == null){
                this.client = new Client(clientName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                Model.getClientList().add(this.client);
            }
        }else{
            this.client = new Client(clientName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            Model.getClientList().add(this.client);
        }
        return this;
    }
   
    public Item build(){
        System.out.print(this.client);
        return new Item(this.name, this.meters, this.metersToGo, this.availability, this.rowNumber, this.hits, this.deliveryDate, this.expectedEndDate, this.client);
    }
}
