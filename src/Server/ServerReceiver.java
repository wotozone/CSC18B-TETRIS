/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Tae
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
            ServerScreen.ss.addText(clients.size() + "users are in the server");
            
            //Send Message
            while (input != null) {
                sendToAll(input.readUTF());
            }
        } catch (IOException e) {
        } finally {
            //connection end
            clients.remove(name);
            sendToAll("#" + name + "[" + socket.getInetAddress() + ":"
                    + socket.getPort() + "]" + " has left.");
            System.out.println(name + "[" + socket.getInetAddress() + ":"
                    + socket.getPort() + "]" + " has disconnected from the server");
            System.out.println(clients.size() + "users are in the server");
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
