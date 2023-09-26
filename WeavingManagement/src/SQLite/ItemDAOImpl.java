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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.ItemBuilder;
import model.Loom;

/**
 *
 * @author Matilde
 */
public class ItemDAOImpl implements ItemDAO {
    
   @Override
   public void insertItem(String iName, int m, int mtg, int disp, int r, int h, String c, String d, String ed) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssociation = null;
        
        String item_name = iName;
        int meters = m;
        int metersToGo = mtg;
        int disponibility = disp;
        int rowNumber = r;
        int hits = h;
        String client = c;
        String deliveryDate = d;
        String expectedEndDate = ed;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            // insert Query
            String insertQuery = "INSERT INTO Item (item_name, meters, metersToGo, disponibility, rowNumber, hits, client, deliveryDate, expectedEndDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
     
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, item_name);
            preparedStatement.setInt(2, meters);
            preparedStatement.setInt(3, metersToGo);
            preparedStatement.setInt(4, disponibility);
            preparedStatement.setInt(5, rowNumber);
            preparedStatement.setInt(6, hits);
            preparedStatement.setString(7, client);
            preparedStatement.setString(8, deliveryDate);
            preparedStatement.setString(9, expectedEndDate);
            
            // Esegui la query di inserimento
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("success");
            } else {
                System.out.println("No data insert");
            }
            
            String insertAssociationQuery = "INSERT INTO ClientAssociation (client_name, item_name) VALUES (?, ?)";
            preparedStatementAssociation = connection.prepareStatement(insertAssociationQuery);
            preparedStatementAssociation.setString(1, client);
            preparedStatementAssociation.setString(2, item_name); 
            
            rowsAffected = preparedStatement.executeUpdate();
            
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
   
   @Override
   public List<Item> getItemList(){
    List<Item> itemList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);

        String selectQuery = "SELECT * FROM Item";

        preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String itemName = resultSet.getString("item_name");
            int meters = resultSet.getInt("meters");
            int metersToGo = resultSet.getInt("metersToGo");
            int disponibility = resultSet.getInt("disponibility");
            int rowNumber = resultSet.getInt("rowNumber");
            int hits = resultSet.getInt("hits");
            String deliveryDate = resultSet.getString("deliveryDate");
            String expectedEndDate = resultSet.getString("expectedEndDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            Item item = new ItemBuilder()
                    .setName(itemName)
                    .setMeters(meters)
                    .setMetersToGo(metersToGo)
                    .setDisponibility(disponibility)
                    .setRowNumber(rowNumber)
                    .setHits(hits)
                    .setDeliveryDate(LocalDate.parse(deliveryDate, formatter))
                    .setExpectedEndDate(LocalDate.parse(expectedEndDate, formatter))
                    .build();
            
            itemList.add(item);
        }

        resultSet.close();
        connection.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
    }
    return itemList;
    }
   
   @Override
   public void updateMetersToGo(Item item){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        //update Loom's meters to go
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql = "UPDATE Item SET metersToGo = ? WHERE item_name = ?";
            preparedStatement = connection.prepareStatement(sql);
            int newMetersToGo = item.getMetersToGo();
            
            preparedStatement.setInt(1, newMetersToGo);
            preparedStatement.setString(2, item.getName());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il valore di metersRun per il item_name " + item.getName() + " è stato aggiornato con successo.");
            } else {
                System.out.println("Nessuna riga è stata modificata.");
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
   
   @Override
   public void updateExpectedEndDate(Item item){
       Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql = "UPDATE Item SET expectedEndDate = ? WHERE item_name = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, item.getExpectedEndDate().toString());
            preparedStatement.setString(2, item.getName());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il valore di expectedEndDate per il item_name " + item.getName() + " è stato aggiornato con successo.");
            } else {
                System.out.println("Nessuna riga è stata modificata.");
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
   
   /*@Override
   public void setDisponibility(){
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       try {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:././Information.db";
        connection = DriverManager.getConnection(dbURL);

        String sql = "UPDATE Item SET disponibility = meters - (SELECT SUM(totalMeters) FROM Loom WHERE Loom.item_code = Item.item_code)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.executeUpdate();


    }  catch (ClassNotFoundException e) {
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

   }*/
   
    @Override
    public void updateDisponibility(Item item){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:././Information.db";
        connection = DriverManager.getConnection(dbURL);

        String sql = "UPDATE Item SET disponibility = ? WHERE item_name = ?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, String.valueOf(item.getDisponibility()));
        preparedStatement.setString(2, item.getName());
        preparedStatement.executeUpdate();
        
        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("success");
        } else {
                System.out.println("Nessuna riga è stata modificata.");
        }
            
        connection.close();
        
    }  catch (ClassNotFoundException e) {
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
