/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import Database.DatabaseManager;
import Server.ClientConnector;
import Server.ClientSender;
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
    //public int decDelay=0;
    
    private boolean test=false;
    private boolean frame=true;
    
    private Timer dropDown;


    public Testing() {
  
        bi = new BufferedImage(Initializer.WINDOW_WIDTH, Initializer.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        this.addKeyListener(this);
        this.setSize(Initializer.WINDOW_WIDTH, Initializer.WINDOW_HEIGHT); 
        this.setTitle("TETRIS COMBO");
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        if(LoginScreen.offline)this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);  
        this.setLocationRelativeTo(null);

        Initializer.start=false;
        Initializer.end=false;

        //NextBlocks.setSpecificBlock(1, 6);
        //BlockManager.bm.setNextBlockToDisplay();
        //SoundManager.sm.playBackground(0);
        if(Initializer.multiplay)test=true;

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
                if(INIT_TIME_DELAY>timeDelay+Initializer.decDelay){
                    timeDelay++;
                }else{
                    BlockManager.bm.setBlockMoveDown();
                    if(Initializer.decDelay<=750)Initializer.decDelay++;
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
                if(Initializer.end){
                    dropDown.cancel();
                }
            }
        }, 0, 1);
    }
    
    
    public void run() {

       try {


           moveDelay = 0;

           while(!Initializer.over) {

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
        //if(init){
            gs.drawImage(imageManager.getBackgroundImage(), 0, 0, this);
            gs.drawImage(imageManager.getOutsideFrame(), Initializer.BLOCK_START_X-10, Initializer.BLOCK_START_Y-10, this);
            init=false;
        //}
        
        if(frame){
            fps.checkFrame();
            fps.setFrame();
            gs.fillRect(10, 40, 70, 15);
            gs.setColor(Color.BLACK);
            gs.drawString("FPS: "+fps.getFramePerSecond(), 20, 50);//Show Frame Per Second
        }
        
        //Main playground
        for(int i=1;i<Initializer.BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<Initializer.BLOCK_NUM_WIDTH;k++){
                gs.drawImage(imageManager.getBlockColorImage(BlockStatus.blocks[k][i].getBlockColor()), 
                                                            Initializer.BLOCK_START_X+(k*Initializer.BLOCK_SIZE_X),
                                                            Initializer.BLOCK_START_Y+((i-1)*Initializer.BLOCK_SIZE_Y), this);

            }
        }
        
        //multiplayer
        if(test){
            
            //enemyblock
            gs.drawImage(imageManager.getOutsideFrame(), Initializer.ENEMYBLOCK_START_X-10, Initializer.ENEMYBLOCK_START_Y-10, this);
            for(int i=1;i<Initializer.BLOCK_NUM_HEIGHT;i++){
                for(int k=0;k<Initializer.BLOCK_NUM_WIDTH;k++){
                    gs.drawImage(imageManager.getBlockColorImage(BlockStatus.enemyblocks[k][i].getBlockColor()), 
                                                                Initializer.ENEMYBLOCK_START_X+(k*Initializer.BLOCK_SIZE_X),
                                                                Initializer.ENEMYBLOCK_START_Y+((i-1)*Initializer.BLOCK_SIZE_Y), this);
                }
            }
            
            //count
            /*
            gs.drawImage(imageManager.getButtonImage(0), Initializer.BLOCK_START_X+45,  Initializer.BLOCK_START_Y-67, this);
            gs.drawImage(imageManager.getButtonImage(0), Initializer.BLOCK_START_X+115, Initializer.BLOCK_START_Y-67, this);
            gs.drawImage(imageManager.getButtonImage(0), Initializer.BLOCK_START_X+185, Initializer.BLOCK_START_Y-67, this);
            gs.drawImage(imageManager.getButtonImage(0), Initializer.BLOCK_START_X+255, Initializer.BLOCK_START_Y-67, this);
            
            gs.drawImage(imageManager.getButtonImage(0), Initializer.ENEMYBLOCK_START_X+45,  Initializer.BLOCK_START_Y-67, this);
            gs.drawImage(imageManager.getButtonImage(0), Initializer.ENEMYBLOCK_START_X+115, Initializer.BLOCK_START_Y-67, this);
            gs.drawImage(imageManager.getButtonImage(0), Initializer.ENEMYBLOCK_START_X+185, Initializer.BLOCK_START_Y-67, this);
            gs.drawImage(imageManager.getButtonImage(0), Initializer.ENEMYBLOCK_START_X+255, Initializer.BLOCK_START_Y-67, this);
            */
            
            //Timer
            gs.drawImage(imageManager.getTimestampFrame(), Initializer.BLOCK_START_X+400, Initializer.BLOCK_START_Y, this);
            
            //Name
            gs.drawImage(imageManager.getVersusImage(), Initializer.BLOCK_START_X+400, Initializer.BLOCK_START_Y-50, this);
        }
        
        
        //Score
        if(!test){
            gs.fillRect(400, 75, 250, 50);
            gs.drawImage(imageManager.getScoreBoxFrame(), Initializer.BLOCK_START_X+375, Initializer.BLOCK_START_Y, this);
            //gs.setColor(Color.BLACK);
            //gs.drawString(String.valueOf(BlockManager.bm.score), 425, 100);
            imageManager.setScoreImage(BlockManager.bm.score);
            for(int i=0;i<8;i++){
                if(imageManager.scoreImage[i]!=null)gs.drawImage(imageManager.scoreImage[i], Initializer.BLOCK_START_X+525-(i*20), Initializer.BLOCK_START_Y+5, this);
            }
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
            if(Initializer.multiplay){
                if(Initializer.winner){
                    gs.drawImage(imageManager.getWinnerImage(), Initializer.BLOCK_START_X, Initializer.BLOCK_START_Y+225, this);
                }else{
                    gs.drawImage(imageManager.getLoserImage(), Initializer.BLOCK_START_X, Initializer.BLOCK_START_Y+225, this);
                }
            }else{
                gs.drawImage(imageManager.getGameOverImage(), Initializer.BLOCK_START_X, Initializer.BLOCK_START_Y+225, this);
            }
        }
        
        if(!Initializer.start&&Initializer.ready){
            gs.drawImage(imageManager.getStartImage(),Initializer.BLOCK_START_X,Initializer.BLOCK_START_Y+325, this);
        }
        
        if(!Initializer.start&&!Initializer.ready){
            gs.drawImage(imageManager.getReadyImage(),Initializer.BLOCK_START_X,Initializer.BLOCK_START_Y+325, this);
        }
        
        if(Initializer.multiplay&&Initializer.reset){
            resetBlock();
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

    public void resetBlock(){
        new Initializer();
        Initializer.ready=false;
        Initializer.start=true;
        Initializer.end=false;
        Initializer.reset=false;
    }
    
    public void gameStart(){
        if(!Initializer.start){
            if(Initializer.multiplay){
                new Initializer();
                Initializer.ready=false;
                Initializer.start=true;
                Initializer.end=false;
                Initializer.reset=false;
                Initializer.decDelay=0;
                BlockManager.bm.setNextBlockToDisplay();
                initTimer();
            }else{
                new Initializer();
                Initializer.ready=true;
                Initializer.start=true;
                Initializer.end=false;
                Initializer.decDelay=0;
                BlockManager.bm.setNextBlockToDisplay();
                initTimer();
            }
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
                if(Initializer.multiplay){
                    ClientConnector.cc.clientSender = new ClientSender(ClientConnector.cc.socket,ClientConnector.cc.name,ClientConnector.cc.nickname,'R',Initializer.room,'S');
                    ClientConnector.cc.clientSender.text="<"+DatabaseManager.dbm.internal_id+">"+"Request to start game";
                    ClientConnector.cc.clientSender.start();
                }else{
                    gameStart();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if(!Initializer.start&&!LoginScreen.offline){
                    if(Initializer.multiplay){
                        ClientConnector.cc.clientSender = new ClientSender(ClientConnector.cc.socket,ClientConnector.cc.name,ClientConnector.cc.nickname,'R',Initializer.room,'Q');
                        ClientConnector.cc.clientSender.text="<"+DatabaseManager.dbm.internal_id+">"+"Request to quit";
                        ClientConnector.cc.clientSender.start();
                    }
                    Initializer.over=true;
                    soundManager.stopBackground();
                    RoomScreen1.rs.returnLobby();
                    this.removeAll();
                    this.dispose();
                }
                break;
            case KeyEvent.VK_T:
                if(test)test=false;
                else test=true;
                init=false;
                break;
            case KeyEvent.VK_F:
                if(frame)frame=false;
                else frame=true;
                break;
            case KeyEvent.VK_R:
                if(Initializer.multiplay){
                    if(!Initializer.ready){
                        Initializer.ready=true;
                        Initializer.end=false;
                        ClientConnector.cc.clientSender = new ClientSender(ClientConnector.cc.socket,ClientConnector.cc.name,ClientConnector.cc.nickname,'R',Initializer.room,'R');
                        ClientConnector.cc.clientSender.text="<"+DatabaseManager.dbm.internal_id+">"+"ready";
                        ClientConnector.cc.clientSender.start();
                        System.out.println("ready");
                    }else{
                        Initializer.ready=false;
                        Initializer.end=false;
                        ClientConnector.cc.clientSender = new ClientSender(ClientConnector.cc.socket,ClientConnector.cc.name,ClientConnector.cc.nickname,'R',Initializer.room,'B');
                        ClientConnector.cc.clientSender.text="<"+DatabaseManager.dbm.internal_id+">"+"ready";
                        ClientConnector.cc.clientSender.start();
                    }
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
