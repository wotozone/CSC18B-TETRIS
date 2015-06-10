/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Database.DatabaseManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;

/**
 *
 * @author Tae
 */
 
public class ServerReceiver extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private String data;
    private String appliedData;
    private char type;
    private char channel;
    private char command;
    
    /*
        TYPE
            [M] - message
            [R] - room
            [S] - serverManager
    
        channel - room number or channel number
    
        Command
            [M]
                [A] - to All
            
            [R]
                [J] - request to join a room
                [A] - successfully joined
                [F] - failed to join a room
                [U] - update changes
                [Q] - quit a room
                [R] - ready
                [C] - unable to set ready
                [B] - Undo ready
                [L] - Lose
                [T] - Time
                [O] - Timeout
                [W] - win
                [N] - renew score
    */
    
    
    private HashMap<String, DataOutputStream> clients;

    public ServerReceiver(Socket socket, HashMap clients) {
        this.clients=clients;
        this.socket = socket;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        }
    }

    private int charToInt(char num){
        if(num=='1')return 1;
        else if(num=='2')return 2;
        else if(num=='3')return 3;
        else if(num=='4')return 4;
        else if(num=='5')return 5;
        else if(num=='6')return 6;
        else if(num=='7')return 7;
        else if(num=='8')return 8;
        return 0;
    }
    
    @Override
    public void run() {
        String name = "";
        try {
            //Notice
            name = input.readUTF();
            if(!name.isEmpty())sendToAll("[MAA]"+ name + " joined.");
            
            clients.put(name, output);
            
            
            ServerScreen.ss.addText(name + "[" + socket.getInetAddress() + ":"
                    + socket.getPort() + "]" + " has connected to the server.");
            ServerScreen.ss.addText(clients.size() + " users are in the server");
            
            //Send Message
            while (input != null) {
                data = input.readUTF();
                type=data.charAt(1);
                channel=data.charAt(2);
                command=data.charAt(3);
                //data=data.substring(5);
                
                
                //sendToUser("1","damn");
                System.out.println(data);
                
                //Messages
                if(type=='M'&&channel=='A'&&command=='A'){
                    ServerScreen.ss.addText("sending text: "+type+channel+command);
                    sendToAll(data);
                    
                }else if(type=='R'){
                    appliedData=data.substring(data.indexOf("<")+1,data.indexOf(">"));
                    DatabaseManager.dbm.loadRoomData(charToInt(channel));
                    //Join room
                    if(command=='J'){//Join
                        ServerScreen.ss.addText(Integer.toString(DatabaseManager.dbm.player1_id)+"///"+Integer.toString(DatabaseManager.dbm.player2_id));
                        if(DatabaseManager.dbm.player1_id==0){
                            ServerScreen.ss.addText(appliedData+" join room "+channel+" as player 1",3);
                            DatabaseManager.dbm.saveRoomData(Character.toString(channel), "player1_id", appliedData);
                            DatabaseManager.dbm.saveData(appliedData, "room_id", "game status",Character.toString(channel));
                            sendToUser(appliedData,"[R"+channel+"A]"+"accepted");
                        }else if(DatabaseManager.dbm.player2_id==0){
                            ServerScreen.ss.addText(appliedData+" join room "+channel+" as player 2",3);
                            DatabaseManager.dbm.saveRoomData(Character.toString(channel), "player2_id", appliedData);
                            DatabaseManager.dbm.saveData(appliedData, "room_id", "game status",Character.toString(channel));
                            sendToUser(appliedData,"[R"+channel+"A]"+"accepted");
                            sendToAll("[R"+channel+"U]"+"Full");
                        }else{
                            sendToUser(appliedData,"[R"+channel+"F]"+"Failed");
                            ServerScreen.ss.addText(appliedData + " failed to join a room #"+ channel,3);
                        }
                    }else if(command=='Q'){//Quit
                        if(Integer.toString(DatabaseManager.dbm.player1_id).equalsIgnoreCase(appliedData)){
                            ServerScreen.ss.addText(appliedData+"\n"+Character.toString(channel)+"\n"+DatabaseManager.dbm.player1_id,3);
                            DatabaseManager.dbm.saveRoomData(Character.toString(channel), "player1_id", "0");
                            if(DatabaseManager.dbm.player2_id!=0){
                                sendToUser(Integer.toString(DatabaseManager.dbm.player2_id),"[R"+channel+"C]"+"cannot ready");
                            }
                        }else if(Integer.toString(DatabaseManager.dbm.player2_id).equalsIgnoreCase(appliedData)){
                            DatabaseManager.dbm.saveRoomData(Character.toString(channel), "player2_id", "0");
                            if(DatabaseManager.dbm.player1_id!=0){
                                sendToUser(Integer.toString(DatabaseManager.dbm.player1_id),"[R"+channel+"C]"+"cannot ready");
                            }
                        }
                        ServerScreen.ss.addText(appliedData+" leave room "+channel,3);
                        DatabaseManager.dbm.saveData(appliedData, "room_id", "game status","0");
                        sendToUser(appliedData,"[R"+channel+"Q]"+"quit room");
                    }else if(command=='R'){//Ready
                        if(Integer.toString(DatabaseManager.dbm.player1_id).equalsIgnoreCase(appliedData)){
                            if(DatabaseManager.dbm.player2_id==0){
                                ServerScreen.ss.addText(appliedData+" unable to ready",3);
                                sendToUser(Integer.toString(DatabaseManager.dbm.player1_id),"[R"+channel+"C]"+"cannot ready");
                            }else{
                                ServerScreen.ss.addText(appliedData+" ready",3);
                                sendToUser(Integer.toString(DatabaseManager.dbm.player2_id),"[R"+channel+"R]"+"I am ready");
                            }
                        }else if(Integer.toString(DatabaseManager.dbm.player2_id).equalsIgnoreCase(appliedData)){
                            if(DatabaseManager.dbm.player1_id==0){
                                ServerScreen.ss.addText(appliedData+" unable to ready",3);
                                sendToUser(Integer.toString(DatabaseManager.dbm.player2_id),"[R"+channel+"C]"+"cannot ready");
                            }else{
                                ServerScreen.ss.addText(appliedData+" ready",3);
                                sendToUser(Integer.toString(DatabaseManager.dbm.player1_id),"[R"+channel+"R]"+"I am ready");
                            }
                        }
                    }else if(command=='B'){//Ready
                        if(Integer.toString(DatabaseManager.dbm.player1_id).equalsIgnoreCase(appliedData)){
                            ServerScreen.ss.addText(appliedData+" undo ready",3);
                            sendToUser(Integer.toString(DatabaseManager.dbm.player2_id),"[R"+channel+"B]"+"undo ready");
                        }else if(Integer.toString(DatabaseManager.dbm.player2_id).equalsIgnoreCase(appliedData)){
                            ServerScreen.ss.addText(appliedData+" undo ready",3);
                            sendToUser(Integer.toString(DatabaseManager.dbm.player1_id),"[R"+channel+"B]"+"undo ready");
                        }
                    }else if(command=='S'){//Start
                        if(Integer.toString(DatabaseManager.dbm.player1_id).equalsIgnoreCase(appliedData)){
                            if(DatabaseManager.dbm.player1_id!=0&&DatabaseManager.dbm.player2_id!=0){
                                sendToUser(Integer.toString(DatabaseManager.dbm.player1_id),"[R"+channel+"S]"+"start game");
                                sendToUser(Integer.toString(DatabaseManager.dbm.player2_id),"[R"+channel+"S]"+"start game");
                            }
                        }
                    }else if(command=='L'){//Lose
                        if(Integer.toString(DatabaseManager.dbm.player1_id).equalsIgnoreCase(appliedData)){
                            ServerScreen.ss.addText(appliedData+" lose the game in room "+Character.toString(channel),3);
                            makeDecision(Integer.toString(DatabaseManager.dbm.player2_id),Integer.toString(DatabaseManager.dbm.player1_id),channel);
                        }else if(Integer.toString(DatabaseManager.dbm.player2_id).equalsIgnoreCase(appliedData)){
                            ServerScreen.ss.addText(appliedData+" lose the game in room "+Character.toString(channel),3);
                            makeDecision(Integer.toString(DatabaseManager.dbm.player1_id),Integer.toString(DatabaseManager.dbm.player2_id),channel);
                        }
                    }
                    DatabaseManager.dbm.clearData(5);
                }
            }
        } catch (IOException e) {
        } finally {
            //connection end
            clients.remove(name);
            if(!name.isEmpty())sendToAll("[MAA]"+ name + " left.");
            //DatabaseManager.dbm.clearData(3);
            //DatabaseManager.dbm.loadData(name, "game status");
            ServerScreen.ss.addText(name + "[" + socket.getInetAddress() + ":"
                    + socket.getPort() + "]" + " has disconnected from the server");
            ServerScreen.ss.addText(clients.size() + " users are in the server");
        }
    }

    
    public void makeDecision(String winner,String loser,char room){
        System.out.println(loser+"///   [R"+room+"L]"+"you lose");
        sendToUser(loser,"[R"+room+"L]"+"you lose");
        sendToUser(winner,"[R"+room+"W]"+"you win");
        
        sendToUser(loser,"[R"+room+"N]"+"check score");
        sendToUser(winner,"[R"+room+"N]"+"check score");
        
    }
    
    public void sendToUser(String name,String message){
        try{
            DataOutputStream dos = clients.get(name);
            dos.writeUTF(message);
        }catch (Exception e){
            System.out.println("error");
        }
    }
    
    public void sendToAll(String message) {
        Iterator<String> it = clients.keySet().iterator();
        
        
        while (it.hasNext()) {
            try {
                DataOutputStream dos = clients.get(it.next());
                dos.writeUTF(message);
            } catch (Exception e) {
            }
        }
    }
}
