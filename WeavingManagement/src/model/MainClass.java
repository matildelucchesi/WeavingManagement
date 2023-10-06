/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import SQLite.ChronologyDB;
import SQLite.ClientDAOImpl;
import SQLite.ItemDAOImpl;
import SQLite.LoomDAOImpl;
import controller.*;
import view.*;

/**
 *
 * @author Matilde
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Model model = new Model();
        MainView view = new MainView();
        LoomDAOImpl ldb = new LoomDAOImpl();
        ChronologyDB c = new ChronologyDB();
        ItemDAOImpl idb = new ItemDAOImpl();
        ClientDAOImpl cdb = new ClientDAOImpl();
       
        AuthenticationController authentication = new AuthenticationController(view, model, idb, ldb, cdb, c);
        authentication.authenticateAndProceed();
        
    }
    
}
