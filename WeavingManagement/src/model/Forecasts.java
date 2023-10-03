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
    
    
    
}
