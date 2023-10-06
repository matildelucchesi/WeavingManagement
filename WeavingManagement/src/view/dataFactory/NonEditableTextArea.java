/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dataFactory;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;

/**
 *
 * @author Matilde
 */
public class NonEditableTextArea extends JTextArea{
    public NonEditableTextArea(String text){
        setEditable(false);
        setBackground(Color.WHITE);
        Dimension labelSize = new Dimension(150, 20);
        setPreferredSize(labelSize);
        setText(text);
    }
}
