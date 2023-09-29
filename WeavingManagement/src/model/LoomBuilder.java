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
public class LoomBuilder {
    private int number;
    private int speed; //hits per minute
    private int surrender;
    private int metersToGo;
    private int totalMeters;
    private Item item;
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    
    public LoomBuilder setNumber(int number){
        this.number = number;
        return this;
    }
   
    
    public LoomBuilder setSpeed(int speed){
        this.speed = speed;
        return this;
    }
    
    public LoomBuilder setStartDate(LocalDate startDate){
        this.startDate = startDate;
        return this;
    }
    
    public LoomBuilder setSurrender(int surrender){
        this.surrender = surrender;
        return this;
    }
    
    public LoomBuilder setTotalMeters(int totalMeters){
        this.totalMeters = totalMeters;
        return this;
    }
    
    public LoomBuilder setMetersToGo(int metersToGo){
        this.metersToGo = metersToGo;
        return this;
    }
    
    public LoomBuilder setItem(String itemName){
        
        if(!Model.getItemList().isEmpty()){

            for(int i=0; i < Model.getItemList().size(); i++){
                if(Model.getItemList().get(i).getName().equals(itemName)){
                    this.item = Model.getItemList().get(i);
                }
            }
        }else{
            throw new IllegalArgumentException("item must be created");
        }
        return this;
    }
    
    public LoomBuilder setExpectedEndDate(LocalDate expectedEndDate){
        this.expectedEndDate = expectedEndDate;
        return this;
    }
 
    
    public Loom build(){
        return new Loom(this.number, this.speed, this.startDate, this.surrender, this.totalMeters, this.metersToGo, this.item, this.expectedEndDate);
    }

}
