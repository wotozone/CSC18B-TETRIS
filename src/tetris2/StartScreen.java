/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author minjikim
 */
public class StartScreen extends JFrame{
    
    //size of each block
    static final int BLOCK_SIZE_X=35;
    static final int BLOCK_SIZE_Y=35;
    //point where block start
    static final int BLOCK_START_X=25;
    static final int BLOCK_START_Y=75;
    //#of blocks each
    static final int BLOCK_NUM_WIDTH=10;
    static final int BLOCK_NUM_HEIGHT=20;
    
    //object of blocks
    public static BlockStatus[][] blocks = new BlockStatus[10][20];
    
    //timer
    private Timer blockDown;
    private int decasec=0;
    
    //test
    private int testing1=0;
    private int testing2=0;
    private int test=0;
    
    public StartScreen() {
        //setScreen
        setSize(600, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //initBlock
        initBlocks();
        initTimer();
        
        //addKeyListener
        this.addKeyListener(new ActionActivator());
        
        //StartGame
        initGame();
        
    }
    
    private void initGame(){
        NextBlocks.setSpecificBlock(1, 0);
        BlockManager.setNextBlockToDisplay();
    }
    
    private void initTimer(){
        blockDown = new Timer();
        /*
        blockDown.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                for(int i=0;i<StartScreen.BLOCK_NUM_HEIGHT;i++){
                    for(int k=0;k<StartScreen.BLOCK_NUM_WIDTH;k++){
                        blocks[k][i].setBlockColor(3);
                    }
                }
                if(test>7)test=0;
                repaint();
            }
        }, 0, 1);
        */
        blockDown.scheduleAtFixedRate(new TimerTask(){
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
                decasec++;
                if(decasec==10){
                    BlockManager.setBlockMoveDown();
                    decasec=0;
                }
                repaint();
            }
        }, 0, 1000);
    }
    
    private void initBlocks(){
        for(int i=0;i<BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<BLOCK_NUM_WIDTH;k++){
                blocks[k][i] = new BlockStatus();
                blocks[k][i].setBlockColor(0);
                blocks[k][i].setBlockEmpty();
            }
        }
        NextBlocks.initBlocks();
    }
    
    @Override
    public void paint(Graphics g) {
        
        //Background
        g.setColor(ColorManager.BACKGROUND);
        g.fillRect(0, 0, 600, 800);
        
        
        //Main playground
        g.setColor(ColorManager.FOREGROUND);
        g.fillRect(BLOCK_START_X-10, BLOCK_START_Y-10, 20+(BLOCK_SIZE_X*BLOCK_NUM_WIDTH), 20+(BLOCK_SIZE_Y*BLOCK_NUM_HEIGHT));
        
        for(int i=0;i<BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<BLOCK_NUM_WIDTH;k++){
                g.setColor(ColorManager.getBlockColor(blocks[k][i].getBlockColor()));
                g.fillRect(BLOCK_START_X+1+(k*BLOCK_SIZE_X), BLOCK_START_Y+1+(i*BLOCK_SIZE_Y), BLOCK_SIZE_X-1, BLOCK_SIZE_Y-1);
                g.setColor(ColorManager.BOXLINES);
                g.drawRect(BLOCK_START_X+(k*BLOCK_SIZE_X), BLOCK_START_Y+(i*BLOCK_SIZE_Y), BLOCK_SIZE_X, BLOCK_SIZE_Y);
            }
        }
        
        //Next blocks
        for(int i=0;i<5;i++){
            g.setColor(ColorManager.NEXTBOX);
            g.fillRect(400, 75+(i*120), 100, 100);
        }
        
    }

    
    
}
