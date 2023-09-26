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
    void insertClient(String name, List<String> referents, List<String> phoneNumber);
    List<Client> getClientList();
    void changeData(String name, List<String> referents, List<String> phone);
    void removeClient(String name);
}
