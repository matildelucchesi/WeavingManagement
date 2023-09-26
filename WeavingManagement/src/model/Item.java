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
public final class Item implements Observer{
    private String name;
    private int rowNumber;
    private int hits; //hits per dm
    private int totalHits; 
    private int meters;
    private int metersToGo;
    private int disponibility;
    private LocalDate creationDate;
    private LocalDate endDate;
    private LocalDate deliveryDate;
    private LocalDate expectedEndDate;
    private List<LocalDate> date;
    
    //constructor
    public Item(String name, int meters, int rowNumber, int hits, LocalDate deliveryDate){
        this.name = name;
        this.meters = meters;
        this.disponibility = meters;
        this.metersToGo = meters;
        this.rowNumber = rowNumber;
        this.hits = hits;
        this.deliveryDate = deliveryDate;
        this.creationDate = LocalDate.now();
        this.calculateTotalHits();
        this.expectedEndDate = LocalDate.now();
        this.endDate = LocalDate.now();
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
    
    public LocalDate getCreationDate(){
        return this.creationDate;
    }
    
    public LocalDate getEndDate(){
        return this.endDate;
    }
    
    public LocalDate getDeliveryDate(){
        return this.deliveryDate;
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
    
    public void setCreationDate(LocalDate creationDate){
        this.creationDate = creationDate;
    }
    
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
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
        this.date.add(expectedEndDate);
        LocalDate max = this.date.get(0);
        for(int i=0; i< date.size(); i++){
            if(max.isBefore(this.date.get(i))){
                max = this.date.get(i);
            }
        }
        this.expectedEndDate = max;
    }
    
    @Override 
    public void updateDisponibility(int meters){
        this.disponibility = this.disponibility - meters;
    }
}

