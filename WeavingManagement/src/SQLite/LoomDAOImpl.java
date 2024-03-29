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
import model.Loom;
import model.LoomBuilder;

/**
 *
 * @author Matilde
 */
public class LoomDAOImpl implements LoomDAO {
    
    @Override
    public boolean insertLoom(Loom loom){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssociation = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            // insert Query
            String insertQuery = "INSERT INTO Loom (loom_code, speed, start_date, surrender, totalMeters, metersToGo, expectedEndDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
     
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, loom.getNumber());
            preparedStatement.setInt(2, loom.getSpeed());
            preparedStatement.setString(3, loom.getStartDate().toString());
            preparedStatement.setInt(4, loom.getSurrender());
            preparedStatement.setInt(5, loom.getTotalMeters());
            preparedStatement.setInt(6, loom.getMetersToGo());
            preparedStatement.setString(7, loom.getExpectedEndDate().toString());            
            preparedStatement.executeUpdate();
            
            //Loom assocition
            String insertAssociationQuery = "INSERT INTO LoomAssociation (loom_code, item_name) VALUES (?, ?)";
 
            preparedStatementAssociation = connection.prepareStatement(insertAssociationQuery);
            preparedStatementAssociation.setInt(1, loom.getNumber());
            preparedStatementAssociation.setString(2, loom.getItem().getName()); 
            
            preparedStatementAssociation.executeUpdate();
         
            int rowsAffected = preparedStatementAssociation.executeUpdate();
            
            if (rowsAffected > 0) {
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
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
                return false;
            }
        }
    }
    
    @Override
    public boolean updateMetersToGo(Loom loom) {
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
                String sql1 = "UPDATE Item SET metersToGo = ? WHERE item_name = ?";
                preparedStatement = connection.prepareStatement(sql1);

                int newItemMetersToGo = loom.getItem().getMetersToGo();

                preparedStatement.setInt(1, newItemMetersToGo);
                preparedStatement.setString(2, loom.getItem().getName());

                rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Il valore di metersRun per il item_name " + loom.getItem().getName() + " è stato aggiornato con successo.");
                    connection.close();
                    return true;
                } else {
                    System.out.println("Nessuna riga è stata modificata.");
                    connection.close();
                    return false;
                }
            } 
            else {
                System.out.println("Nessuna riga è stata modificata.");
                connection.close();
                return false;
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
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
                return false;
            }
        }
    }
    
    @Override
    public List<Loom> getLoomList(){
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate expectedEndDate = LocalDate.parse(resultSet.getString("expectedEndDate"), formatter);
            
            String selectItem = "SELECT item_name FROM LoomAssociation WHERE loom_code = ?";
            preparedStatement = connection.prepareStatement(selectItem);
            preparedStatement.setInt(1, loomId);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            String itemName = new String();
            while(resultSet1.next()){
                itemName = resultSet1.getString("item_name");
            }
            
            Loom loom = new LoomBuilder()
                        .setNumber(loomId)
                        .setSpeed(speed)
                        .setStartDate(LocalDate.parse(startDate))
                        .setSurrender(surrender)
                        .setTotalMeters(totalMeters)
                        .setMetersToGo(metersToGo)
                        .setMetersToGo(metersToGo)
                        .setItem(itemName)
                        .setExpectedEndDate(expectedEndDate)
                        .build();
                      
            loomList.add(loom);
            
            String insertLoomExpectedEndDate = "UPDATE Loom SET expectedEndDate = ? WHERE loom_code = ?";
            preparedStatement = connection.prepareStatement(insertLoomExpectedEndDate);
            LocalDate exp = loom.getExpectedEndDate();
            
            preparedStatement.setString(1, exp.toString());
            preparedStatement.setInt(2, loom.getNumber());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il valore di expectedEndDate è stato aggiornato con successo.");
            } else {
                System.out.println("Nessuna riga è stata modificata.");
            }
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
    return loomList;
    }
    
    @Override
    public boolean removeLoom(Loom loom){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql = "DELETE FROM LoomAssociation WHERE loom_code = ?";
            
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loom.getNumber());
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("success deleting in LoomAssociation");
                String sql1 = "DELETE FROM Loom WHERE loom_code = ?";
                preparedStatement = connection.prepareStatement(sql1);

                preparedStatement.setInt(1, loom.getNumber());

                int rowsDeleted1 = preparedStatement.executeUpdate();

                if (rowsDeleted1 > 0) {
                    System.out.println("success deleting in Loom");
                    
                    String updateAvailability = "UPDATE Item SET availability = ? WHERE item_name = ?";
                    preparedStatement = connection.prepareStatement(updateAvailability);
            
                    int newAvailability = loom.getItem().getAvailability() + loom.getMetersToGo();

                    preparedStatement.setInt(1, newAvailability);
                    preparedStatement.setString(2, loom.getItem().getName());

                    int rowsUpdate1 = preparedStatement.executeUpdate();

                    if (rowsUpdate1 > 0) {
                        System.out.println("success deleting in Loom");
                        connection.close();
                        return true;
                    } else {
                        System.out.println("fail deleting in Loom");
                        connection.close();
                        return false;
                    }
                } else {
                    System.out.println("fail deleting in Loom");
                    connection.close();
                    return false;
                }
            
            } 
            else {
                System.out.println("fail deleting in LoomAssociation");
                connection.close();
                return false;
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
             return false;
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
                return false;
            }
        }
    }
    
    @Override
    public boolean updateExpectedEndDate(Loom loom){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql = "UPDATE Loom SET expectedEndDate = ? WHERE loom_code = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, loom.getExpectedEndDate().toString());
            preparedStatement.setInt(2, loom.getNumber());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il valore di expectedEndDate per il loom " + loom.getNumber() + " è stato aggiornato con successo.");
                connection.close();
                return true;
            } else {
                System.out.println("Nessuna riga è stata modificata.");
                connection.close();
                return false;
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
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
                return false;
            }
        }
    }
}
