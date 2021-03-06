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
import java.net.Socket;
import java.util.Scanner;
 
/**
 *
 * @author Tae
 */
public class ClientConnector {
    
    public static ClientConnector cc = new ClientConnector();
    
    public static String MESSAGE_TO_ALL = "[MAA]";
    
    public ClientReceiver clientReceiver;
    public ClientSender clientSender;
    
    public String name;
    public Socket socket;
    public String nickname;
    public String serverIp = "71.95.58.77";
    public boolean first=true;
    
    public ClientConnector(){
        serverIp = "71.95.58.77";
    }
    
    public boolean checkConnection(){
        try {
            socket = new Socket(serverIp, 8840);
            name="server";
            socket.close();
            return true;
        } catch (IOException e) {
            System.out.println("Game Server is down.");
            return false;
        }
    }
    
    public void connectToChat(){
        try {
            socket = new Socket(serverIp, 8840);
            name = Integer.toString(DatabaseManager.dbm.internal_id);
            nickname=DatabaseManager.dbm.tempNickname;
            
            clientReceiver = new ClientReceiver(socket,name);
            //clientSender = new ClientSender(socket,name);
            ClientSender clientSender = new ClientSender(socket,name,nickname,'M','A','A');
            
            clientReceiver.start();
            clientSender.start();
        } catch (IOException e) {
        }
    }
    
    
}
