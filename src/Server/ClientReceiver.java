/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Database.DatabaseManager;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import tetris2.BlockStatus;
import tetris2.Initializer;
import tetris2.RoomScreen1;

/**
 *
 * @author Tae
 */
public class ClientReceiver extends Thread {
    private Socket socket;
    private DataInputStream input;
    private String name;
    private String text;
    private char type;
    private char channel;
    private char command;
    
    public ClientReceiver(Socket socket, String name) {
        this.socket = socket;
        try {
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            RoomScreen1.rs.receiveChat("Server connection error(#0001)");
        }
    }

    private int charToInt(char num){
        if(num=='0')return 0;
        else if(num=='1')return 1;
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
        while (input != null) {
            try {
                text=input.readUTF();
                type=text.charAt(1);
                channel=text.charAt(2);
                command=text.charAt(3);
                if(type=='M'&&channel=='A'&&command=='A'){
                    text=text.substring(5);
                    RoomScreen1.rs.receiveChat(text);
                }else if(type=='R'){
                    if(command=='F'){
                        RoomScreen1.rs.failedToJoin();
                    }else if(command=='A'){
                        RoomScreen1.rs.JoinRoom();
                    }else if(command=='C'){
                        Initializer.ready=false;
                    }else if(command=='R'){
                        Initializer.enemyReady=true;
                    }else if(command=='B'){
                        Initializer.enemyReady=false;
                    }else if(command=='S'){
                        RoomScreen1.rs.main.gameStart();
                    }else if(command=='T'){
                        text=text.substring(5);
                        Initializer.time=Integer.parseInt(text);
                    }else if(command=='N'){
                        DatabaseManager.dbm.loadData(DatabaseManager.dbm.internal_id, "account status");
                        if(DatabaseManager.dbm.highScore<Initializer.myScore){
                            DatabaseManager.dbm.saveData(Integer.toString(DatabaseManager.dbm.internal_id), "high_score", "account status", Integer.toString(Initializer.myScore));
                        }
                    }else if(command=='L'){
                        Initializer.start=false;
                        Initializer.end=true;
                        Initializer.winner=false;
                    }else if(command=='W'){
                        Initializer.start=false;
                        Initializer.end=true;
                        Initializer.winner=true;
                    }else if(command=='E'){
                        text=text.substring(5);
                        StringBuffer str = new StringBuffer();
                        str.append(text);
                        for(int i=1;i<21;i++){
                            for(int j=0;j<10;j++){
                                BlockStatus.enemyblocks[j][i].setBlockColor(charToInt(str.charAt( ((i*10)-10) + j)));
                            }
                        }
                    }
                }
                
            } catch (IOException e) {
                RoomScreen1.rs.receiveChat("Server connection error(#0002)");
                break;
            }
        }
    }
}
