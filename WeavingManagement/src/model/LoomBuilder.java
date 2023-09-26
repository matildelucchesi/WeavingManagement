/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
    
    public LoomBuilder setNumber(int number){
        this.number = number;
        return this;
    }
   
    
    public LoomBuilder setSpeed(int speed){
        this.speed = speed;
        return this;
    }
    
    public LoomBuilder setSurrender(int surrender){
        this.surrender = surrender;
        return this;
    }
    
    
    public LoomBuilder setMetersToGo(int metersToGo){
        this.metersToGo = metersToGo;
        return this;
    }
    
    public LoomBuilder setItem(String itemName, List<Item> itemList){
        if(!itemList.isEmpty()){
            for(int i=0; i < itemList.size(); i++){
                if(itemList.get(i).getName().equals(itemName)){
                    this.item = itemList.get(i);
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
 
    
    public Loom build(){
        return new Loom(this.number, this.speed, this.surrender, this.totalMeters, this.item);
    }

}
