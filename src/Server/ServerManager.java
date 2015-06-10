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
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;

/**
 *
 * @author Tae
 */
/*
public class ServerManager extends Thread {
  
    ArrayList clientOutputStreams;
 
  
   
    public void start() {
    clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSock = new ServerSocket(5000);
            while(ServerScreen.ss.serverStatus) {
            System.out.println("sdfsdf");
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                ServerReceiver receiver = new ServerReceiver(clientSocket,clientOutputStreams);
                receiver.start();
                //Thread t = new Thread(new ServerReceiver(clientSocket,clientOutputStreams));
                //t.start();
                //System.out.println("got a connection" + clientSocket.getPort());
                ServerScreen.ss.addText("got a connection : " + clientSocket.getPort() + "\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
*/
  


public class ServerManager extends Thread {
    
    public static ServerManager sm = new ServerManager();
    
    public HashMap<String, DataOutputStream> clients;
    public ServerSocket serverSocket;
    public ServerReceiver receiver;
    
    public ServerManager(){
        clients = new HashMap<String, DataOutputStream>();
        
        Collections.synchronizedMap(clients);
    }
    
    @Override
    public void run(){
        try{
            Socket socket;
            
            serverSocket = new ServerSocket(8840);
            System.out.println("Server is now open");
            ServerScreen.ss.addText("Server is now open");
            
            while(ServerScreen.ss.serverStatus){
                socket = serverSocket.accept();
                receiver = new ServerReceiver(socket,clients);
                receiver.start();
            }
            ServerScreen.ss.addText("Server is closed");
            
        } catch (IOException e){
            e.printStackTrace();
            ServerScreen.ss.addText("Currently unavailable to open server");
        }
    }
    
}
