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
    boolean insertItem(Item item, ClientDAOImpl cdb);
    List<Item> getItemList();
    boolean updateMetersToGo(Item item);
    boolean updateExpectedEndDate(Item item);
    boolean setAvailability();
    boolean updateAvailability(Item item, int metersUsed);
    boolean removeItem(Item item);
}
