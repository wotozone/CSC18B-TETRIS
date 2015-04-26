/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoundPack;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author minjikim
 */
public class SoundLoader {
    
    //BACKGROUND MUSIC
    public final InputStream BACKGROUND;
    
    //COMBO SOUNDS
    //public final AudioInputStream COMBO1;
    //public final AudioInputStream COMBO2;
    //public final AudioInputStream COMBO3;
    
    //WOOHOO
    //public final AudioInputStream WOOHOO;
    
    public SoundLoader() throws Exception{
        BACKGROUND = getClass().getResourceAsStream("Sounds/BACKGROUND.mp3");
        //BACKGROUND = AudioSystem.getAudioInputStream(audioSrc);
        
        /*
        audioSrc = getClass().getResourceAsStream("Sounds/COMBO1.mp3");
        bufferedIn = new BufferedInputStream(audioSrc);
        COMBO1= AudioSystem.getAudioInputStream(new FileInputStream("audioSrc"));
        
        audioSrc = getClass().getResourceAsStream("Sounds/COMBO2.mp3");
        bufferedIn = new BufferedInputStream(audioSrc);
        COMBO2= AudioSystem.getAudioInputStream(new FileInputStream("audioSrc"));
        
        audioSrc = getClass().getResourceAsStream("Sounds/COMBO3.mp3");
        bufferedIn = new BufferedInputStream(audioSrc);
        COMBO3= AudioSystem.getAudioInputStream(new FileInputStream("audioSrc"));
        
        
        audioSrc = getClass().getResourceAsStream("Sounds/WOOHOO.mp3");
        bufferedIn = new BufferedInputStream(audioSrc);
        WOOHOO= AudioSystem.getAudioInputStream(new FileInputStream("audioSrc"));
        */
    }
    
}
