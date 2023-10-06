/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.util.List;
import model.Item;

/**
 *
 * @author Matilde
 */
public interface ItemDAO {
    void insertItem(Item item, ClientDAOImpl cdb);
    List<Item> getItemList();
    void updateMetersToGo(Item item);
    void updateExpectedEndDate(Item item);
    void setDisponibility();
    void updateDisponibility(Item item, int metersUsed);
    void removeItem(Item item);
}
