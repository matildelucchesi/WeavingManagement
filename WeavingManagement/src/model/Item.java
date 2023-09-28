/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matilde
 */
public final class Item implements Observer{
    private String name;
    private int rowNumber;
    private int hits; //hits per dm
    private int totalHits; 
    private int meters;
    private int metersToGo;
    private int disponibility;
    private LocalDate deliveryDate;
    private LocalDate expectedEndDate;
    private List<LocalDate> date = new ArrayList<>();
    private Client client;
    
    //constructor
    public Item(String name, int meters, int metersToGo, int disponibility, int rowNumber, int hits, LocalDate deliveryDate, LocalDate expectedEndDate, Client client){
        this.name = name;
        this.meters = meters;
        this.disponibility = disponibility;
        this.metersToGo = metersToGo;
        this.rowNumber = rowNumber;
        this.hits = hits;
        this.deliveryDate = deliveryDate;
        this.calculateTotalHits();
        this.expectedEndDate = expectedEndDate;
        this.client = client;
        this.client.getItem().add(this);
    }
    
    //getter methods
    public String getName(){
        return this.name;
    }
    
    public int getRowNumber(){
        return this.rowNumber;
    }
    
    public int getHits(){
        return this.hits;
    }
    
    public int getTotalHits(){
        return this.totalHits;
    }
    
    public int getMeters(){
        return this.meters;
    }
    
    public int getMetersToGo(){
        return this.metersToGo;
    }
    
    public int getDisponibility(){
        return this.disponibility;
    }
    
    
    public LocalDate getExpectedEndDate(){
        return this.expectedEndDate;
    }
    
    public LocalDate getDeliveryDate(){
        return this.deliveryDate;
    }
    
    public Client getClient(){
        return this.client;
    }
    
    //setter methods
    public void setName(String name){
        if(name != null){
            this.name = name;
        } else {
            throw new IllegalArgumentException("The name of the item cannot be null");
            }
    }
    
    public void setRowNumber(int rowNumber){
        if(rowNumber > 0){
            this.rowNumber = rowNumber;
        } else{
            throw new IllegalArgumentException("The row number must be strictly greater than zero");
        }
    }
    
    public void setHits(int hits){
        if(hits > 0){
            this.hits = hits;
        } else{
            throw new IllegalArgumentException("The hits number must be strictly greater than zero");
        }
    }
    
    public void setTotalHits(int totalHits){
        this.totalHits = totalHits;
    }
    
    public void setMeters(int meters){
        this.meters = meters;
    }
    
    public void setDeliveryDate(LocalDate deliveryDate){
        this.deliveryDate = deliveryDate;
    }
    
    //other methods
    public void calculateTotalHits(){
        this.totalHits = this.hits * 10 * this.meters;
    }
    
    @Override
    public void updateMetersToGo(int meters){
        this.metersToGo = this.metersToGo - meters;
    }
    
    @Override
    public void updateExpectedEndDate(LocalDate expectedEndDate){
        if(!this.date.isEmpty()){
            for(int k = 0; k < this.date.size(); k++){
                if(this.date.get(k).equals(expectedEndDate)){
                    this.date.remove(this.date.get(k));
                }
            }
        
            this.date.add(expectedEndDate);
            LocalDate max = this.expectedEndDate;
            for(int i=0; i< date.size(); i++){
                if(max.isBefore(this.date.get(i))){
                    max = this.date.get(i);
                }
            }
            this.expectedEndDate = max;
        }else{
            this.date.add(expectedEndDate);
            this.expectedEndDate = expectedEndDate;
        }
        
    }
    
    @Override 
    public void updateDisponibility(int meters){
        this.disponibility = this.disponibility - meters;
    }
}

