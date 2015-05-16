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
public class DatabaseManager {
    
    public static DatabaseManager dbm = new DatabaseManager();
    
    //select query
    final static String ACCOUNT_FULL ="SELECT internal_id, username, password, nickname, on_line FROM entity_account";
    final static String ACCOUNT_STATUS_FULL ="SELECT internal_id, game_played, game_win, high_score, level, experience FROM entity_account_status";
    final static String GAME_STATUS_FULL ="SELECT internal_id, blockstatus, new_state, combo, attack, knockdown, in_game, room_id, enemy_internal_id FROM entity_game_status";
    final static String ROOM_FULL ="SELECT room_id, player1_id, player2_id, timer FROM entity_room";
    
    final static String ACCOUNT_META =" FROM entity_account";
    final static String ACCOUNT_STATUS_META =" FROM entity_account_status";
    final static String GAME_STATUS_META =" FROM entity_game_status";
    final static String ROOM_META =" FROM entity_room";
    
    final static String ENUM_LEVEL ="SELECT enum_level_id, description FROM entity_room";
    
    //Address
    public String server_url;
    public String server_username;
    public String server_password;
    public String server_select_query;
    
    //temp. data
    public String tempUsername;
    public String tempPassword;
    public String tempNickname;
    
    //Masterkey
    public int user_id;
    
    public int internal_id;
    
    //Data for account
    //public int internal_id;
    public String username;
    public String password;
    public String nickname;
    public boolean on_line;
    
    //Data for account status
    //public int internal_id;
    public int gamePlayed;
    public int gameWin;
    public int highScore;
    public int level;
    public int experience;
    
    //Data for enum_level
    public int enum_level_id;
    public String levelDescription;
    
    //Data for game status
    //public int internal_id;
    public String blockState;
    public boolean newState;
    public int combo;
    public int attack;
    public int knockdown;
    public boolean in_game;
    //public int room_id;
    
    //Data for room
    public int room_id;
    public int player1_id;
    public int player2_id;
    //public boolean in_game;
    public int timer;
    
    
    public DatabaseManager(){//Initialize data
        server_url = "jdbc:mysql://localhost:3306/tetris";
        server_username = "root";
        server_password = "";
        server_select_query = ACCOUNT_FULL;
        user_id=0;
        clearData();
    }
    
    public void changeServerAddress(String url, String username, String password){
        server_url=url;
        server_username=username;
        server_password=password;
    }
    
    
    
    public boolean checkAccountInfo(String id,String metatype,String datatype){
        System.out.println("SELECT "+datatype+getPartMetaData(datatype));
        System.out.println(ACCOUNT_FULL);
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT "+metatype+getPartMetaData(datatype)))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            System.out.printf("Database Loaded: "+getPartMetaData(datatype)+" - ");
            
            for(int i = 1; i <= numberOfColumns; i++){
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();
            
            while(resultSet.next()){
                if(id.equalsIgnoreCase((String) resultSet.getObject(1)))return false;
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return true;
    }
    
    public void saveNewAccountID(String username, String password, String nickname){
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(ACCOUNT_FULL))
        {
            int lastPrimaryKey = 0;
            
            System.out.printf("Database Loaded: "+getPartMetaData("account"));
            
            while(resultSet.next())lastPrimaryKey = resultSet.getInt(1)+1;
            System.out.println(lastPrimaryKey);
            
            statement.executeUpdate("INSERT into entity_account values("+lastPrimaryKey+",'"+username+"','"+password+"','"+nickname+"',false)");
            
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        loadData("");
    }
    
    
    
    
    
    
    
    
    public void getDataFromServer(String part, int meta){
        clearData(meta);
        String str = getPartMetaData(meta);
        if(str==null){
            System.out.println("Incorrect meta number");
        }else{
            str="Select "+part+str;
            loadData(str);
        }
    }
    
    public void getDataFromServer(int meta){
        clearData(meta);
        String str = getMetaData(meta);
        if(str==null){
            System.out.println("Incorrect meta number");
        }else{
            loadData(str);
        }
    }
    
    private String getPartMetaData(int meta){
        switch(meta){
            case 1:
                return ACCOUNT_META;
            case 2:
                return ACCOUNT_STATUS_META;
            case 3:
                return GAME_STATUS_META;
            case 4:
                return ROOM_META;
            default:
                return null;
        }
    }
    
    private String getPartMetaData(String meta){
        if(meta.equalsIgnoreCase("account"))return ACCOUNT_META;
        else if(meta.equalsIgnoreCase("account status"))return ACCOUNT_STATUS_META;
        else if(meta.equalsIgnoreCase("game status"))return GAME_STATUS_META;
        else if(meta.equalsIgnoreCase("room"))return ROOM_META;
        return null;
    }
    private String getMetaData(int meta){
        switch(meta){
            case 1:
                return ACCOUNT_FULL;
            case 2:
                return ACCOUNT_STATUS_FULL;
            case 3:
                return GAME_STATUS_FULL;
            case 4:
                return ROOM_FULL;
            default:
                return null;
        }
    }
    
    
    private void loadData(String str){
        
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(server_select_query))
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
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    
    public void clearData(){
        //Data for account
        internal_id=0;
        username="";
        password="";
        nickname="";
        on_line=false;

        //Data for account status
        //public int internal_id;
        gamePlayed=0;
        gameWin=0;
        highScore=0;
        level=0;
        experience=0;

        //Data for enum_level
        enum_level_id=0;
        levelDescription="";

        //Data for game status
        //public int internal_id;
        blockState="";
        newState=false;
        combo=0;
        attack=0;
        knockdown=0;
        in_game=false;
        //public int room_id;

        //Data for room
        room_id=0;
        player1_id=0;
        player2_id=0;
        //public boolean in_game;
        timer=0;
    }
    
    public void clearData(int meta){
        switch(meta){
            case 1:
                //Data for account
                internal_id=0;
                username="";
                password="";
                nickname="";
                on_line=false;
                break;
            
            case 2:
                //Data for account status
                //public int internal_id;
                gamePlayed=0;
                gameWin=0;
                highScore=0;
                level=0;
                experience=0;
                break;
                
            case 3:
                //Data for game status
                //public int internal_id;
                blockState="";
                newState=false;
                combo=0;
                attack=0;
                knockdown=0;
                in_game=false;
                //public int room_id;
                break;
                
            case 4:
                //Data for room
                room_id=0;
                player1_id=0;
                player2_id=0;
                //public boolean in_game;
                timer=0;
                break;
                
            case 5:
                //Data for enum_level
                enum_level_id=0;
                levelDescription="";
                break;
                
            default:
                System.out.println("Incorrect value");
                break;
        }
    }
}
