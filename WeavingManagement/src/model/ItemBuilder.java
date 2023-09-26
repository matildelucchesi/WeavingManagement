/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.List;

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
    
    public ItemBuilder setName(String name){
        this.name = name;
        return this;
    }
    
    public ItemBuilder setMeters(int meters){
        this.meters = meters;
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
    
    public ItemBuilder setClient(String clientName, List<Client> clientList){
        if(!clientList.isEmpty()){
            for(int i=0; i < clientList.size(); i++){
                if(clientList.get(i).getName().equals(clientName)){
                    this.client = clientList.get(i);
                }
                else{
                    throw new IllegalArgumentException("item must be created");
                }
            }
        }else{
            throw new IllegalArgumentException("item must be created");
        }
        return this;
    }
   
    public Item build(){
        return new Item(this.name, this.meters, this.rowNumber, this.hits, this.deliveryDate, this.client);
    }
}
