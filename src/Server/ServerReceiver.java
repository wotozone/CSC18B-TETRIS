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
/*public class ServerReceiver extends Thread {
    ArrayList clientOutputStreams;
    BufferedReader reader;
    Socket sock;
   
   public ServerReceiver(Socket clientSocket,ArrayList clientOutputStreams) {
    try {
     sock = clientSocket;
     this.clientOutputStreams = clientOutputStreams;
     InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
     reader = new BufferedReader(isReader);
     
    } catch (Exception e) {e.printStackTrace();}
   }
   
   public void run() {
    String message;
    try {
    while((message = reader.readLine()) != null) {
      System.out.println("read : " + message);
      tellEveryone(message);
     }
    } catch (Exception e) {e.printStackTrace();}
   }
   
  public void tellEveryone(String message) {
   Iterator it = clientOutputStreams.iterator();
   while(it.hasNext()) {
    try {
     PrintWriter writer = (PrintWriter) it.next();
     writer.println(message);
     writer.flush();
    } catch(Exception e) {
     e.printStackTrace();
    }
   }
  }
 }
*/
 


public class ServerReceiver extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    
    
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

    @Override
    public void run() {
        String name = "";
        try {
            //Notice
            name = input.readUTF();
            sendToAll(name + " has joined");
            
            clients.put(name, output);
            
            
            ServerScreen.ss.addText(name + "[" + socket.getInetAddress() + ":"
                    + socket.getPort() + "]" + " has connected to the server.");
            ServerScreen.ss.addText(clients.size() + " users are in the server");
            
            //Send Message
            while (input != null) {
                sendToAll(input.readUTF());
            }
        } catch (IOException e) {
        } finally {
            //connection end
            clients.remove(name);
            sendToAll(name + " has left.");
            ServerScreen.ss.addText(name + "[" + socket.getInetAddress() + ":"
                    + socket.getPort() + "]" + " has disconnected from the server");
            ServerScreen.ss.addText(clients.size() + " users are in the server");
        }
    }

    public void sendToUser(String message){
        
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
