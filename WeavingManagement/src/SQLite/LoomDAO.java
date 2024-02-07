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
    boolean insertLoom(Loom loom);
    boolean removeLoom(Loom loom);
    boolean updateMetersToGo(Loom loom);
    List<Loom> getLoomList();
    boolean updateExpectedEndDate(Loom loom);
}
