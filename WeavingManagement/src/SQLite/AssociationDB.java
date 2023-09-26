/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Matilde
 */
public class AssociationDB {
    
   public List<String> getAssociationList(String itemCode){
        List<String> loomAssociationList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);

        String selectQuery = "SELECT * FROM LoomAssociation";

        preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            if((resultSet.getString("item_code").equals(itemCode))){
                loomAssociationList.add(Integer.toString(resultSet.getInt("loom_code")));   
            }
        }

        resultSet.close();
        connection.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }  
        return loomAssociationList;
    }
   
   public LocalDate getExpectedEndDate(String itemCode) {
    List<LocalDate> date = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate after = null;

    try {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:././Information.db";
        connection = DriverManager.getConnection(dbURL);

        String selectQuery = "SELECT * FROM LoomAssociation";

        preparedStatement = connection.prepareStatement(selectQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getString("item_code").equals(itemCode)) {
                date.add(LocalDate.parse(resultSet.getString("expectedEndDate"), formatter));
            }
        }

        after = date.isEmpty() ? null : date.get(0);

        for (int i = 1; i < date.size(); i++) {
            if (date.get(i).isAfter(date.get(i - 1))) {
                after = date.get(i);
            }
        }

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
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

    return after;
    }
   
   public List<String> getItemAssociationClientList(String clientName){
       
       List<String> item = new ArrayList<>();
       Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String clientCode;
        int clientId = 0;
    
       try {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:././Information.db";
        connection = DriverManager.getConnection(dbURL);
        
        String getId = "SELECT id FROM Client WHERE name = ?";
        preparedStatement = connection.prepareStatement(getId);
        preparedStatement.setString(1, clientName);
        
        resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                clientId = resultSet.getInt("id");
                System.out.println("L'ID corrispondente al client name " + clientName + " è: " + clientId);
            } else {
                System.out.println("Nessun cliente trovato con il nome " + clientName);
            }

        String selectQuery = "SELECT * FROM ClientAssociation";

        preparedStatement = connection.prepareStatement(selectQuery);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            if (resultSet.getString("client_id").equals(Integer.toString(clientId))) {
                System.out.print("ciao");
                item.add(resultSet.getString("item_code"));
            }
        }

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
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
       return item;
   }

}
  
