/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tae
 */
public class DataLoader {
    
    private String DATABASE_URL = "jdbc:mysql://71.95.58.77:3306/tetris";
    private String USERNAME = "client";
    private String PASSWORD = "hNBPuxGfAT8dyh75";
    private String SELECT_QUERY = "SELECT internal_id, username, password, nickname, on_line FROM entity_account";
    
    public DataLoader(){//Initialize data
    }
    
    public void loadData(){
        
        try(
                Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_QUERY))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            System.out.printf("Database Loaded%n%n");
            for(int i = 1; i <= numberOfColumns; i++){
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();
            
            while(resultSet.next()){
                for(int i = 1; i <= numberOfColumns; i++){
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                System.out.println();
            }
            
            int j = 0;
            while(resultSet.next())j = resultSet.getInt(1)+1;
            statement.executeUpdate("INSERT into entity_account values("+j+",'T','K','F',true)");
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
}
