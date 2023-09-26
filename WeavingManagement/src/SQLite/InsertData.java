/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Matilde
 */
public class InsertData {
    
    public static void insertLoom(int lcode, int s, String sd, int sr, int mt, String ic) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        int loom_code = lcode;
        int speed = s;
        String start_date = sd;
        int surrender = sr;
        int metersToGo = mt;
        String item_code = ic;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            // insert Query
            String insertQuery = "INSERT INTO Loom (loom_code, speed, start_date, surrender, metersToGo, item_code) VALUES (?, ?, ?, ?, ?, ?)";
     
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, loom_code);
            preparedStatement.setInt(2, speed);
            preparedStatement.setString(3, start_date);
            preparedStatement.setInt(4, surrender);
            preparedStatement.setInt(5, metersToGo);
            preparedStatement.setString(6, item_code);
            
            // Esegui la query di inserimento
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("success");
            } else {
                System.out.println("No data insert");
            }
            
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
