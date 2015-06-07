/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import tetris2.RoomScreen1;

/**
 *
 * @author Tae
 */
public class ClientReceiver extends Thread {
    private Socket socket;
    private DataInputStream input;
    private String name;
    
    public ClientReceiver(Socket socket, String name) {
        this.socket = socket;
        try {
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            RoomScreen1.rs.receiveChat("Server connection error(#0001)");
        }
    }

    @Override
    public void run() {
        while (input != null) {
            try {
                RoomScreen1.rs.receiveChat(input.readUTF());
            } catch (IOException e) {
                RoomScreen1.rs.receiveChat("Server connection error(#0002)");
                break;
            }
        }
    }
}
