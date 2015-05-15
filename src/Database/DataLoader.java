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
    
    final static String DATABASE_URL = "jdbc:mysql://localhost:3306/tetris";
    final static String USERNAME = "root";
    final static String PASSWORD = "";
    final String SELECT_QUERY = "SELECT Internal_ID, username, password";
    
    public void loadData(){
        
        try(
                Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_QUERY))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            for(int i = 1; i<=numberOfcolumns; i++){
                
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
}
