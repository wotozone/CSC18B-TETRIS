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
    private String nickname;
    public String text="";
    public char type;
    public char channel;
    public char command;
    
    public ClientSender(Socket socket,String name,String nickname,char type,char channel,char command) {
        this.name = name;
        this.socket = socket;
        this.nickname=nickname;
        this.type=type;
        this.channel=channel;
        this.command=command;
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
                output.writeUTF("["+type+channel+command+"]"+text); 
            } catch (IOException e) {
                
            }
        }
        //}
    }
    
    
}
