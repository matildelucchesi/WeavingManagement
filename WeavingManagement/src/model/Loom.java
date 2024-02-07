/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Matilde
 */
public final class Loom implements Observable{
    private int number;
    private Item item;
    private int speed; //hits per minute
    private int surrender;
    private int totalMeters;
    private int metersRun;
    private int metersToGo;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate expectedEndDate;
    private Observer observer ;
    
    //constructor
    public Loom(int number, int speed, LocalDate startDate, int surrender, int totalMeters, int metersToGo, Item item, LocalDate expectedEndDate){
       this.number = number;
       this.speed = speed;
       this.startDate = startDate;
       this.surrender = surrender;
       this.totalMeters = totalMeters;
       this.metersToGo = metersToGo;
       this.metersRun = 0;
       this.item = item;
       LocalDate data = LocalDate.of(1111, 11, 11);
       if(expectedEndDate.isEqual(data)){
           this.calculateExpectedEndDate(this.startDate, this.totalMeters);
       }else{
           this.expectedEndDate = expectedEndDate;
       }
       this.addObserver(item);
       this.notifyExpectedEndDate();
       this.notifyLoomAtWork();
       this.notifyAvailability(totalMeters);
    }
    
    public Loom(int speed, int surrender, int totalMeters, Item item){
        this.speed = speed;
        this.surrender = surrender;
        this.totalMeters = totalMeters;
        this.metersToGo = totalMeters;
        this.item = item;
        this.startDate = LocalDate.now();
        this.calculateExpectedEndDate(this.startDate, this.totalMeters);
        this.addObserver(item);
        this.notifyExpectedEndDate();
        this.notifyLoomAtWork();
    }
    
    //setter methods
    public void setItem(Item item){
        this.item = item;
    }
    
    public void setNumber(int number){
        if(number > 0){
            this.number = number;
        }else{
            throw new IllegalArgumentException("number must be strictly major than 0");
        }
    }
    
    public void setSpeed(int speed){
        if(speed > 0){
            this.speed = speed;
        }else{
            throw new IllegalArgumentException("speed must be strictly major than 0");
        }
    }
    
    public void setSurrender(int surrender){
        if(surrender > 0){
            this.surrender = surrender;
        }else{
            throw new IllegalArgumentException("surrender must be strictly major than 0");
        }
    }
    
    public void setMetersRun(int metersRun){
        if(metersRun > 0){
            this.metersRun = metersRun;
        }else{
            throw new IllegalArgumentException("metersRun must be strictly major than 0");
        }
    }
    
    public void setMetersToGo(int metersToGo){
        if(metersToGo > 0){
            this.metersToGo = metersToGo;
        }else{
            throw new IllegalArgumentException("metersToGo must be strictly major than 0");
        }
    }
    
    public void setStartDate(){
        this.startDate = LocalDate.now();
    }
    
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }
    
    public void setExpectedEndDate(LocalDate expectedEndDate){
        this.expectedEndDate = expectedEndDate;
    }
    
    //getter methods
    public Item getItem(){
        return this.item;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public int getSpeed(){
        return this.speed;
    }
    
    public int getSurrender(){
        return this.surrender;
    }
    
    public int getMetersRun(){
        return this.metersRun;
    }
    
    public int getTotalMeters(){
        return this.totalMeters;
    }
    
    public int getMetersToGo(){
        return this.metersToGo;
    }
    
    public LocalDate getStartDate(){
        return this.startDate;
    }
    
    public LocalDate getEndDate(){
        return this.endDate;
    }
    
    public LocalDate getExpectedEndDate(){
        return this.expectedEndDate;
    }
    
    //other methods
    public void calculateExpectedEndDate(LocalDate date, int meters){
        double speedPerDay = this.speed * 60 * 24;
        double days = ((double) this.item.getTotalHits()) * meters / speedPerDay; //days without surrender
        double expectedDays = days * this.surrender / 100;
        this.expectedEndDate = date.plusDays((long) expectedDays);
    }
    
    public void updateBecauseMetersRun(int metersRun){
        this.metersRun = metersRun;
        this.metersToGo = this.metersToGo - this.metersRun;
        this.notifyMetersRun(this.metersRun);
        if(this.metersToGo == 0){
            this.expectedEndDate = LocalDate.now();
        }else{
            this.calculateExpectedEndDate(LocalDate.now(), this.metersToGo);
        }
        this.notifyExpectedEndDate();
        this.metersRun = 0;
    }
    
    //observer
    @Override
    public void addObserver(Observer observer){
        this.observer = observer;
    }
    
    @Override
    public void notifyMetersRun(int metersRun){
        observer.updateMetersToGo(metersRun);
    }
    
    @Override
    public void notifyExpectedEndDate(){
        observer.updateExpectedEndDate(this.expectedEndDate);
    }
    
    @Override
    public void notifyAvailability(int meters){
        observer.updateAvailability(meters);
    }
    
    @Override
    public void notifyLoomAtWork(){
       observer.addLoomAtWork(this.number);
    }
    
    @Override
    public void removeObserver(){
        this.observer.removeLoomAtWork(this.number);
        this.observer = null;
    }
    
}
