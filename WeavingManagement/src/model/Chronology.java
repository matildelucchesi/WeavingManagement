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
public class Chronology {
    private String itemName;
    private int meters;
    private String clientName;
    private LocalDate endDate;
    private List<Integer> loomAtWork;
    
    public Chronology(String itemName, int meters, String clientName, LocalDate endDate, List<Integer> loomAtWork){
        this.itemName = itemName;
        this.meters = meters;
        this.clientName = clientName;
        this.endDate = endDate;
        this.loomAtWork = loomAtWork;
    }
    
    //getter methods
    public String getItemName(){
        return this.itemName;
    }
    
    public int getMeters(){
        return this.meters;
    }
    
    public String getClientName(){
        return this.clientName;
    }
    
    public LocalDate getEndDate(){
        return this.endDate;
    }
    
    public List<Integer> getLoomAtWork(){
        return this.loomAtWork;
    }
}
