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
    public final File BACKGROUND;
    
    //COMBO SOUNDS
    public final File COMBO1;
    public final File COMBO2;
    public final File COMBO3;
    
    //WOOHOO
    public final File WOOHOO;
    
    public SoundLoader() throws Exception{
        BACKGROUND = new File("Sounds/BACKGROUND.wav");
        
        COMBO1 = new File("Sounds/COMBO1.wav");
        COMBO2 = new File("Sounds/COMBO2.wav");
        COMBO3 = new File("Sounds/COMBO3.wav");
        
        WOOHOO = new File("Sounds/WOOHOO.wav");
    }
    
}
