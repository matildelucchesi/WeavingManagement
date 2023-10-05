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
public class Forecasts {
    private Item item;
    private List<Loom> loomList = new ArrayList<>();
    private LocalDate expectedEndDate;
    
    public Forecasts(Item item, List<Loom> loomList){
        this.item = item;
        this.loomList = loomList;
        this.expectedEndDate = item.getExpectedEndDate();
    }
    
    public LocalDate getExpectedEndDate(){
        return this.expectedEndDate;
    }
    
    public List<Loom> getLoomList(){
        return this.loomList;
    }
    
    public List<Integer> getItemValues(){
        List<Integer> values = new ArrayList<>();
        values.add(this.item.getMeters());
        values.add(this.item.getRowNumber());
        values.add(this.item.getHits());
        
        return values;
    }
    
    public int getLoomNumber(){
        return this.loomList.size();
    }
    
    public List<Integer> getLoomValues(){
        List<Integer> values = new ArrayList<>();
        for(int i = 0; i < this.loomList.size(); i++){
            values.add(this.loomList.get(i).getSpeed());
            values.add(this.loomList.get(i).getSurrender());
            values.add(this.loomList.get(i).getTotalMeters());
        }
        return values;
    }
    
    
}
