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
public class Client {
    private String name;
    private List<String> referents;
    private List<String> phoneNumber;
    private List<Item> item;
    
    public Client(String name, List<String> referents, List<String> phoneNumber, List<Item> item){
        this.name = name;
        this.referents = referents;
        this.phoneNumber = phoneNumber;
        this.item = item;
    }
    
    //setter methods
    public void setName(String name){
        this.name = name;
    }
    
    public void setReferents(List<String> referents){
        this.referents = referents;
    }
    
    public void setPhoneNumber(List<String> phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void setItem(List<Item> item){
        this.item = item;
    }
    
    //getter methods
    public String getName(){
        return this.name;
    }
    
    public List<String> getReferents(){
        return this.referents;
    }
    
    public List<String> getPhoneNumber(){
        return this.phoneNumber;
    }
    
    public List<Item> getItem(){
        return this.item;
    }
    
    public void update(List<String> referents, List<String> phone){
        this.referents = referents;
        this.phoneNumber = phone;
    }
}
