/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Chronology;
import model.Item;

/**
 *
 * @author Matilde
 */
public class ChronologyDB {
    public void insertElement(Item item){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssociation = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            // insert Query
            String insertQuery = "INSERT INTO Chronology (item_name, meters, client, endDate, loom) VALUES (?, ?, ?, ?, ?)";
     
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getMeters());
            preparedStatement.setString(3, item.getClient().getName());
            preparedStatement.setString(4, LocalDate.now().toString());
            
        
            preparedStatement.setString(5, DBUtility.stringBuilder(item.getLoomAtWork()));
            
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
    
    public List<Chronology> getChronologyList(){
        List<Chronology> chronology = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);

            String selectQuery = "SELECT * FROM Chronology";
            preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            LocalDate end = LocalDate.now();
            
            while (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                int meters = resultSet.getInt("meters");
                String clientName = resultSet.getString("client");
                String date = resultSet.getString("endDate");
                String loom = resultSet.getString("loom");
                
                SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date endDate = new Date();
                    String formattedEndDate = new String();
            try{
                endDate = originalDateFormat.parse(date);
                SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                formattedEndDate = desiredDateFormat.format(endDate);
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                end =  LocalDate.parse(formattedEndDate, formatter);
                
            }catch(ParseException e){
                System.err.println("Errore durante il parsing della data");
            }   
            
            Chronology c = new Chronology(itemName, meters, clientName, end, DBUtility.convertStringToInteger(loom));
                chronology.add(c);
            }

            resultSet.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
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
        
        return chronology;
    }
    
    public void removeChronology(Chronology chronology){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql0 = "DELETE FROM Chronology WHERE item_name = ?";
            
            preparedStatement = connection.prepareStatement(sql0);
            preparedStatement.setString(1, chronology.getItemName());
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("success deleting in Chronology");
            } else {
                System.out.println("fail deleting in Chronology");
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
