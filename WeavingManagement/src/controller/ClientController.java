/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import SQLite.ClientDAOImpl;
import SQLite.ItemDAOImpl;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import model.Client;
import model.Model;
import view.ClientData;
import view.ClientForm;
import view.ClientPanel;
import view.ErrorDialog;
import view.ItemData;
import view.MainForm;

/**
 *
 * @author Matilde
 */
public class ClientController {
    private ClientForm clientForm;
    private ClientPanel clientPanel;
    private ClientData clientData;
    static boolean isFormVisible;
    static boolean isPanelVisible;
    static boolean isDataVisible;
    static boolean modify;
    private Client client;
    
    public ClientController(MainForm view, Model model, ClientDAOImpl cdb, ItemDAOImpl idb){
        view.getLeftPanel().getClientButton().addActionListener(e -> {
            ClientController.modify = false;
            
            this.clientPanel = new ClientPanel(cdb.getClientList());
            this.clientPanel.setSize(view.getCentralPanel().getSize());
            this.clientPanel.setVisible(true);
            ClientController.isPanelVisible = true;
            
            view.getCentralPanel().removeAll();
            view.getCentralPanel().add(clientPanel, BorderLayout.CENTER);
            view.getScroll().setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            view.getCentralPanel().revalidate();
            view.getCentralPanel().repaint();
            view.getLeftPanel().addBackButton();
            
            clientPanel.getButton().addActionListener(e1 -> {
                
                    this.clientForm = new ClientForm(view.getScroll());
                    this.clientForm.setSize(view.getCentralPanel().getSize());
                    this.clientForm.setVisible(true);

                    view.getCentralPanel().removeAll();
                    view.getCentralPanel().add(this.clientForm);
                    view.getCentralPanel().revalidate();
                    view.getCentralPanel().repaint();
                    ClientController.isFormVisible = true;
                    ClientController.isPanelVisible  = false;

                    this.clientForm.getAddReferentsButton().addActionListener(e2 ->{
                        this.clientForm.addReferentsText();
                    });

                    this.clientForm.getAddPhoneButton().addActionListener(e3 ->{
                        this.clientForm.addPhoneText();
                    });
                
                    this.clientForm.getSaveButton().addActionListener(e4 ->{
                        
                        if(!this.controlErrors()){
                                this.client = new Client(
                                this.clientForm.getTName().getText(),
                                this.clientForm.getReferentsList(),
                                this.clientForm.getPhoneList()
                            );

                            model.addClient(client);

                            cdb.insertClient(
                                    model.getClient().getName(),
                                    model.getClient().getReferents(),
                                    model.getClient().getPhoneNumber()
                            );

                            view.getCentralPanel().remove(this.clientForm);
                            this.clientForm.setVisible(false);
                            this.clientPanel.changeButtonPosition(model.getClientList().size(), model.getClient().getName());
                            view.getCentralPanel().add(this.clientPanel);
                            view.getCentralPanel().revalidate();
                            view.getCentralPanel().repaint();

                            clientPanel.getClientButtonList().get(clientPanel.getClientButtonList().size() - 1).addActionListener(e3 ->{
                                this.client = cdb.getClientList().get(cdb.getClientList().size() - 1);

                                clientData = new ClientData(this.client, view.getScroll());
                                clientData.setSize(view.getCentralPanel().getSize());
                                clientData.setVisible(true);
                                ClientController.isDataVisible = true;
                                ClientController.isPanelVisible = false;
                                view.getCentralPanel().removeAll();
                                view.getCentralPanel().add(clientData);
                                view.getCentralPanel().revalidate();
                                view.getCentralPanel().repaint();
                                view.getLeftPanel().addBackButton();
                                view.getLeftPanel().revalidate();
                                view.getLeftPanel().repaint();
                                
                                clientData.getModify().addActionListener(e5 ->{
                                    if(!ClientController.modify){
                                        clientData.addModifyComponents();
                                        ClientController.modify = true;
                                    }else{
                                        cdb.changeData(this.client.getName(), clientData.getReferentsList(), clientData.getPhoneNumberList());
                                        this.client.setReferents(this.clientData.getReferentsList());
                                        this.client.setPhoneNumber(this.clientData.getPhoneNumberList());
                                        clientData.restoreComponents();
                                        ClientController.modify = false;
                                    }
                                });
                                
                                clientData.getDelete().addActionListener(e5 ->{
                                    cdb.removeClient(this.client.getName());
                                    model.removeClient(this.client.getName());
                                    clientPanel.removeClientButton(this.client.getName());
                                    clientPanel.restore();
                                    view.getCentralPanel().remove(this.clientData);
                                    ClientController.isDataVisible = false;
                                    view.getCentralPanel().add(this.clientPanel);
                                    ClientController.isPanelVisible = true;
                                    view.getCentralPanel().revalidate();
                                    view.getCentralPanel().repaint();
                                });
                            });
                        
                        }
                    });
                    
                    
                
            });
            
            for (JButton button : clientPanel.getClientButtonList()) {
                button.addActionListener(e4 -> {
                for(int k = 0; k < cdb.getClientList().size(); k++){
                        if(button.getText().equals(cdb.getClientList().get(k).getName())){
                            this.client = cdb.getClientList().get(k);
                            break;
                        }
                    }
                
                this.clientData = new ClientData(this.client, view.getScroll());
                this.clientData.setSize(view.getCentralPanel().getSize());
                this.clientData.setVisible(true);
                view.getCentralPanel().removeAll();
                view.getCentralPanel().add(clientData);
                ClientController.isDataVisible = true;
                ClientController.isFormVisible = false;
                ClientController.isPanelVisible = false;
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
                view.getLeftPanel().addBackButton();
                view.getLeftPanel().revalidate();
                view.getLeftPanel().repaint();
                
                clientData.getModify().addActionListener(e5 ->{
                    if(!ClientController.modify){
                        clientData.addModifyComponents();
                        ClientController.modify = true;
                    }else{
                        cdb.changeData(this.client.getName(), clientData.getReferentsList(), clientData.getPhoneNumberList());
                        this.client.setReferents(this.clientData.getReferentsList());
                        this.client.setPhoneNumber(this.clientData.getPhoneNumberList());
                        clientData.restoreComponents();
                        ClientController.modify = false;
                    }
                });
                
                clientData.getDelete().addActionListener(e5 ->{
                    cdb.removeClient(this.client.getName());
                    model.removeClient(this.client.getName());
                    clientPanel.removeClientButton(this.client.getName());
                    clientPanel.restore();
                    view.getCentralPanel().remove(this.clientData);
                    ClientController.isDataVisible = false;
                    view.getCentralPanel().add(this.clientPanel);
                    ClientController.isPanelVisible = true;
                    view.getCentralPanel().revalidate();
                    view.getCentralPanel().repaint();
                });
                
            });
                
            }
            
        });
        
        view.getLeftPanel().getBackButton().addActionListener(e1 -> {
            if(ClientController.isFormVisible){
                view.getCentralPanel().remove(this.clientForm);
                this.clientForm.setVisible(false);
                ClientController.isFormVisible = false;
                view.getCentralPanel().add(clientPanel);
                ClientController.isPanelVisible = true;
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
                
            else if (ClientController.isPanelVisible){
                view.getCentralPanel().remove(this.clientPanel);
                ClientController.isPanelVisible = false;
                view.restoreCentralPanel();
                view.getLeftPanel().restore();
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
            
            else if(ClientController.isDataVisible){
                view.getCentralPanel().remove(this.clientData);
                this.clientData.setVisible(false);
                ClientController.isDataVisible = false;
                view.getCentralPanel().add(this.clientPanel);
                ClientController.isPanelVisible = true;
                view.getCentralPanel().revalidate();
                view.getCentralPanel().repaint();
            }
        });
        
            
        
        
    }
    
    public boolean controlErrors(){
        boolean anyConditionMet = false;
        
        if(clientForm.getTName().getText().equals("")){
            ErrorDialog.showErrorDialog("ERROR: the name cannot be null");
            anyConditionMet = true;
        }
        
        return anyConditionMet;
    }
}
