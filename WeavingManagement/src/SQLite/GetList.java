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
import java.util.List;
import model.*;

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
   
}
