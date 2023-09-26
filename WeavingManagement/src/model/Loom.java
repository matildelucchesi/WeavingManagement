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
    private List<Observer> observer = new ArrayList<>();
    
    //constructor
    public Loom(int number, int speed, int surrender, int totalMeters, Item item){
       this.number = number;
       this.speed = speed;
       this.surrender = surrender;
       this.totalMeters = totalMeters;
       this.metersToGo = totalMeters;
       this.startDate = LocalDate.now();
       this.item = item;
       this.calculateExpectedEndDate();
       this.addObserver(item);
       this.notifyExpectedEndDate();
       this.notifyDisponibility(totalMeters);
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
    
    public int getMetersToGo(){
        return this.metersToGo;
    }
    
    public LocalDate getStartDate(){
        return this.startDate;
    }
    
    public LocalDate getEndDate(){
        return this.endDate;
    }
    
    //other methods
    public void calculateExpectedEndDate(){
        int speedPerDay = this.speed * 60 * 24;
        double days = this.item.getTotalHits() / speedPerDay; //days without surrender
        double expectedDays = days * this.surrender / 100;
        this.expectedEndDate = this.startDate.plusDays((long) expectedDays);
    }
    
    //observer
    @Override
    public void addObserver(Observer observer){
        this.observer.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer){
        int s=0;
        for(int i=0; i < this.observer.size(); i++){
            if(this.observer.get(i).equals(observer)){
                s = i;
            }
        }
        this.observer.remove(s);
    }
    
    @Override
    public void notifyMetersRun(int metersRun){
        for (Observer observer : this.observer) {
            observer.updateMetersToGo(metersRun);
        }
    }
    
    @Override
    public void notifyExpectedEndDate(){
        for (Observer observer : this.observer) {
            observer.updateExpectedEndDate(this.expectedEndDate);
        }
    }
    
    @Override
    public void notifyDisponibility(int meters){
        for (Observer observer : this.observer) {
            observer.updateDisponibility(meters);
        }
    }
    
}
