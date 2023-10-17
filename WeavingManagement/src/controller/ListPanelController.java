/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.LoomDAOImpl;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import model.Model;
import view.ListPanel;
import view.MainView;

/**
 *
 * @author Matilde
 */
public class ListPanelController {
    private MainView view;
    private Model model;
    private ListPanel panel;
    
    public ListPanelController(MainView view, Model model, LoomDAOImpl ldb){
        Model.setLoomList(ldb.getLoomList());
        this.view = view;
        this.model = model;
        this.panel = new ListPanel(Model.getLoomList());
    }
    
    public void handleAction(){
        this.view.getCentralPanel().removeAll();
        this.view.getCentralPanel().add(this.panel);
        this.view.getCentralPanel().revalidate();
        this.view.getCentralPanel().repaint();
        
        this.panel.getPrintButton().addActionListener(e->{
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new PrintHandler(this.panel.getDataToPrint()));
            boolean doPrint = job.printDialog();
            if (doPrint) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        });

        
    }
}
