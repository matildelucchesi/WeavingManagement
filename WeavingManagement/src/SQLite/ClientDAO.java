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
    void insertClient(Client client);
    List<Client> getClientList();
    void changeData(Client client, List<String> referents, List<String> phone);
    void removeClient(Client client);
}
