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
    public AudioClip lobbyBGM;
    
    public SoundManager() throws Exception{
        soundLoader=new SoundLoader();
    }
    
    public void playCombo(int count){
        AudioClip clip;
        try {
            switch(count){
                case 2:
                    clip = Applet.newAudioClip(soundLoader.COMBO1.toURL());
                    break;
                case 3:
                    clip = Applet.newAudioClip(soundLoader.COMBO2.toURL());
                    break;
                default:
                    clip = Applet.newAudioClip(soundLoader.COMBO3.toURL());
            }
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playAltCombo(int count){
        AudioClip clip;
        try {
            switch(count){
                case 2:
                    clip = Applet.newAudioClip(soundLoader.COMBO200.toURL());
                    break;
                case 3:
                    clip = Applet.newAudioClip(soundLoader.COMBO300.toURL());
                    break;
                case 4:
                    clip = Applet.newAudioClip(soundLoader.COMBO400.toURL());
                    break;
                case 5:
                    clip = Applet.newAudioClip(soundLoader.COMBO500.toURL());
                    break;
                case 6:
                    clip = Applet.newAudioClip(soundLoader.COMBO600.toURL());
                    break;
                case 7:
                    clip = Applet.newAudioClip(soundLoader.COMBO700.toURL());
                    break;
                case 8:
                    clip = Applet.newAudioClip(soundLoader.COMBO800.toURL());
                    break;
                case 9:
                    clip = Applet.newAudioClip(soundLoader.COMBO900.toURL());
                    break;
                default:
                    clip = Applet.newAudioClip(soundLoader.COMBO3.toURL());
            }
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playImpactSound(){
        AudioClip clip;
        try {
            clip = Applet.newAudioClip(soundLoader.WOOHOO.toURL());
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playBackground(int type){
        try {
            switch(type){
                case 1:
                    currentBGM = Applet.newAudioClip(soundLoader.BACKGROUND2.toURL());
                    break;
                case 2:
                    currentBGM = Applet.newAudioClip(soundLoader.BACKGROUND3.toURL());
                    break;
                case 3:
                    currentBGM = Applet.newAudioClip(soundLoader.BACKGROUND4.toURL());
                    break;
                default:
                    currentBGM = Applet.newAudioClip(soundLoader.BACKGROUND1.toURL());
            }
            currentBGM.loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stopBackground(){
        try {
            if(currentBGM!=null)currentBGM.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void playLobyBackground(int type){
        try {
            switch(type){
                case 1:
                    lobbyBGM = Applet.newAudioClip(soundLoader.LOBBY_INTRO.toURL());
                    lobbyBGM.play();
                    break;
                case 2:
                    lobbyBGM = Applet.newAudioClip(soundLoader.LOBBY_REFEAT.toURL());
                    lobbyBGM.loop();
                    break;
                case 3:
                    lobbyBGM.stop();
                    break;
                case 4:
                    lobbyBGM.loop();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
