/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoundPack;

import javafx.scene.media.Media;

/**
 *
 * @author minjikim
 */
public class SoundManager {
    
    public SoundLoader soundLoader;
    
    public SoundManager(){
        soundLoader=new SoundLoader();
    }
    
    public Media getComboSound(int value){
        switch(value){
            case(0):return soundLoader.COMBO1;
            case(1):return soundLoader.COMBO2;
            case(2):return soundLoader.COMBO3;
            default:
                System.out.println("No Sound");
                return soundLoader.COMBO1;
        }
        
    }
    
    public Media getBackgroundImage(){
        return soundLoader.BACKGROUND;
    }
    
    public Media getWooHoo(){
        return soundLoader.WOOHOO;
    }
    
    //public Media getGameOverImage(){
    //    return imageLoader.GAME_OVER;
    //}
}
