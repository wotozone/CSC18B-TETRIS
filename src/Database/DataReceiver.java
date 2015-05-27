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
import tetris2.RoomScreen1;

/**
 *
 * @author Tae
 */
public class DataReceiver extends Thread{
    
    private boolean connected=true;
    private Thread chatHost;
    
    private boolean host=false;
    private int host_id=0;
    
    private int host_timeout=0;
    
    public DataReceiver(){
        joinChat();
    }
    
    public void run(){
        try {

            boolean connection=true;
            while(connection){
                Thread.sleep(100);
                if(!checkUpdate())connection=false;
            } 
            
        }catch(Exception e){
            e.printStackTrace(); 
        } 
        RoomScreen1.rs.receiveChat("Disconected from the server");
    }
    
    public void joinChat(){
        try(
                Connection connection = DriverManager.getConnection(DatabaseManager.dbm.server_url, DatabaseManager.dbm.server_username, DatabaseManager.dbm.server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(DatabaseManager.CHAT_FULL))
        {
            int lastPrimaryKey = 0;
            int chatId=1;
            int intId=0;
            boolean currentManager=false;
            boolean manager=false;
            boolean chatNum[] = new boolean[200];
            for(int i=0;i<200;i++){
                chatNum[i]=true;
            }
            
            System.out.printf("Database Saving: chat");
            
            //MAX CAPACITY
            while(resultSet.next()){
                if(DatabaseManager.dbm.internal_id==(int) resultSet.getObject(1)){
                    DatabaseManager.dbm.deleteData(Integer.toString((int) resultSet.getObject(1)), "chat");
                    System.out.println("PREVIOUS DATA HAS DELETED");
                }
                if((int) resultSet.getObject(2)<=200){//just in case
                    chatNum[(int) resultSet.getObject(2)-1]=false;
                }
                if((boolean) resultSet.getObject(3))currentManager=true;
                lastPrimaryKey++;
                intId=(int) resultSet.getObject(1);
            }
            System.out.println(lastPrimaryKey);
            for(int i=0;i<200;i++){
                if(chatNum[i]){
                    chatId=i+1;
                    break;
                }
            }
            
            if(!currentManager){
                manager=true;
            }else if(lastPrimaryKey==0){
                manager=true;
            }else if(lastPrimaryKey==1){
                DatabaseManager.dbm.saveData(Integer.toString(intId), "manager", "chat", "false");
                manager=true;
                //add later
            }else if(lastPrimaryKey>200){
                //add later
            }
            RoomScreen1.rs.currentUser=lastPrimaryKey;
            RoomScreen1.rs.receiveChat(lastPrimaryKey+" players are playing game");
            
            lastPrimaryKey=DatabaseManager.dbm.internal_id;
            statement.executeUpdate("INSERT into entity_chat values("
                    +lastPrimaryKey+",'"
                    +chatId+"',"
                    +manager+",'',true,false)");
            
            if(manager){
                host=true;
                chatHost = new Thread(new ChatHost());
                chatHost.start();
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public boolean checkUpdate(){
        try(
                Connection connection = DriverManager.getConnection(DatabaseManager.dbm.server_url, DatabaseManager.dbm.server_username, DatabaseManager.dbm.server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT internal_id, manager, latency, chat_update FROM entity_chat"))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            int userNum=0;
            
            System.out.printf(".");
            
            connected=false;
            while(resultSet.next()){
                if(!host&&(boolean) resultSet.getObject(2)==true){
                    if((int) resultSet.getObject(1)==host_id&&(boolean) resultSet.getObject(4)==true){
                        if(host_timeout>300){
                                DatabaseManager.dbm.deleteData(Integer.toString((int) resultSet.getObject(1)), "chat");
                                DatabaseManager.dbm.saveData(Integer.toString(DatabaseManager.dbm.internal_id), "manager", "chat", "true");
                                host=true;
                                chatHost = new Thread(new ChatHost());
                                chatHost.start();
                        }else{
                            host_timeout++;
                            System.out.println(host_timeout);
                        }
                    }else{
                        host_id=(int) resultSet.getObject(1);
                        host_timeout=0;
                    }
                }
                if(DatabaseManager.dbm.internal_id==(int) resultSet.getObject(1)){
                    if((boolean) resultSet.getObject(3)==false)DatabaseManager.dbm.saveData(Integer.toString(DatabaseManager.dbm.internal_id), "latency", "chat", "true");
                    if((boolean) resultSet.getObject(4)==true){
                        getChatText();
                    }
                    connected=true;
                }
                userNum++;
            }
            RoomScreen1.rs.currentUser=userNum;
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            connected=false;
            return connected;
        }
        return connected;
    }
    
    public boolean getChatText(){
        try(
                Connection connection = DriverManager.getConnection(DatabaseManager.dbm.server_url, DatabaseManager.dbm.server_username, DatabaseManager.dbm.server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT internal_id, chat_text FROM entity_chat"))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            System.out.printf("Database Loaded: entity_chat - ");
            
            for(int i = 1; i <= numberOfColumns; i++){
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();
            
            while(resultSet.next()){
                if(DatabaseManager.dbm.internal_id==(int) resultSet.getObject(1)){
                    DatabaseManager.dbm.chat_text=(String) resultSet.getObject(2);
                    RoomScreen1.rs.receiveChat();
                    DatabaseManager.dbm.saveData(Integer.toString(DatabaseManager.dbm.internal_id), "chat_update", "chat", "false");
                    DatabaseManager.dbm.saveData(Integer.toString(DatabaseManager.dbm.internal_id), "chat_text", "chat", "null");
                }
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return true;
    }
    
    
}
