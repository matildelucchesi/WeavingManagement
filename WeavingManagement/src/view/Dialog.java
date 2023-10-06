/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

/**
 *
 * @author Matilde
 */
public class Dialog {
    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showDialog(String message){
        JOptionPane.showMessageDialog(null, message, "Dialog", JOptionPane.INFORMATION_MESSAGE);
    }
}
