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
    final static String GAME_STATUS_FULL ="SELECT internal_id, blockstate, new_state, combo, attack, knockdown, in_game, room_id, enemy_internal_id FROM entity_game_status";
    final static String CHAT_FULL ="SELECT internal_id, chat_id, manager, chat_text, latency, chat_update FROM entity_chat";
    final static String ROOM_FULL ="SELECT room_id, player1_id, player2_id, playing FROM entity_room";
    final static String SERVER_FULL ="SELECT server_id, ip_address, port FROM entity_server";
    
    final static String ACCOUNT_META =" FROM entity_account";
    final static String ACCOUNT_STATUS_META =" FROM entity_account_status";
    final static String GAME_STATUS_META =" FROM entity_game_status";
    final static String CHAT_META =" FROM entity_chat";
    final static String ROOM_META =" FROM entity_room";
    final static String SERVER_META =" FROM entity_server";
    
    final static String ACCOUNT_NAME ="entity_account";
    final static String ACCOUNT_STATUS_NAME ="entity_account_status";
    final static String GAME_STATUS_NAME ="entity_game_status";
    final static String CHAT_NAME ="entity_chat";
    final static String ROOM_NAME ="entity_room";
    final static String SERVER_NAME ="entity_server";
    
    final static String ENUM_LEVEL ="SELECT enum_level_id, description FROM entity_room";
    
    //Address
    public String server_url;
    public String server_username;
    public String server_password;
    public String server_select_query;
    public String server_description;
    
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
    
    //Data for chat
    //public int internal_id;
    public int chat_id;
    public boolean manager;
    public String chat_text;
    public boolean latency;
    public boolean chat_update;
    //public int room_id;
    
    //Data for room
    public int room_id;
    public int player1_id;
    public int player2_id;
    //public boolean in_game;
    public boolean playing;
    
    //Data for server
    //public int internal_id;
    public int server_id;
    public String ip_address;
    public int port;
    
    public DatabaseManager(){//Initialize data
        /*
        server_url = "jdbc:mysql://localhost:3306/tetris";
        server_username = "root";
        server_password = "";
        */
        server_url = "jdbc:mysql://71.95.58.77:3306/tetris?useUnicode=true&characterEncoding=utf8";
        server_username = "client";
        server_password = "hNBPuxGfAT8dyh75";;
        server_select_query = ACCOUNT_FULL;
        server_description = "Tae's testing DB";
        user_id=0;
        clearData();
    }
    
    public boolean checkConnection(){
        boolean connect=false;
        try(Connection connection = DriverManager.getConnection(server_url, server_username, server_password)){
            connect=true;
        }catch (SQLException sqlException){
            System.out.println("SQLException: " + sqlException.getMessage());
            System.out.println("SQLState: " + sqlException.getSQLState());
            System.out.println("VendorError: " + sqlException.getErrorCode());
        }
        loadData("SELECT internal_id, username, nickname FROM entity_account");
        return connect;
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
    
    public boolean getInternalID(){
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(ACCOUNT_FULL))
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            System.out.printf("Database Loaded: entity_account%n%n");
            
            for(int i = 1; i <= numberOfColumns; i++){
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();
            
            while(resultSet.next()){
                for(int i = 1; i <= numberOfColumns; i++){
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                if(tempUsername.equalsIgnoreCase((String) resultSet.getObject(2))){
                    internal_id=(int) resultSet.getObject(1);
                    tempNickname=(String) resultSet.getObject(4);
                }
                System.out.println();
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
            System.out.println("LP_"+lastPrimaryKey);
            
            internal_id=lastPrimaryKey;
            statement.executeUpdate("INSERT into entity_account values("
                    +lastPrimaryKey+",'"
                    +username+"','"
                    +password+"','"
                    +nickname+"',false)");
            statement.executeUpdate("INSERT into entity_account_status values("
                    +lastPrimaryKey+",0,0,0,1,0)");
            statement.executeUpdate("INSERT into entity_game_status values("
                    +lastPrimaryKey+",'',false,0,0,0,false,0,0)");
            
            //getInternalID();
            
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        loadData(ACCOUNT_FULL);
        loadData(ACCOUNT_STATUS_FULL);
        loadData(GAME_STATUS_FULL);
    }
    /*
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
    */
    
    public void getDataFromServer(int meta){//Searching by internal_id
        clearData(meta);
        String str = getMetaData(meta);
        if(str==null){
            System.out.println("Incorrect meta number");
        }else{
            loadData(str);
        }
    }
    
    
    public void loadRoomData(int roomNum){
        
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(ROOM_FULL))
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
                    if(roomNum==(int) resultSet.getObject(1))getData(ROOM_FULL,i,resultSet.getObject(i));
                }
                System.out.println();
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    private void loadData(String str){
        
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(str))
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
                    if(internal_id==(int) resultSet.getObject(1))getData(str,i,resultSet.getObject(i));
                }
                System.out.println();
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public void loadData(int id,String str){
        
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getMetaData(str)))
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
                    if(id==((int) resultSet.getObject(1))){
                        getData(getMetaData(str),i,resultSet.getObject(i));
                    }
                }
                System.out.println();
            }
            
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public void getData(String str,int column,Object data){
        if(str==ACCOUNT_FULL||str==ACCOUNT_META){
            switch(column){
                case 2:username=(String) data;break;
                case 3:password=(String) data;break;
                case 4:nickname=(String) data;break;
                case 5:on_line=(boolean) data;break;
                default:break;
            }
        }else if(str==ACCOUNT_STATUS_FULL||str==ACCOUNT_STATUS_META){
            switch(column){
                case 2:gamePlayed=(int) data;break;
                case 3:gameWin=(int) data;break;
                case 4:highScore=(int) data;break;
                case 5:level=(int) data;break;
                case 6:experience=(int) data;break;
                default:break;
            }
        }else if(str==GAME_STATUS_FULL||str==GAME_STATUS_META){
            switch(column){
                case 2:blockState=(String) data;break;
                case 3:newState=(boolean) data;break;
                case 4:combo=(int) data;break;
                case 5:attack=(int) data;break;
                case 6:knockdown=(int) data;break;
                case 7:in_game=(boolean) data;break;
                default:break;
            }
        }else if(str==CHAT_FULL||str==CHAT_META){
            switch(column){
                case 2:chat_id=(int) data;break;
                case 3:manager=(boolean) data;break;
                case 4:chat_text=(String) data;break;
                case 5:latency=(boolean) data;break;
                case 6:chat_update=(boolean) data;break;
                default:break;
            }
        }else if(str==ROOM_FULL||str==ROOM_META){
            switch(column){
                case 1:room_id=(int) data;break;
                case 2:player1_id=(int) data;break;
                case 3:player2_id=(int) data;break;
                case 4:playing=(boolean) data;break;
                default:break;
            }
        }else if(str==SERVER_FULL||str==SERVER_META){
            switch(column){
                case 1:server_id=(int) data;break;
                case 2:ip_address=(String) data;break;
                case 3:port=(int) data;break;
                default:break;
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void saveRoomData(String roomNum,String metatype,String value){
        System.out.println(ACCOUNT_FULL);
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement())
        {
            System.out.printf("Database Loaded: "+ROOM_META+" - ");
            System.out.println("update "+metatype+" as "+value);
            
            System.out.println("UPDATE "+ROOM_NAME+" set "+metatype+"="+value+" where room_id="+ roomNum);
            if(statement.executeUpdate("UPDATE "+ROOM_NAME+" set "+metatype+"="+value+" where room_id="+ roomNum)==0){
                System.out.println("Unable to update data");
            }else{
                System.out.println("Successfuly updated");
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public void saveData(String id,String metatype,String datatype,String value){
        System.out.println("SELECT "+datatype+getPartMetaData(datatype));
        System.out.println(ACCOUNT_FULL);
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement())
        {
            System.out.printf("Database Loaded: "+getPartMetaData(datatype)+" - ");
            System.out.println("update "+metatype+" as "+value);
            
            System.out.println("UPDATE "+getEntityName(datatype)+" set "+metatype+"="+value+" where internal_id="+ id);
            if(statement.executeUpdate("UPDATE "+getEntityName(datatype)+" set "+metatype+"="+value+" where internal_id="+ id)==0){
                System.out.println("Unable to update data");
            }else{
                System.out.println("Successfuly updated");
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    
    
    
    public void deleteData(String id,String datatype){
        System.out.println("SELECT "+datatype+getPartMetaData(datatype));
        System.out.println(ACCOUNT_FULL);
        try(
                Connection connection = DriverManager.getConnection(server_url, server_username, server_password);
                Statement statement = connection.createStatement())
        {
            System.out.printf("Database Loaded: "+getPartMetaData(datatype)+" - ");
            System.out.println("delete "+id);
            
            System.out.println("DELETE"+getPartMetaData(datatype)+" where internal_id="+ id);
            if(statement.executeUpdate("DELETE"+getPartMetaData(datatype)+" where internal_id="+ id)==0){
                System.out.println("Unable to delete data");
            }else{
                System.out.println("Successfuly deleted");
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private String getEntityName(int meta){
        switch(meta){
            case 1:
                return ACCOUNT_NAME;
            case 2:
                return ACCOUNT_STATUS_NAME;
            case 3:
                return GAME_STATUS_NAME;
            case 4:
                return CHAT_NAME;
            case 5:
                return ROOM_NAME;
            case 6:
                return SERVER_NAME;
            default:
                return null;
        }
    }
    
    private String getEntityName(String meta){
        if(meta.equalsIgnoreCase("account"))return ACCOUNT_NAME;
        else if(meta.equalsIgnoreCase("account status"))return ACCOUNT_STATUS_NAME;
        else if(meta.equalsIgnoreCase("game status"))return GAME_STATUS_NAME;
        else if(meta.equalsIgnoreCase("chat"))return CHAT_NAME;
        else if(meta.equalsIgnoreCase("room"))return ROOM_NAME;
        else if(meta.equalsIgnoreCase("server"))return SERVER_NAME;
        return null;
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
                return CHAT_META;
            case 5:
                return ROOM_META;
            case 6:
                return SERVER_META;
            default:
                return null;
        }
    }
    
    private String getPartMetaData(String meta){
        if(meta.equalsIgnoreCase("account"))return ACCOUNT_META;
        else if(meta.equalsIgnoreCase("account status"))return ACCOUNT_STATUS_META;
        else if(meta.equalsIgnoreCase("game status"))return GAME_STATUS_META;
        else if(meta.equalsIgnoreCase("chat"))return CHAT_META;
        else if(meta.equalsIgnoreCase("room"))return ROOM_META;
        else if(meta.equalsIgnoreCase("server"))return SERVER_META;
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
                return CHAT_FULL;
            case 5:
                return ROOM_FULL;
            case 6:
                return SERVER_FULL;
            default:
                return null;
        }
    }
    private String getMetaData(String meta){
        if(meta.equalsIgnoreCase("account"))return ACCOUNT_FULL;
        else if(meta.equalsIgnoreCase("account status"))return ACCOUNT_STATUS_FULL;
        else if(meta.equalsIgnoreCase("game status"))return GAME_STATUS_FULL;
        else if(meta.equalsIgnoreCase("chat"))return CHAT_FULL;
        else if(meta.equalsIgnoreCase("room"))return ROOM_FULL;
        else if(meta.equalsIgnoreCase("server"))return SERVER_FULL;
        return null;
    }
    
    
    public void clearData(){
        System.out.println("######################################");
        System.out.println("##                                  ##");
        System.out.println("##            WARNING!!!            ##");
        System.out.println("##                                  ##");
        System.out.println("##        ALL DATA HAS WIPED        ##");
        System.out.println("##                                  ##");
        System.out.println("##                                  ##");
        System.out.println("######################################");
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
        
        //Data for chat
        //public int internal_id;
        chat_id=0;
        manager=false;
        String chat_text=null;
        latency=false;
        chat_update=false;

        //Data for room
        room_id=0;
        player1_id=0;
        player2_id=0;
        //public boolean in_game;
        playing=false;
        
        
        //Data for server
        //public int internal_id;
        server_id=0;
        ip_address="127.0.0.1";
        port=8840;
    }
    
    public void clearData(int meta){
        System.out.println("######################################");
        System.out.println("##                                  ##");
        System.out.println("##            WARNING!!!            ##");
        System.out.println("##                                  ##");
        System.out.println("##           DATA-"+meta+" WIPED           ##");
        System.out.println("##                                  ##");
        System.out.println("##                                  ##");
        System.out.println("######################################");
        System.out.println("INTERNAL_ID: "+internal_id);
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
                //Data for chat
                //public int internal_id;
                chat_id=0;
                manager=false;
                String chat_text=null;
                latency=false;
                chat_update=false;
                break;
                
            case 5:
                //Data for room
                room_id=0;
                player1_id=0;
                player2_id=0;
                //public boolean in_game;
                playing=false;
                break;
                
            case 6:
                //Data for enum_level
                enum_level_id=0;
                levelDescription="";
                break;
            
            case 7:
                server_id=0;
                ip_address="127.0.0.1";
                port=8840;
                break;
                
            default:
                System.out.println("Incorrect value");
                break;
        }
    }
}
