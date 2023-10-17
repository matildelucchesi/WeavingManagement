/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Matilde
 */
import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.List;


public class PrintHandler implements Printable {
    private List<List<String>> dataToPrint = new ArrayList<>();
    
    public PrintHandler(List<List<String>> dataToPrint){
        this.dataToPrint = dataToPrint;
    }
    
    @Override
public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
    if (pageIndex > 0) {
        return Printable.NO_SUCH_PAGE;
    }
    int x = (int) pf.getImageableX();
    int y = (int) pf.getImageableY();
    int width = (int) pf.getImageableWidth();

    int startY = y + 20;

    int startX = x + 5;  

    for(int i = 0; i < dataToPrint.size(); i++){
        if (i > 0) {
        startY += g.getFontMetrics().getHeight();
        startX = x;
    }
        for (String line : dataToPrint.get(i)) {
        int stringWidth = g.getFontMetrics().stringWidth(line);

        if (startX + stringWidth > x + width) {
            startY += g.getFontMetrics().getHeight();
            startX = x;
        }

        g.drawString(line, startX, startY);
        startX += stringWidth +10;
    }
    }
    

    return Printable.PAGE_EXISTS;
}

}

