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
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 *
 * @author minjikim
 */
public class ObjectStream {
    
    public static int totalUsers=0;
    public static UserInfo us;
    public static ObjectOutputStream oos = null;
    public static ObjectInputStream ois = null;
    
    public static boolean initsaveStream(String name){
        Path path = Paths.get(name+".dat");
        if(Files.exists(path)&&isDummy(name)==false){
            continueSaveStream(name);
            return false;
        }else{
            createSaveStream(name);
            return true;
        }
    }
    
    public static boolean isDummy(String name){
        return false;
    }
    
    public static void continueSaveStream(String name){
        //Path path = Paths.get("Account.dat");
        //if(Files.exists(path)){}
        try{
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(name+".dat"),APPEND));
            
        }catch(IOException e){
            System.exit(1);
        }
        
    }
    
    private static void createSaveStream(String name){
        try{
            //fos = new FileOutputStream(Files.newOutputStream(Paths.get("Accounts.dat")));
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(name+".dat"),CREATE_NEW));
            
        }catch(IOException e){
            System.exit(1);
        }
    }
    
    public static void saveID(String name, String password, int point){
        us = new UserInfo(name,password,point);
        try{
            oos.writeObject(us);
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
    
    public static void initLoadStream(String name){
        try{
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(name+".dat"),APPEND));
        }catch(IOException ioException){
            System.exit(1);
        }
    }
    
    public static void loadID(){
        int i=0;
        try{
            us=(UserInfo)ois.readObject();
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
