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


public class Testing extends JFrame implements Runnable, KeyListener{
 
    public ImageManager imageManager;
    public SoundManager soundManager;
    public FPSManager fps;
    
    public static final int BLOCK_SIZE_X=35;
    public static final int BLOCK_SIZE_Y=35;
    //point where block start
    public static final int BLOCK_START_X=225;
    public static final int BLOCK_START_Y=75;
    //#of blocks each
    
    //size of window
    public static final int WINDOW_WIDTH=900;
    public static final int WINDOW_HEIGHT=800;
    
    //other constants
    public static final int INIT_TIME_DELAY=1000;
    
    private BufferedImage bi = null;
    
    private boolean init = true;
    private boolean left = false, right = false, down = false;
    
    private int moveDelay;
    private int controlDelay=0;
    private int timeDelay=0;
    private int decDelay=0;
    
    private Timer dropDown;


    public Testing() {
  
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
        initTimer();
        

        fps = new FPSManager();
        try {
            imageManager = new ImageManager();
            soundManager = new SoundManager();
            soundManager.playBackground(1);
            //test t = new test();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initTimer(){
        dropDown = new Timer();
        dropDown.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                /*
                for(int i=0;i<StartScreen.BLOCK_NUM_HEIGHT;i++){
                    for(int k=0;k<StartScreen.BLOCK_NUM_WIDTH;k++){
                        blocks[k][i].setBlockColor(test);
                    }
                }
                if(test>7)test=0;
                test++;
                */
                if(controlDelay>100){
                    keyControl();
                }else{
                    controlDelay++;
                }
                if(INIT_TIME_DELAY>timeDelay+decDelay){
                    timeDelay++;
                }else{
                    BlockManager.bm.setBlockMoveDown();
                    if(decDelay<=750)decDelay++;
                    timeDelay=0;
                    //System.out.println("Delay: "+(1000-decDelay));
                }
                if(BlockManager.bm.soundOn){
                    if(BlockManager.bm.soundType==0){
                        soundManager.playImpactSound();
                    }else{
                        soundManager.playCombo(BlockManager.bm.soundType);
                    }
                    BlockManager.bm.soundOn=false;
                }
            }
        }, 0, 1);
    }
 public void run() {

    try {

        
        moveDelay = 0;

        while(true) {

            Thread.sleep(1);//optimizing fps
            
            if(Initializer.start) {
                if(moveDelay>=5){//move per 0.1sec
                    //keyControl();
                }else{
                    moveDelay+=1;
                }
            }
            draw();
        }  
        }catch(Exception e){
            e.printStackTrace(); 
    } 
}


    public void draw() {

        Graphics gs = bi.getGraphics();
        if(init){
            gs.drawImage(imageManager.getBackgroundImage(), 0, 0, this);
            gs.drawImage(imageManager.getOutsideFrame(), Initializer.BLOCK_START_X-10, Initializer.BLOCK_START_Y-10, this);
            init=false;
        }

        fps.checkFrame();
        fps.setFrame();
        gs.fillRect(10, 40, 70, 15);
        gs.setColor(Color.BLACK);
        gs.drawString("FPS: "+fps.getFramePerSecond(), 20, 50);//Show Frame Per Second
        
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

    public void keyControl() {
        if(left){
            BlockManager.bm.setBlockMove(1);
            moveDelay=0;
            controlDelay=0;
        }else if(right){
            BlockManager.bm.setBlockMove(2);
            moveDelay=0;
            controlDelay=0;
        }else if(down){
            BlockManager.bm.setBlockMove(0);
            moveDelay=0;
            controlDelay=0;
        }
    }

    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_LEFT:  
                BlockManager.bm.setBlockMove(1);
                moveDelay=0;
                controlDelay=0;
                left = true;       
                break;
            case KeyEvent.VK_RIGHT: 
                BlockManager.bm.setBlockMove(2);
                moveDelay=0;
                controlDelay=0;
                right = true;      
                break;
            case KeyEvent.VK_UP:   
                BlockManager.bm.setBlockRotate();
                break;
            case KeyEvent.VK_DOWN:   
                BlockManager.bm.setBlockMove(0);
                moveDelay=0;
                controlDelay=0;
                down = true;      
                break;
            case KeyEvent.VK_SPACE:
                BlockManager.bm.setQuickDown();
                break;
            case KeyEvent.VK_SHIFT:
                BlockManager.bm.holdBlock();
                break;
            case KeyEvent.VK_ENTER:
                if(Initializer.end){
                    new Initializer();
                    Initializer.start=true;
                    Initializer.end=false;
                    decDelay=0;
                    BlockManager.bm.setNextBlockToDisplay();
                }
                break;
            } 
   }

    public void keyReleased(KeyEvent ke){
    switch(ke.getKeyCode()) 
        {
            case KeyEvent.VK_LEFT: 
                left = false;  
                break;
            case KeyEvent.VK_RIGHT: 
                right = false; 
                break;
            case KeyEvent.VK_DOWN:
                down = false;  
                break;
        } 
    } 

    public void keyTyped(KeyEvent ke) {

    }
}
