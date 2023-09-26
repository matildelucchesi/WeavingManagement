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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Client;
import model.Item;

/**
 *
 * @author Matilde
 */
public class ClientDAOImpl implements ClientDAO{
    
    @Override
    public void insertClient(String name, List<String> referents, List<String> phoneNumber){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        String cName = name;
        int client_id;
       
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String insertClient = "INSERT INTO Client (name) VALUES(?)";
            
            preparedStatement = connection.prepareStatement(insertClient);
            preparedStatement.setString(1, cName);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("success");
            } else {
                System.out.println("No data insert");
            }
            
            //take id of client
            preparedStatement = connection.prepareStatement("SELECT last_insert_rowid()");
            ResultSet resultSet = preparedStatement.executeQuery();
            client_id = resultSet.getInt(1);
            
            //insert referents
            if(!referents.isEmpty()){
                String insertReferent = "INSERT INTO Referents (client_id, referent_name) VALUES(?, ?)";

                for(int i=0; i < referents.size(); i++){
                    preparedStatement = connection.prepareStatement(insertReferent);
                    preparedStatement.setInt(1, client_id);
                    preparedStatement.setString(2, referents.get(i));

                    rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("success");
                    } else {
                        System.out.println("No data insert");
                    }
                }
            }
            
            //insert phone numbers
            if(!phoneNumber.isEmpty()){
                String insertPhone = "INSERT INTO PhoneNumbers (client_id, number) VALUES (?, ?)";

                for(int k=0; k < phoneNumber.size(); k++){
                    preparedStatement = connection.prepareStatement(insertPhone);
                    preparedStatement.setInt(1, client_id);
                    preparedStatement.setString(2, phoneNumber.get(k));

                    rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("success");
                    } else {
                        System.out.println("No data insert");
                    }
                }
            }
            
            connection.close();
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
    }
    
    @Override
    public List<Client> getClientList(){
        
        List<Client> clientList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int client_id = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);

            String selectQuery = "SELECT * FROM Client";
            preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                client_id = resultSet.getInt("id");
                
                String referentsQuery = "SELECT referent_name FROM Referents WHERE client_id = ?";
                String phoneNumbersQuery = "SELECT number FROM PhoneNumbers WHERE client_id = ?";
                
                preparedStatement = connection.prepareStatement(referentsQuery);
                preparedStatement.setInt(1, client_id);
                ResultSet referentsResultSet = preparedStatement.executeQuery();

                List<String> referents = new ArrayList<>();
                while (referentsResultSet.next()) {
                    referents.add(referentsResultSet.getString("referent_name"));
                }
                
                preparedStatement = connection.prepareStatement(phoneNumbersQuery);
                preparedStatement.setInt(1, client_id);
                ResultSet phoneNumbersResultSet = preparedStatement.executeQuery();

                List<String> phoneNumbers = new ArrayList<>();
                while (phoneNumbersResultSet.next()) {
                    phoneNumbers.add(phoneNumbersResultSet.getString("number"));
                }

                Client client = new Client(name, referents, phoneNumbers);
                clientList.add(client);
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
        return clientList;
        }

    @Override
    public void changeData(String name, List<String> referents, List<String> phone){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int client_id = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);

            String selectId = "SELECT id FROM Client WHERE name = ?";
            
            preparedStatement = connection.prepareStatement(selectId);
            preparedStatement.setString(1, name);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client_id = resultSet.getInt("id");
            } else {
                System.out.print("failed");
            }
            
            if(!referents.isEmpty()){
                
                String delete = "DELETE FROM Referents WHERE client_id = ?";
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setInt(1, client_id);
                
                int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("success");
                    } else {
                        System.out.println("No data insert");
                    }
                 
                String insertReferent = "INSERT INTO Referents (client_id, referent_name) VALUES(?, ?)";

                for(int i=0; i < referents.size(); i++){
                    if(referents.get(i).isBlank()){
                        i++;
                    }else{
                       preparedStatement = connection.prepareStatement(insertReferent);
                        preparedStatement.setInt(1, client_id);
                        preparedStatement.setString(2, referents.get(i));

                        rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("success");
                        } else {
                            System.out.println("No data insert");
                        } 
                    }
                    
                }
            }
            
            if(!phone.isEmpty()){
                String delete = "DELETE FROM PhoneNumbers WHERE client_id = ?";
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setInt(1, client_id);
                
                int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("success");
                    } else {
                        System.out.println("No data insert");
                    }
                    
                String insertPhone = "INSERT INTO PhoneNumbers (client_id, number) VALUES (?, ?)";

                for(int k=0; k < phone.size(); k++){
                    if(phone.get(k).isBlank()){
                        k++;
                    }else{
                        preparedStatement = connection.prepareStatement(insertPhone);
                        preparedStatement.setInt(1, client_id);
                        preparedStatement.setString(2, phone.get(k));

                        rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("success");
                        } else {
                            System.out.println("No data insert");
                        }
                    }
                    
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
    }
    
    @Override
    public void removeClient(String name){
        
    }
}
