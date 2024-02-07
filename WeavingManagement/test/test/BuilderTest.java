/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import model.Client;
import model.Item;
import model.Loom;
import model.LoomBuilder;
import model.Model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import view.MainView;
import view.formFactory.FormPanel;

/**
 *
 * @author Matilde
 */
public class BuilderTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Item item = new Item("item", 10000, 10000, 10000, 450, 450, LocalDate.parse("23/01/2024", formatter), LocalDate.parse("30/12/2023", formatter), new Client("client", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
  
    
    @Test
    public void BuilderTest(){
        
        Loom loom = new LoomBuilder()
                .setNumber(0)
                .setItem(item)
                .setSpeed(450)
                .setStartDate(LocalDate.now())
                .setSurrender(90)
                .setTotalMeters(10000)
                .setMetersToGo(10000)
                .setExpectedEndDate(LocalDate.parse("11/11/1111", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
        
        assertEquals(Integer.valueOf(loom.getNumber()), Integer.valueOf(0));
        assertEquals(loom.getItem(), item);
        assertEquals(Integer.valueOf(loom.getSpeed()), Integer.valueOf(450));
        assertEquals(loom.getStartDate(), LocalDate.now());
        assertEquals(Integer.valueOf(loom.getSurrender()), Integer.valueOf(90));
        assertEquals(Integer.valueOf(loom.getTotalMeters()), Integer.valueOf(10000));
        assertEquals(Integer.valueOf(loom.getMetersToGo()), Integer.valueOf(10000));
        
    }
    
    private MainView view;
    private FormPanel panel;
    
    @Before
    public void setUp(){
        view = mock(MainView.class);
        panel = mock(FormPanel.class);
        
    }
    
    @Test
    public void builderUsingTest(){
        when(view.getFormPanel()).thenReturn(panel);
        when(view.getFormPanel().getData()).thenReturn(Arrays.asList("item", "1000", "450", "450", "11/01/2024", "client"));
        Model.addItem(view.getFormPanel().getData());
        assertFalse(Model.getItemList().isEmpty());
        assertEquals(Model.getItemList().get(Model.getItemList().size() - 1).getName(), "item");
        //adding a loom with item
        when(view.getFormPanel().getData()).thenReturn(Arrays.asList("0", "item", "450", "90", "100"));
        Model.addLoom(view.getFormPanel().getData());
        assertFalse(Model.getLoomList().isEmpty());
        assertEquals(Model.getLoomList().get(Model.getLoomList().size() - 1).getNumber(), 0);
        //ensures that the availability of the item is decreased
        assertEquals(Model.getItem("item").getAvailability(), 900);
        //adding a second loom with item
        when(view.getFormPanel().getData()).thenReturn(Arrays.asList("1", "item", "450", "90", "200"));
        Model.addLoom(view.getFormPanel().getData());
        assertEquals(Model.getLoomList().get(Model.getLoomList().size() - 1).getNumber(), 1);
        //ensures that the availability of the item is decreased
        assertEquals(Model.getItem("item").getAvailability(), 700);
    }
    
    private Item item1;
    private Loom loom2;
    private Loom loom1;
    
    @Before
    public void setUp1(){
        item1 = new Item("item1", 10000, 10000, 10000, 450, 450, LocalDate.parse("23/01/2024", formatter), LocalDate.parse("30/12/2023", formatter), new Client("client", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        loom2 =  new Loom(0, 450, LocalDate.parse("23/12/2023", formatter), 90, 3000, 3000, item1, LocalDate.parse("11/11/1111", formatter));
        loom1 =  new Loom(1, 450, LocalDate.parse("23/12/2023", formatter), 90, 7000, 7000, item1, LocalDate.parse("11/11/1111", formatter));
     
    }
    
    @Test
    public void EndOfWorkTest(){
        loom1.updateBecauseMetersRun(7000);
        assertEquals(item1.getMetersToGo(), 3000);
        assertEquals(loom1.getMetersToGo(), 0);
        Model.removeLoom(loom1.getNumber());
        loom2.updateBecauseMetersRun(3000);
        assertEquals(item1.getMetersToGo(), 0);
        assertEquals(loom2.getMetersToGo(), 0);
        Model.removeLoom(loom1.getNumber());
        Model.addChronology(item1.getName(), item.getMeters(), item.getClient().getName(), item.getExpectedEndDate(), item.getLoomAtWork());
        assertTrue(!Model.getChronologyList().isEmpty());
}
}
