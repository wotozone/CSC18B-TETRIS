/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import SoundPack.SoundManager;
import SoundPack.test;
import blockShape.BlockManager;
import imageRendering.ImageManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;


public class LobbyScreen extends JFrame implements Runnable, KeyListener, MouseListener{
 
    public ImageManager imageManager;
    public SoundManager soundManager;
    
    
    //size of window
    public static final int WINDOW_WIDTH=700;
    public static final int WINDOW_HEIGHT=700;
    
    //other constants
    
    private BufferedImage bi = null;
    
    public LobbyScreen() {
  
        bi = new BufferedImage(Initializer.WINDOW_WIDTH, Initializer.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        this.addKeyListener(this);
        this.setSize(Initializer.WINDOW_WIDTH, Initializer.WINDOW_HEIGHT); 
        this.setTitle("TETRIS");
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);  
        this.setLocationRelativeTo(null);
        
        Initializer.start=true;
        Initializer.end=false;

        //NextBlocks.setSpecificBlock(1, 6);
        BlockManager.bm.setNextBlockToDisplay();
        //SoundManager.sm.playBackground(0);
        

        try {
            imageManager = new ImageManager();
            soundManager = new SoundManager();
            soundManager.playBackground(1);
            //test t = new test();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 public void run() {

    try {

        

        while(true) {

            Thread.sleep(1);//optimizing fps
            draw();
        }  
        }catch(Exception e){
            e.printStackTrace(); 
    } 
}


    public void draw() {

        Graphics gs = bi.getGraphics();

        gs.fillRect(10, 40, 70, 15);
        gs.setColor(Color.BLACK);
        
        //Main playground
        for(int i=1;i<Initializer.BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<Initializer.BLOCK_NUM_WIDTH;k++){
                gs.drawImage(imageManager.getBlockColorImage(BlockStatus.blocks[k][i].getBlockColor()), 
                                                            Initializer.BLOCK_START_X+(k*Initializer.BLOCK_SIZE_X),
                                                            Initializer.BLOCK_START_Y+((i-1)*Initializer.BLOCK_SIZE_Y), this);

            }
        }
        
        
        //Score
        gs.fillRect(400, 75, 250, 50);
        gs.drawImage(imageManager.getScoreBoxFrame(), Initializer.BLOCK_START_X+375, Initializer.BLOCK_START_Y, this);
        //gs.setColor(Color.BLACK);
        //gs.drawString(String.valueOf(BlockManager.bm.score), 425, 100);
        imageManager.setScoreImage(BlockManager.bm.score);
        for(int i=0;i<8;i++){
            if(imageManager.scoreImage[i]!=null)gs.drawImage(imageManager.scoreImage[i], 550-(i*20), 80, this);
        }
        
        //Next blocks
        for(int i=0;i<4;i++){
            gs.setColor(ColorManager.NEXTBOX);
            gs.fillRect(Initializer.BLOCK_START_X+375, (Initializer.BLOCK_START_Y+75)+(i*120), 100, 100);
            gs.drawImage(imageManager.getNextBlockFrame(), Initializer.BLOCK_START_X+375, (Initializer.BLOCK_START_Y+75)+(i*120), this);
            gs.drawImage(imageManager.getNextBlockImage(NextBlocks.getSpecificBlock(i+1)), Initializer.BLOCK_START_X+375, (Initializer.BLOCK_START_Y+75)+(i*120), this);
        }
        
        //hold block frame
        gs.drawImage(imageManager.getHoldBlockFrame(), Initializer.BLOCK_START_X+375, Initializer.BLOCK_START_Y+600, this);
        if(!BlockManager.bm.firstHold)gs.drawImage(imageManager.getNextBlockImage(BlockManager.bm.holdBlock), Initializer.BLOCK_START_X+375, Initializer.BLOCK_START_Y+610, this);
        
        //When Game has ended
        if(Initializer.end){
            //gs.setColor(Color.WHITE);
            //gs.drawString("GAME OVER", 25+(35*4), 75+(35*10));//Show GAME OVER
            gs.drawImage(imageManager.getGameOverImage(), 25, 425, this);
        }
        
        Graphics ge = this.getGraphics();

        ge.drawImage(bi, 0, 0, Initializer.WINDOW_WIDTH, Initializer.WINDOW_HEIGHT, this);
    }


    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_LEFT:  
                break;
            case KeyEvent.VK_RIGHT: 
                break;
            case KeyEvent.VK_UP:   
                break;
            case KeyEvent.VK_DOWN:   
                break;
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_SHIFT:
                break;
            case KeyEvent.VK_ENTER:
                break;
            } 
   }

    public void keyReleased(KeyEvent ke){
    switch(ke.getKeyCode()) 
        {
            case KeyEvent.VK_LEFT: 
                break;
            case KeyEvent.VK_RIGHT: 
                break;
            case KeyEvent.VK_DOWN:
                break;
        } 
    } 

    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
