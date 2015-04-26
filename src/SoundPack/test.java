/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoundPack;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static tetris2.ObjectStream.ois;

/**
 *
 * @author Tae
 */
public class test {
    
    public test(){
        AudioClip clip;
        try {
            InputStream BACKGROUND = getClass().getResourceAsStream("Sounds/BACKGROUND.mp3");
            File file = new File("Sounds/BACKGROUND.wav");
            clip = Applet.newAudioClip(file.toURL());
            clip.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
