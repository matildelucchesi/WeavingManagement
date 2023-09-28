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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Item;
import model.ItemBuilder;

/**
 *
 * @author Matilde
 */
public class ItemDAOImpl implements ItemDAO {
    
   @Override
   public void insertItem(Item item, ClientDAOImpl cdb) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssociation = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            // insert Query
            String insertQuery = "INSERT INTO Item (item_name, meters, metersToGo, disponibility, rowNumber, hits, deliveryDate, expectedEndDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
     
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getMeters());
            preparedStatement.setInt(3, item.getMetersToGo());
            preparedStatement.setInt(4, item.getDisponibility());
            preparedStatement.setInt(5, item.getRowNumber());
            preparedStatement.setInt(6, item.getHits());
            preparedStatement.setString(7, item.getDeliveryDate().toString());
            preparedStatement.setString(8, item.getExpectedEndDate().toString());
            
            // Esegui la query di inserimento
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("success");
            } else {
                System.out.println("No data insert");
            }
            
            String insertAssociationQuery = "INSERT INTO ClientAssociation (client_name, item_name) VALUES (?, ?)";
            preparedStatementAssociation = connection.prepareStatement(insertAssociationQuery);
            preparedStatementAssociation.setString(1, item.getClient().getName());
            preparedStatementAssociation.setString(2, item.getName()); 
            
            rowsAffected = preparedStatementAssociation.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("success");
            } else {
                System.out.println("No data insert");
            }
            
            if(cdb.countClientWithName(item.getClient().getName()) == 0){
                cdb.insertClient(item.getClient());
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
            
            SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date delivDate = new Date();
            Date expDate = new Date();
            String formattedDelivDate = new String();
            String formattedExpDate = new String();
            try{
                delivDate = originalDateFormat.parse(deliveryDate);
                expDate = originalDateFormat.parse(expectedEndDate);
                SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                formattedDelivDate = desiredDateFormat.format(delivDate);
                formattedExpDate =  desiredDateFormat.format(expDate);
                
            }catch(ParseException e){
                System.err.println("Errore durante il parsing della data");
            }
            
            String clientQuery = "SELECT client_name FROM ClientAssociation WHERE item_name = ?";
            preparedStatement = connection.prepareStatement(clientQuery);
            preparedStatement.setString(1, itemName);
            resultSet = preparedStatement.executeQuery();
            
            String clientName = new String();
            
            while (resultSet.next()) {
                    clientName = resultSet.getString("client_name");
                }
            
            Item item = new ItemBuilder()
                    .setName(itemName)
                    .setMeters(meters)
                    .setMetersToGo(metersToGo)
                    .setDisponibility(disponibility)
                    .setRowNumber(rowNumber)
                    .setHits(hits)
                    .setDeliveryDate(formattedDelivDate)
                    .setExpectedEndDate(formattedExpDate)
                    .setClient(clientName)
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
    
    @Override
    public void removeItem(Item item){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql0 = "DELETE FROM ClientAssociation WHERE item_name = ?";
            
            preparedStatement = connection.prepareStatement(sql0);
            preparedStatement.setString(1, item.getName());
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("success deleting in ClientAssociation");
            } else {
                System.out.println("fail deleting in ClientAssociation");
            }
            
            String sql1 = "DELETE FROM ItemAssociation WHERE item_name = ?";
            
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, item.getName());
            rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("success deleting in LoomAssociation");
            } else {
                System.out.println("fail deleting in LoomAssociation");
            }
            
            String sql2 = "DELETE FROM Item WHERE item_name = ?";
            preparedStatement = connection.prepareStatement(sql2);

            preparedStatement.setString(1, item.getName());

            int rowsDeleted1 = preparedStatement.executeUpdate();

            if (rowsDeleted1 > 0) {
                System.out.println("success deleting in Item");
            } else {
                System.out.println("fail deleting in Item");
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
