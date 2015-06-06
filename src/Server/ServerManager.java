/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Tae
 */
public class ServerManager extends Thread {
    public HashMap<String, DataOutputStream> clients;
    public ServerSocket serverSocket;
    
    
    public ServerManager(){
        clients = new HashMap<String, DataOutputStream>();
        
        Collections.synchronizedMap(clients);
    }
    
    @Override
    public void run(){
        try{
            Socket socket;
            
            serverSocket = new ServerSocket(8840);
            ServerScreen.ss.addText("Server is now open");
            
            while(ServerScreen.ss.serverStatus){
                socket = serverSocket.accept();
                ServerReceiver receiver = new ServerReceiver(socket,clients);
                receiver.start();
            }
            
        } catch (IOException e){
            e.printStackTrace();
            ServerScreen.ss.addText("Currently unavailable to open server");
        }
    }
    
}