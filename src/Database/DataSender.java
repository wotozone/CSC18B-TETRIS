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
public class DataSender {
    
    public void setChatText(String text){
        try(
                Connection connection = DriverManager.getConnection(DatabaseManager.dbm.server_url, DatabaseManager.dbm.server_username, DatabaseManager.dbm.server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT internal_id, chat_text, chat_update FROM entity_chat"))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            System.out.println("Chatting log Updated");
            
            while(resultSet.next()){
                if(DatabaseManager.dbm.internal_id!=(int) resultSet.getObject(1)){
                    if((boolean) resultSet.getObject(3)==false){
                        DatabaseManager.dbm.saveData(Integer.toString((int) resultSet.getObject(1)), "chat_update", "chat", "true");
                        DatabaseManager.dbm.saveData(Integer.toString((int) resultSet.getObject(1)), "chat_text", "chat", "'"+text+"'");
                    }else{
                        DatabaseManager.dbm.saveData(Integer.toString((int) resultSet.getObject(1)), "chat_text", "chat", "'"+(String)resultSet.getObject(2)+text+"'");
                    }
                }
            }
            
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
