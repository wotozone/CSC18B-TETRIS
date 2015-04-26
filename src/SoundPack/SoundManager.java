/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoundPack;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



/**
 *
 * @author minjikim
 */
public class SoundManager {
    
    public SoundLoader soundLoader;
    
    public AudioInputStream currentBGM=null;
    
    public SoundManager() throws Exception{
        soundLoader=new SoundLoader();
    }
    
    /*
    public void playComboSound(int value) throws IOException{
        Clip clip;
        try {
            clip = AudioSystem.getClip();
            switch(value){
                case(0):clip.open(soundLoader.COMBO1);break;
                case(1):clip.open(soundLoader.COMBO2);break;
                case(2):clip.open(soundLoader.COMBO3);break;
                default:
                    System.out.println("No Sound");
                    clip.open(soundLoader.COMBO1);
                    break;
            }
            clip.start();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
        
    }
    */
    
    public void playBackground() throws IOException, UnsupportedAudioFileException{
        Clip clip;
        try {
            InputStream bufferedIn = new BufferedInputStream(soundLoader.BACKGROUND);
            AudioInputStream ais;
            ais = AudioSystem.getAudioInputStream(soundLoader.BACKGROUND);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    /*
    public void playWooHoo() throws IOException{
        Clip clip;
        try {
            clip = AudioSystem.getClip();
            clip.open(soundLoader.WOOHOO);
            clip.start();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
    */
    //public Media getGameOverImage(){
    //    return imageLoader.GAME_OVER;
    //}
}
