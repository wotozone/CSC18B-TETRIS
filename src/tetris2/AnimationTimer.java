/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author minjikim
 */
public class AnimationTimer extends TimerTask{

    public int test=0;
    
    @Override
    public void run() {
        
        for(int i=0;i<StartScreen.BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<StartScreen.BLOCK_NUM_WIDTH;k++){
                StartScreen.blocks[k][i].setBlockColor(test);
            }
        }
        if(test>7)test=0;
    }
}
