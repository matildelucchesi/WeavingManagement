/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.util.List;
import model.*;

/**
 *
 * @author Matilde
 */
public class MainForm extends JFrame {
    private JButton button = new JButton("Button");
    private JPanel centralPanel;
    //private List<LoomButton> lbuttonList = new ArrayList<>();
    //List<Component> componentsToRemove = Arrays.asList(view.getCentralPanel().getComponents());
    List<JButton> saveButton = new ArrayList<>();

    
    public void MainForm(){}
    
    public void initialize(List<Loom> loomList){
        JFrame frame = new JFrame();
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        frame.setMinimumSize(new Dimension(450, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));
        leftPanel.setBackground(Color.BLUE);
        
        this.centralPanel = new JPanel();
        this.centralPanel.setLayout(new BoxLayout(this.centralPanel, BoxLayout.Y_AXIS));
        this.centralPanel.setBackground(Color.WHITE);
        
       /* ImageIcon icon = new ImageIcon("././icon/plus.png");
        Image scaledIcon = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
       
        this.button.setIcon(new ImageIcon(scaledIcon));
        this.button.setSize(64, 64);
        this.button.setText("");
        this.button.setBorder(null);
        this.button.setContentAreaFilled(false);
        
        this.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                    button.setIcon(changeIconColor(scaledIcon));
                    }

            @Override
            public void mouseExited(MouseEvent e) {
            button.setIcon(new ImageIcon(scaledIcon));
            
        });
        */
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));
        rightPanel.setBackground(Color.BLUE);
        
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centralPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        frame.add(mainPanel);
        frame.setVisible(true);
        
    }
    
    /*public ImageIcon changeIconColor(Image image){
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
        
    public JButton getButton(){
        return this.button;
    }
    
    public JPanel getCentralPanel(){
        return this.centralPanel;
    }
  
    public void changeButtonPosition(int loomListSize, int loomCode){
        for (Component loom : this.saveButton) {
                    this.centralPanel.add(loom);
        }
       
        this.centralPanel.remove(this.button);
        LoomButton lbutton = new LoomButton(loomCode);
        centralPanel.add(lbutton);
        
        
        centralPanel.add(this.button);
        
        int emptyCells = 5 * 5 - loomListSize;
        for (int i = 0; i < emptyCells; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            centralPanel.add(panel); 
        }

        centralPanel.revalidate();
        centralPanel.repaint();
    }
   
    public void saveComponents(JButton button){
       this.saveButton = Arrays.asList(button);
    }*/
}
 
