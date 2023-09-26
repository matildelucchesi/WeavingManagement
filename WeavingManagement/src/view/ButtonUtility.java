/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Matilde
 */
public class ButtonUtility {
    static JButton createButton(String label, String iconPath){
        JButton button = new JButton();
        
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
       
        button.setIcon(new ImageIcon(scaledIcon));
        button.setSize(64, 64);
        button.setText(label);
        button.setBorder(null);
        button.setContentAreaFilled(false);
        
        return button;
    }
    
    static ImageIcon changeIconColor(Image image){
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        
        // Cambiare il colore dell'immagine
        Color newColor = Color.BLUE; // Cambia il colore desiderato
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int rgba = bufferedImage.getRGB(x, y);
                Color color = new Color(rgba, true);
                if (color.getAlpha() > 0) {
                    bufferedImage.setRGB(x, y, newColor.getRGB());
                }
            }
        }

        ImageIcon coloredIcon = new ImageIcon(bufferedImage);
        return coloredIcon;
    }
}

        /*
        QUESTO VA SCRITTO IN LEFTPANELCONTROLLER
this.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                    button.setIcon(changeIconColor(scaledIcon));
                    }

            @Override
            public void mouseExited(MouseEvent e) {
            button.setIcon(new ImageIcon(scaledIcon));
*/