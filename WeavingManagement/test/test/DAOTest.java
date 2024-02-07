/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import static org.mockito.Mockito.mock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.junit.Test;

import SQLite.ItemDAOImpl;
import SQLite.ClientDAOImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Client;
import model.Item;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DAOTest {
    private ItemDAOImpl db;
    Item item;
    Item item1;
    
    @Before
    public void setUp(){
    db = mock(ItemDAOImpl.class);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    item = new Item("item", 10000, 10000, 10000, 450, 450, LocalDate.parse("20/01/2024", formatter), 
                LocalDate.parse("30/12/2023", formatter), 
                new Client("client", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        
    item1 = new Item("item", 100, 100, 100, 450, 450, LocalDate.parse("20/01/2024", formatter), 
                LocalDate.parse("30/12/2023", formatter), 
                new Client("client", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
    

    @Test
    public void testInsertItem() {
        when(db.insertItem( any(Item.class), any(ClientDAOImpl.class) )).thenReturn(true);
        boolean result = db.insertItem(item , new ClientDAOImpl());
        assertTrue(result);
    }
    
    @Test
    public void getItemList(){ 
        when(db.getItemList()).thenReturn(Arrays.asList(item));
        List<Item> list = db.getItemList();
        assertFalse(list.isEmpty());
    }
    
    @Test
    public void updateItem(){
        when(db.updateAvailability( any(Item.class), any(Integer.class))).thenReturn(true);
        boolean update = db.updateAvailability(item, 10);
        assertTrue(update);
    }
    
    @Test
    public void deleteItem(){
        //if the item is in the database
        when(db.removeItem(item)).thenReturn(true);
        boolean remove = db.removeItem(item);
        assertTrue(remove);
        //if the item isn't in the database
        boolean r = db.removeItem(item1);
        assertFalse(r);
    }
}


