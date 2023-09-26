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
import model.Loom;
import model.LoomBuilder;

/**
 *
 * @author Matilde
 */
public class LoomDAOImpl implements LoomDAO {
    
    @Override
    public void insertLoom(int lcode, int s, String sd, int sr, int totmt, int mt, String in, String ed) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssociation = null;
        
        int loom_code = lcode;
        int speed = s;
        String start_date = sd;
        int surrender = sr;
        int totalMeters = totmt;
        int metersToGo = mt;
        String item_name = in;
        String expected_end_date = ed;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            // insert Query
            String insertQuery = "INSERT INTO Loom (loom_code, speed, start_date, surrender, totalMeters, metersToGo, item_name, expectedEndDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
     
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, loom_code);
            preparedStatement.setInt(2, speed);
            preparedStatement.setString(3, start_date);
            preparedStatement.setInt(4, surrender);
            preparedStatement.setInt(5, totalMeters);
            preparedStatement.setInt(6, metersToGo);
            preparedStatement.setString(7, item_name);
            preparedStatement.setString(8, expected_end_date);
            
            preparedStatement.executeUpdate();
            
            //Loom assocition
            String insertAssociationQuery = "INSERT INTO LoomAssociation (loom_code, item_name, expectedEndDate) VALUES (?, ?, ?)";
 
            preparedStatementAssociation = connection.prepareStatement(insertAssociationQuery);
            preparedStatementAssociation.setInt(1, loom_code);
            preparedStatementAssociation.setString(2, item_name); 
            preparedStatementAssociation.setString(3, expected_end_date); 
            
            preparedStatementAssociation.executeUpdate();
            
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
    public void updateMetersToGo(Loom loom) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        //update Loom's meters to go
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql = "UPDATE Loom SET metersToGo = ? WHERE loom_code = ?";
            preparedStatement = connection.prepareStatement(sql);
            int newMetersToGo = loom.getMetersToGo();
            
            preparedStatement.setInt(1, newMetersToGo);
            preparedStatement.setString(2, Integer.toString(loom.getNumber()));

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il valore di metersRun per il loom_code " + Integer.toString(loom.getNumber()) + " è stato aggiornato con successo.");
            } else {
                System.out.println("Nessuna riga è stata modificata.");
            }
            
            String sql1 = "UPDATE Item SET metersToGo = ? WHERE item_name = ?";
            preparedStatement = connection.prepareStatement(sql1);
            
            int newItemMetersToGo = loom.getItem().getMetersToGo();
            
            preparedStatement.setInt(1, newItemMetersToGo);
            preparedStatement.setString(2, loom.getItem().getName());

            rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il valore di metersRun per il item_name " + loom.getItem().getName() + " è stato aggiornato con successo.");
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
    public List<Loom> getLoomList(List<Item> itemList){
        List<Loom> loomList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
    try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);

        String selectQuery = "SELECT * FROM Loom";

        preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int loomId = resultSet.getInt("loom_code");
            int speed = resultSet.getInt("speed");
            String startDate = resultSet.getString("start_date");
            int surrender = resultSet.getInt("surrender");
            int totalMeters = resultSet.getInt("totalMeters");
            int metersToGo = resultSet.getInt("metersToGo");
            String itemName = resultSet.getString("item_name");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate expectedEndDate = LocalDate.parse(resultSet.getString("expectedEndDate"), formatter);
            
            Loom loom = new LoomBuilder()
                        .setNumber(loomId)
                        .setSpeed(speed)
                        .setStartDate(LocalDate.parse(startDate))
                        .setSurrender(surrender)
                        .setTotalMeters(totalMeters)
                        .setMetersToGo(metersToGo)
                        .setMetersToGo(metersToGo)
                        .setItem(itemName, itemList)
                        .setExpectedEndDate(expectedEndDate)
                        .build();
                        
            loomList.add(loom);
        }
        
        
        resultSet.close();
        connection.close();
        
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        // Chiudi le risorse
    }
    return loomList;
    }
    
    @Override
    public void removeLoom(int lcode){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql = "DELETE FROM LoomAssociation WHERE loom_code = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, lcode);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("success deleting in LoomAssociation");
            } else {
                System.out.println("fail deleting in LoomAssociation");
            }
            
            String sql1 = "DELETE FROM Loom WHERE loom_code = ?";
            preparedStatement = connection.prepareStatement(sql1);

            preparedStatement.setInt(1, lcode);

            int rowsDeleted1 = preparedStatement.executeUpdate();

            if (rowsDeleted1 > 0) {
                System.out.println("success deleting in Loom");
            } else {
                System.out.println("fail deleting in Loom");
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