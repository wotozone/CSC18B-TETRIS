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
public class SoundLoader {
    
    //BACKGROUND MUSIC
    public final Media BACKGROUND;
    
    //COMBO SOUNDS
    public final Media COMBO1;
    public final Media COMBO2;
    public final Media COMBO3;
    
    //WOOHOO
    public final Media WOOHOO;
    
    public SoundLoader(){
        BACKGROUND= new Media("Sounds/BACKGROUND.mp3");
        
        COMBO1= new Media("Sounds/COMBO1.mp3");
        COMBO2= new Media("Sounds/COMBO2.mp3");
        COMBO3= new Media("Sounds/COMBO3.mp3");
        
        WOOHOO= new Media("Sounds/WOOHOO.mp3");
    }
    
}
