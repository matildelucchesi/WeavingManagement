/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Matilde
 */
public interface Observer {
   public void updateMetersToGo(int meters);
   public void updateExpectedEndDate(LocalDate expectedEndDate);
   public void updateDisponibility(int meters);
}
