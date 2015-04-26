/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoundPack;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.InputStream;




/**
 *
 * @author minjikim
 */
public class SoundManager {
    
    public SoundLoader soundLoader;
    
    public AudioClip currentBGM;
    
    public SoundManager() throws Exception{
        soundLoader=new SoundLoader();
    }
    
    public void playCombo(int count){
        AudioClip clip;
        try {
            switch(count){
                case 2:
                    clip = Applet.newAudioClip(soundLoader.COMBO1.toURL());
                case 3:
                    clip = Applet.newAudioClip(soundLoader.COMBO2.toURL());
                default:
                    clip = Applet.newAudioClip(soundLoader.COMBO3.toURL());
            }
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playBackground(){
        try {
            currentBGM = Applet.newAudioClip(soundLoader.BACKGROUND.toURL());
            currentBGM.loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
