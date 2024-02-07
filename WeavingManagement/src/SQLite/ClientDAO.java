/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.util.List;
import model.Client;

/**
 *
 * @author Matilde
 */
public interface ClientDAO {
    boolean insertClient(Client client);
    List<Client> getClientList();
    boolean changeData(Client client, List<String> referents, List<String> phone);
    boolean removeClient(Client client);
    int countClientWithName(String name);
    boolean changeData(Client cient);
}
