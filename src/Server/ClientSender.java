/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import tetris2.RoomScreen;
import tetris2.RoomScreen1;

/**
 *
 * @author Tae
 */
public class ClientSender extends Thread {
    private Socket socket;
    private DataOutputStream output;
    private String name;
    public String text="";
    
    public ClientSender(Socket socket,String name) {
        this.name = name;
        this.socket = socket;
        try {
            output = new DataOutputStream(socket.getOutputStream());
            if(ClientConnector.cc.first)output.writeUTF(name);
        } catch (Exception e) {
        }
    }

    
    
    
    
    
    @Override
    public void run() {
        //while (output != null) {
        if(ClientConnector.cc.first)ClientConnector.cc.first=false;
        else{
            try {
                //text=RoomScreen1.rs.sendingText;
                System.out.println(text);
                output.writeUTF(name+ ": " + text); 
            } catch (IOException e) {
                
            }
        }
        //}
    }
    
    
}
