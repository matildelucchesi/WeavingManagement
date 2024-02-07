/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import model.Client;
import org.junit.Test;
import model.Item;
import model.Loom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Matilde
 */
public class ObserverTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Item item = new Item("item", 10000, 10000, 10000, 450, 450, LocalDate.parse("23/01/2024", formatter), LocalDate.parse("30/12/2023", formatter), new Client("client", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    Loom loom =  new Loom(0, 450, LocalDate.parse("23/12/2023", formatter), 90, 3000, 3000, item, LocalDate.parse("11/11/1111", formatter));
    Loom loom1 =  new Loom(1, 450, LocalDate.parse("23/12/2023", formatter), 90, 7000, 7000, item, LocalDate.parse("11/11/1111", formatter));
                
    @Test
    public void ObserverTest(){
        //ensures that the new loom is added to the item's loomAtWork list
        assertEquals(Integer.valueOf(0), item.getLoomAtWork().get(0));
        
        //ensures that the availability is updated after the addition of a new loom
        assertEquals(Integer.valueOf(item.getMeters() - loom.getTotalMeters()), Integer.valueOf(item.getAvailability()));
        
        //ensures that the loom's meters to go are updated after adding some meters run
        loom.updateBecauseMetersRun(1000);
        assertEquals(Integer.valueOf(loom.getMetersToGo()), Integer.valueOf(loom.getTotalMeters() - 1000));
        //ensures that the item's meters to go are updated after the update of the loom's meters run
        assertEquals(Integer.valueOf(item.getMetersToGo()), Integer.valueOf(item.getMeters() - 1000));
        
        //ensures that an observer is removed correctly
        loom.removeObserver();
        
        int s = 888;
        for(Integer loomNumber : item.getLoomAtWork()){
            if (loomNumber.equals(loom.getNumber())) {
                s = loomNumber;
                break;
            }
        }
        assertEquals(Integer.valueOf(s), Integer.valueOf(888));
    }
    
    
   
}
