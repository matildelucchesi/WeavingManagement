/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.util.List;
import model.Item;
import model.Loom;


/**
 *
 * @author Matilde
 */
public interface LoomDAO {
    void insertLoom(int lcode, int s, String sd, int sr, int totmt, int mt, String ic, String ed);
    void removeLoom(int lcode);
    void updateMetersToGo(Loom loom);
    List<Loom> getLoomList(List<Item> itemList);
}
