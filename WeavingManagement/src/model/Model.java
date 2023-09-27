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
public class Model {
    private static List<Loom> loomList = new ArrayList<>();
    private static List<Item> itemList = new ArrayList<>();
    private static List<Client> clientList = new ArrayList<>();
    
    //Loom
    public static void addLoom(List<String> data){
        Loom loom = new LoomBuilder()
                .setNumber(Integer.parseInt(data.get(0)))
                .setItem(data.get(1))
                .setSpeed(Integer.parseInt(data.get(2)))
                .setStartDate(LocalDate.now())
                .setSurrender(Integer.parseInt(data.get(3)))
                .setTotalMeters(Integer.parseInt(data.get(4)))
                .setMetersToGo(Integer.parseInt(data.get(4)))
                .build();
        
        loomList.add(loom);
    }
    
    public static void removeLoom(Loom loom){
        int s=0;
        for(int i=0; i < loomList.size(); i++){
            if(loomList.get(i).getNumber() == loom.getNumber()){
                s = i;
            }
        }
        loomList.remove(s);
    }
    
    public static List<Loom> getLoomList(){
        return loomList;
    }
    
    public static void setLoomList(List<Loom> loomList){
        Model.loomList = loomList;
    }
    
    //Item
    public static void addItem(List<String> data){
        Item item = new ItemBuilder()
                .setName(data.get(0))
                .setMeters(Integer.parseInt(data.get(1)))
                .setMetersToGo(Integer.parseInt(data.get(1)))
                .setDisponibility(Integer.parseInt(data.get(1)))
                .setRowNumber(Integer.parseInt(data.get(2)))
                .setHits(Integer.parseInt(data.get(3)))
                .setDeliveryDate(data.get(4))
                .setExpectedEndDate("")
                .setClient(data.get(5))
                .build();
        
        itemList.add(item);
    }
    
    public static void removeItem(Item item){
        int s=0;
        for(int i=0; i < itemList.size(); i++){
            if(itemList.get(i).getName().equals(item.getName())){
                s = i;
            }
        }
        itemList.remove(s);
    }
    
    public static List<Item> getItemList(){
        return itemList;
    }
    
    public static void setItemList(List<Item> itemList){
        Model.itemList = itemList;
    }
    
    //Client
    public static void addClient(Client client){
        clientList.add(client);
    }
    
    public static void removeClient(Client client){
        int s=0;
        for(int i=0; i < clientList.size(); i++){
            if(clientList.get(i).getName().equals(client.getName())){
                s = i;
            }
        }
        clientList.remove(s);
    }
    
    public static List<Client> getClientList(){
        return clientList;
    }
    
    public static void setClientList(List<Client> clientList){
        Model.clientList = clientList;
    }
}
