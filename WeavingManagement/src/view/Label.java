/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Matilde
 */
public class Label extends JLabel{
    public Label(String text){
        setText(text);
        Dimension labelSize = new Dimension(450, 30);
        setPreferredSize(labelSize);
        int labelMargin = 300;
        setBorder(new EmptyBorder(0, labelMargin, 0, 0));
    }
}
