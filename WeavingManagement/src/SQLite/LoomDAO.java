/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.util.List;
import model.Loom;


/**
 *
 * @author Matilde
 */
public interface LoomDAO {
    void insertLoom(Loom loom);
    void removeLoom(Loom loom);
    void updateMetersToGo(Loom loom);
    List<Loom> getLoomList();
}
