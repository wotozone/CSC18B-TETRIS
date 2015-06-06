/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Tae
 */
public class ClientSender extends Thread {
    private Socket socket;
    private DataOutputStream output;
    private String name;
    
    public ClientSender(Socket socket,String name) {
        this.name = name;
        this.socket = socket;
        try {
            output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(name);
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String msg = "";

        while (output != null) {
            try {
                msg = sc.nextLine();
                output.writeUTF(name + ": " + msg);
            } catch (IOException e) {
            }
        }
    }
}
