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
import model.Model;

/**
 *
 * @author Matilde
 */
public class ClientDAOImpl implements ClientDAO{
    
    @Override
    public void insertClient(Client client){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String insertClient = "INSERT INTO Client (name) VALUES(?)";
            
            preparedStatement = connection.prepareStatement(insertClient);
            preparedStatement.setString(1, client.getName());
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("success");
            } else {
                System.out.println("No data insert");
            }
           
            
            //insert referents
            if(!client.getReferents().isEmpty()){
                String insertReferent = "INSERT INTO Referents (client_name, referent_name) VALUES(?, ?)";

                for(int i=0; i < client.getReferents().size(); i++){
                    preparedStatement = connection.prepareStatement(insertReferent);
                    preparedStatement.setString(1, client.getName());
                    preparedStatement.setString(2, client.getReferents().get(i));

                    rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("success");
                    } else {
                        System.out.println("No data insert");
                    }
                }
            }
            
            //insert phone numbers
            if(!client.getPhoneNumber().isEmpty()){
                String insertPhone = "INSERT INTO PhoneNumbers (client_name, number) VALUES (?, ?)";

                for(int k=0; k < client.getPhoneNumber().size(); k++){
                    preparedStatement = connection.prepareStatement(insertPhone);
                    preparedStatement.setString(1, client.getName());
                    preparedStatement.setString(2, client.getPhoneNumber().get(k));

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
                
                String referentsQuery = "SELECT referent_name FROM Referents WHERE client_name = ?";
                String phoneNumbersQuery = "SELECT number FROM PhoneNumbers WHERE client_name = ?";
                String itemQuery = "SELECT item_name FROM ClientAssociation WHERE client_name = ?";
                
                preparedStatement = connection.prepareStatement(referentsQuery);
                preparedStatement.setString(1, name);
                ResultSet referentsResultSet = preparedStatement.executeQuery();

                List<String> referents = new ArrayList<>();
                while (referentsResultSet.next()) {
                    referents.add(referentsResultSet.getString("referent_name"));
                }
                
                preparedStatement = connection.prepareStatement(phoneNumbersQuery);
                preparedStatement.setString(1, name);
                ResultSet phoneNumbersResultSet = preparedStatement.executeQuery();

                List<String> phoneNumbers = new ArrayList<>();
                while (phoneNumbersResultSet.next()) {
                    phoneNumbers.add(phoneNumbersResultSet.getString("number"));
                }
                
                preparedStatement = connection.prepareStatement(itemQuery);
                preparedStatement.setString(1, name);
                ResultSet itemResultSet = preparedStatement.executeQuery();

                List<String> itemNameList = new ArrayList<>();
                List<Item> itemList = new ArrayList<>();
                while (itemResultSet.next()) {
                    itemNameList.add(itemResultSet.getString("item_name"));
                }
                
                for(int i=0; i < itemNameList.size(); i++){
                    for(int j = 0; j < Model.getItemList().size(); j++){
                        if(itemNameList.get(i).equals(Model.getItemList().get(j).getName())){
                            itemList.add(Model.getItemList().get(j));
                        }
                    }
                }
                
                Client client = new Client(name, referents, phoneNumbers, itemList);
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
    public void changeData(Client client, List<String> referents, List<String> phone){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            if(!referents.isEmpty()){
                
                String delete = "DELETE FROM Referents WHERE client_name = ?";
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setString(1, client.getName());
                
                int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("success");
                    } else {
                        System.out.println("No data deleted");
                    }
                 
                String insertReferent = "INSERT INTO Referents (client_name, referent_name) VALUES(?, ?)";

                for(int i=0; i < referents.size(); i++){
                    if(referents.get(i).isBlank()){
                        i++;
                    }else{
                       preparedStatement = connection.prepareStatement(insertReferent);
                        preparedStatement.setString(1, client.getName());
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
                String delete = "DELETE FROM PhoneNumbers WHERE client_name = ?";
                preparedStatement = connection.prepareStatement(delete);
                preparedStatement.setString(1, client.getName());
                
                int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("success");
                    } else {
                        System.out.println("No data deleted");
                    }
                    
                String insertPhone = "INSERT INTO PhoneNumbers (client_name, number) VALUES (?, ?)";

                for(int k=0; k < phone.size(); k++){
                    if(phone.get(k).isBlank()){
                        k++;
                    }else{
                        preparedStatement = connection.prepareStatement(insertPhone);
                        preparedStatement.setString(1, client.getName());
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
    public void removeClient(Client client){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String sql0 = "DELETE FROM Referents WHERE client_name = ?";
            preparedStatement = connection.prepareStatement(sql0);
            preparedStatement.setString(1, client.getName());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("success deleting in Referents");
            } else {
                System.out.println("fail deleting in Referents");
            }
            
            String sql1 = "DELETE FROM PhoneNumbers WHERE client_name = ?";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, client.getName());
            rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("success deleting in PhoneNumbers");
            } else {
                System.out.println("fail deleting in PhoneNumbers");
            }
            
            String sql2 = "DELETE FROM ClientAssociation WHERE client_name = ?";
            
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, client.getName());
            rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("success deleting in ClientAssociation");
            } else {
                System.out.println("fail deleting in ClientAssociation");
            }
            
            String sql3 = "DELETE FROM Client WHERE client_name = ?";
            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1, client.getName());
            rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("success deleting in Client");
            } else {
                System.out.println("fail deleting in Client");
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
    public int countClientWithName(String name){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int number = 1;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            String checkClientQuery = "SELECT COUNT(*) FROM Client WHERE name = ?";
            PreparedStatement checkClientStatement = connection.prepareStatement(checkClientQuery);
            checkClientStatement.setString(1, name);
            ResultSet resultSet = checkClientStatement.executeQuery();
            resultSet.next();
            number = resultSet.getInt(1);
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
        return number;
    }
    
    @Override
    public void changeData(Client client){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:././Information.db";
            connection = DriverManager.getConnection(dbURL);
            
            String delete = "DELETE FROM Referents WHERE client_name = ?";
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, client.getName());
                
            int rowsAffected = preparedStatement.executeUpdate();
                
            if (rowsAffected > 0) {
                System.out.println("success!!");
            } else {
                System.out.println("No data deleted");
            }
            
            if(!client.getReferents().isEmpty()){     
                String insertReferent = "INSERT INTO Referents (client_name, referent_name) VALUES(?, ?)";

                for(int i=0; i < client.getReferents().size(); i++){
                    if(client.getReferents().get(i).isBlank()){
                        i++;
                    }else{
                       preparedStatement = connection.prepareStatement(insertReferent);
                        preparedStatement.setString(1, client.getName());
                        preparedStatement.setString(2, client.getReferents().get(i));

                        rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("success");
                        } else {
                            System.out.println("No data insert");
                        } 
                    }
                    
                }
            }
            
            String delete1 = "DELETE FROM PhoneNumbers WHERE client_name = ?";
            preparedStatement = connection.prepareStatement(delete1);
            preparedStatement.setString(1, client.getName());
                
            rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("success");
            } else {
                System.out.println("No data deleted");
            }
                   
            if(!client.getPhoneNumber().isEmpty()){ 
                String insertPhone = "INSERT INTO PhoneNumbers (client_name, number) VALUES (?, ?)";

                for(int k=0; k < client.getPhoneNumber().size(); k++){
                    if(client.getPhoneNumber().get(k).isBlank()){
                        k++;
                    }else{
                        preparedStatement = connection.prepareStatement(insertPhone);
                        preparedStatement.setString(1, client.getName());
                        preparedStatement.setString(2, client.getPhoneNumber().get(k));

                        rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("success");
                        } else {
                            System.out.println("No data insert");
                        }
                    }
                    
                }
            }

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
}
