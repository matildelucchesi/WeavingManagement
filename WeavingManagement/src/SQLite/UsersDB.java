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
import java.util.List;

/**
 *
 * @author Matilde
 */
public class UsersDB {
    
    public static boolean authenticate(String username, String password) {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            connection.close();
            
            return resultSet.next();
        }
        catch (ClassNotFoundException e) {
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
        return false;
    }
    
    public boolean changePassword(List<String> password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean update = false;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql = "UPDATE Users SET password = ? WHERE password = ?";
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1, password.get(1));
            preparedStatement.setString(2, password.get(0));

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("success");
                update = true;
            } else {
                System.out.println("failed");
                update = false;
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
        return update;
    }
    
    public void addUser(List<String> data){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssociation = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            // insert Query
            String insertQuery = "INSERT INTO Users (username, password) VALUES (?, ?)";
     
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, data.get(0));     
            preparedStatement.setString(2, data.get(1));
            
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
