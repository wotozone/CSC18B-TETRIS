/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tae
 */
public class ChatHost extends Thread {
    
    private boolean host=true;
    
    public ChatHost(){
        
    }
    
    public void run(){
        try {
            while(host){
                Thread.sleep(10000);//check per 10sec
                if(!checkOnLine())host=false;
            } 
            
        }catch(Exception e){
            e.printStackTrace(); 
        } 
        System.out.println("You are not host any longer");
    }
    
    public boolean checkOnLine(){
        boolean connected=false;
        try(
                Connection connection = DriverManager.getConnection(DatabaseManager.dbm.server_url, DatabaseManager.dbm.server_username, DatabaseManager.dbm.server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT internal_id, manager, latency FROM entity_chat"))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            System.out.println("CHATHOST IS RUNNING");
            
            
            while(resultSet.next()){
                if((boolean) resultSet.getObject(3)==true){
                    DatabaseManager.dbm.saveData(Integer.toString((int) resultSet.getObject(1)), "latency", "chat", "false");
                }else{
                    DatabaseManager.dbm.deleteData(Integer.toString((int) resultSet.getObject(1)), "chat");
                }
                if(DatabaseManager.dbm.internal_id==(int) resultSet.getObject(1)){
                    if((boolean) resultSet.getObject(2)==false){
                        host=false;
                    }
                    connected=true;
                }
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
        return connected;
    }
    
}
