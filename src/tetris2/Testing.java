/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import SoundPack.SoundManager;
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
    public static final int BLOCK_START_X=25;
    public static final int BLOCK_START_Y=75;
    //#of blocks each
    public static final int BLOCK_NUM_WIDTH=10;
    public static final int BLOCK_NUM_HEIGHT=20;
    
    //size of window
    public static final int WINDOW_WIDTH=700;
    public static final int WINDOW_HEIGHT=800;
    
    //other constants
    public static final int INIT_TIME_DELAY=1000;
    
    private BufferedImage bi = null;

    private boolean left = false, right = false, down = false;
    
    private int moveDelay;
    private int dropDelay;
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
            soundManager.playBackground();
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
                if(INIT_TIME_DELAY>timeDelay+decDelay){
                    timeDelay++;
                }else{
                    BlockManager.bm.setBlockMoveDown();
                    if(decDelay<=750)decDelay++;
                    timeDelay=0;
                    System.out.println("Delay: "+(1000-decDelay));
                }
            }
        }, 0, 1);
    }
 public void run() {

    try {

        
        moveDelay = 0;
        dropDelay = 0;

        while(true) {

            Thread.sleep(1);//optimizing fps
            
            if(Initializer.start) {
                if(moveDelay>=10){//move per 0.1sec
                    keyControl();
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
        gs.drawImage(imageManager.getBackgroundImage(), 0, 0, this);

        fps.checkFrame();
        fps.setFrame();
        gs.drawString("FPS: "+fps.getFramePerSecond(), 20, 50);//Show Frame Per Second
        
        //Main playground
        gs.drawImage(imageManager.getOutsideFrame(), Initializer.BLOCK_START_X-10, Initializer.BLOCK_START_Y-10, this);
        for(int i=0;i<Initializer.BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<Initializer.BLOCK_NUM_WIDTH;k++){
                gs.drawImage(imageManager.getBlockColorImage(BlockStatus.blocks[k][i].getBlockColor()), 
                                                            Initializer.BLOCK_START_X+(k*Initializer.BLOCK_SIZE_X),
                                                            Initializer.BLOCK_START_Y+(i*Initializer.BLOCK_SIZE_Y), this);

            }
        }


        //Next blocks
        for(int i=0;i<5;i++){
            gs.setColor(ColorManager.NEXTBOX);
            gs.fillRect(400, 75+(i*120), 100, 100);
        }
        
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
        }else if(right){
            BlockManager.bm.setBlockMove(2);
            moveDelay=0;
        }else if(down){
            BlockManager.bm.setBlockMove(0);
            moveDelay=0;
        }
    }

    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_LEFT:  
                left = true;       
                break;
            case KeyEvent.VK_RIGHT: 
                right = true;      
                break;
            case KeyEvent.VK_UP:   
                BlockManager.bm.setBlockRotate();
                break;
            case KeyEvent.VK_DOWN:   
                down = true;      
                break;
            case KeyEvent.VK_SPACE:
                BlockManager.bm.setQuickDown();
                break;
            case KeyEvent.VK_CONTROL:
                System.out.println("sdf");
                BlockManager.bm.holdBlock();
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
