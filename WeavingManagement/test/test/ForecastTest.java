/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Forecasts;
import model.Item;
import model.Model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import view.ForecastsPanel;

/**
 *
 * @author Matilde
 */
public class ForecastTest {
    private ForecastsPanel panel;
    private Forecasts f;
    private List<List<Integer>> list = new ArrayList<>();
    
    @Before
    public void setUp(){
        panel = mock(ForecastsPanel.class);  
    }
    
    @Test
    public void forecastTest(){
        when(panel.getItemData()).thenReturn(Arrays.asList(5000, 450, 450));
        Item item = Model.getForecastsItem(panel.getItemData());
        for(int i = 0; i < 4; i ++){
            list.add(new ArrayList<>());
            list.get(i).add(450);
            list.get(i).add(90); 
            list.get(i).add(1250);
        }
        when(panel.getLoomData()).thenReturn(list);
        f = new Forecasts(item, Model.getForecastsLoom(panel.getLoomData(), item));
        assertTrue(!f.getLoomList().isEmpty());
        assertEquals(f.getLoomList().size(), 4);
        assertEquals(f.getExpectedEndDate(), item.getExpectedEndDate());
    }
}
