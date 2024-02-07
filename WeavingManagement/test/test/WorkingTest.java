/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import SQLite.ChronologyDB;
import SQLite.ClientDAOImpl;
import SQLite.ItemDAOImpl;
import SQLite.LoomDAOImpl;
import SQLite.UsersDB;
import java.util.Arrays;
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
public class WorkingTest {
    private UsersDB u;
    private Model model;
    private MainView view;
    private LoomDAOImpl ldb;
    private ChronologyDB c;
    private ItemDAOImpl idb;
    private ClientDAOImpl cdb;
    private FormPanel panel;
    
    
    @Before
    public void setUp(){
        model = new Model();
        view = mock(MainView.class);
        panel = mock(FormPanel.class);
    }
    
    @Test
    public void workingTest(){
        //adding an item (using Builder)
        when(view.getFormPanel()).thenReturn(panel);
        when(view.getFormPanel().getData()).thenReturn(Arrays.asList("item", "1000", "450", "450", "11/01/2024", "client"));
        Model.addItem(view.getFormPanel().getData());
        assertFalse(Model.getItemList().isEmpty());
        assertEquals(Model.getItemList().get(Model.getItemList().size() - 1).getName(), "item");
        
        //adding a loom with item (using Builder)
        when(view.getFormPanel().getData()).thenReturn(Arrays.asList("0", "item", "450", "90", "500"));
        Model.addLoom(view.getFormPanel().getData());
        assertFalse(Model.getLoomList().isEmpty());
        assertEquals(Model.getLoomList().get(Model.getLoomList().size() - 1).getNumber(), 0);
        
        //ensures that the availability of the item is decreased (using Observer)
        assertEquals(Model.getItem("item").getAvailability(), 500);
        
        //adding a second loom with item (using Builder)
        when(view.getFormPanel().getData()).thenReturn(Arrays.asList("1", "item", "450", "90", "500"));
        Model.addLoom(view.getFormPanel().getData());
        assertEquals(Model.getLoomList().get(Model.getLoomList().size() - 1).getNumber(), 1);
        
        //ensures that the availability of the item is decreased (using Observer)
        assertEquals(Model.getItem("item").getAvailability(), 0);
        
        //adding some meters run for the loom 0
        Model.getLoom(0).updateBecauseMetersRun(250);
        assertEquals(Model.getLoom(0).getMetersToGo(), 250);
        //ensures that metersToGo of the item is decreased (using Observer)
        assertEquals(Model.getItem("item").getMetersToGo(), 750);
        
        //end the work for the loom 1
        Model.getLoom(1).updateBecauseMetersRun(500);
        assertEquals(Model.getLoom(1).getMetersToGo(), 0);
        Model.removeLoom(1);
        assertTrue(!Model.getLoomList().isEmpty());
        assertEquals(Model.getLoomList().size(), 1);
        //ensures that metersToGo of the item is decreased (using Observer)
        assertEquals(Model.getItem("item").getMetersToGo(), 250);
        
        //end the work for loom 0 and then end the item
        Model.getLoom(0).updateBecauseMetersRun(250);
        assertEquals(Model.getLoom(0).getMetersToGo(), 0);
        Model.removeLoom(0);
        assertTrue(Model.getLoomList().isEmpty());
        //ensures that metersToGo of the item is decreased (using Observer)
        assertEquals(Model.getItem("item").getMetersToGo(), 0);
        
        //when the item work is done, it is saved in the chronology and then it is delete
        Model.addChronology(Model.getItem("item").getName(), Model.getItem("item").getMeters(), 
                Model.getItem("item").getClient().getName(), Model.getItem("item").getExpectedEndDate(), 
                Model.getItem("item").getLoomAtWork());
        assertTrue(!Model.getChronologyList().isEmpty());
        Model.removeItem("item");
        assertTrue(Model.getItemList().isEmpty());
    }
    
    
}
