/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.time.LocalDate;
import java.util.List;
import model.Item;
import model.Loom;

/**
 *
 * @author Matilde
 */
public interface ItemDAO {
    void insertItem(String iName, int m , int mtg, int disp, int r, int h, String c, String d, String ed);
    List<Item> getItemList();
    void updateMetersToGo(Item item);
    void updateExpectedEndDate(Item item);
    //void setDisponibility();
    void updateDisponibility(Item item);
}
