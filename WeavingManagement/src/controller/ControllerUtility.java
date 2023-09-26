/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JPanel;
import view.MainForm;

/**
 *
 * @author Matilde
 */
public class ControllerUtility {
    static void changePanel(MainForm view, JPanel panel){
        view.getCentralPanel().removeAll();
        view.getCentralPanel().add(panel);
        view.getCentralPanel().revalidate();
        view.getCentralPanel().repaint();
    }
}
