/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
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
        MainForm view = new MainForm();
        LoomDAOImpl db = new LoomDAOImpl();
        ItemDAOImpl idb = new ItemDAOImpl();
       
        LoomController loom = new LoomController(view,model, db, idb );
        
    }
    
}
