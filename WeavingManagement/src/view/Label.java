/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Matilde
 */
public class Label extends JLabel{
    public Label(String text, int x){
        setText(text);
        Dimension labelSize = new Dimension(x, 30);
        setPreferredSize(labelSize);
    }
}
