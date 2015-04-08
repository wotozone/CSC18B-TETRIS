/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 *
 * @author minjikim
 */
public class ObjectStream {
    
    public static int totalUsers=0;
    public static final int MAX_USER_NUM = 20;
    public static String[] userName = new String[MAX_USER_NUM];
    public static ObjectOutputStream oos = null;
    public static ObjectInputStream ois = null;
    
    public static void initSaveSteam(){
        
        //store objects by using ObjectOutput
        try{
            //fos = new FileOutputStream(Files.newOutputStream(Paths.get("Accounts.dat")));
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("Account.dat")));
            
        }catch(IOException e){
            System.exit(1);
        }
        
    }
    
    
    
    public static void saveID(String name){
        
        Scanner input = new Scanner(System.in);
        
        try{
            oos.writeObject(name);
        }catch(NoSuchElementException elementException){
            
        }catch(IOException ioexception){
            
        }
    }
    
    public static void closeSaveStream(){
        try{
            if(oos != null){
                oos.close();
            }
        }catch(IOException ioException){
            
        }
    }
    
    public static void initLoadStream(){
        try{
            ois = new ObjectInputStream(Files.newInputStream(Paths.get("Account.dat")));
        }catch(IOException ioException){
            System.exit(1);
        }
    }
    
    public static void loadID(){
        int i=0;
        try{
            while(true){
                userName[i]=(String) ois.readObject();
                System.out.println(userName[i]);
                i++;
                totalUsers=i;
            }
        }catch(EOFException endOfFileException){
            
        }catch(ClassNotFoundException classNotFoundException){
            
        }catch(IOException ioException){
            
        }
    }
    
    public static void closeLoadStream(){
        try{
            if(ois!=null)ois.close();
        }catch(IOException ioException){
            System.exit(1);
        }
    }
}
