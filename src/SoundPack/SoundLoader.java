/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoundPack;

import java.io.File;


/**
 *
 * @author minjikim
 */
public class SoundLoader {
    
    //BACKGROUND MUSIC
    public final File BACKGROUND1;
    public final File BACKGROUND2;
    public final File BACKGROUND3;
    public final File BACKGROUND4;
    
    //LOBBY MUSIC
    public final File LOBBY_INTRO;
    public final File LOBBY_REFEAT;
    
    //COMBO SOUNDS
    public final File COMBO1;
    public final File COMBO2;
    public final File COMBO3;
    
    public final File COMBO100;
    public final File COMBO200;
    public final File COMBO300;
    public final File COMBO400;
    public final File COMBO500;
    public final File COMBO600;
    public final File COMBO700;
    public final File COMBO800;
    public final File COMBO900;
    
    //WOOHOO
    public final File WOOHOO;
    
    public SoundLoader() throws Exception{
        BACKGROUND1 = new File("Sounds/BACKGROUND1.wav");
        BACKGROUND2 = new File("Sounds/BACKGROUND2.wav");
        BACKGROUND3 = new File("Sounds/BACKGROUND3.wav");
        BACKGROUND4 = new File("Sounds/BACKGROUND4.wav");
        
        LOBBY_INTRO = new File("Sounds/ROOM_INTRO.wav");
        LOBBY_REFEAT = new File("Sounds/ROOM_REPEAT.wav");
        
        COMBO1 = new File("Sounds/COMBO1.wav");
        COMBO2 = new File("Sounds/COMBO2.wav");
        COMBO3 = new File("Sounds/COMBO3.wav");
        
        COMBO100 = new File("Sounds/COMBO100.wav");
        COMBO200 = new File("Sounds/COMBO200.wav");
        COMBO300 = new File("Sounds/COMBO300.wav");
        COMBO400 = new File("Sounds/COMBO400.wav");
        COMBO500 = new File("Sounds/COMBO500.wav");
        COMBO600 = new File("Sounds/COMBO600.wav");
        COMBO700 = new File("Sounds/COMBO700.wav");
        COMBO800 = new File("Sounds/COMBO800.wav");
        COMBO900 = new File("Sounds/COMBO900.wav");
        
        WOOHOO = new File("Sounds/WOOHOO.wav");
        
    }
    
}
