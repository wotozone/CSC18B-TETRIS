/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import blockShape.BlockManager;
import java.awt.Canvas;
import javax.swing.JFrame;

/**
 *
 * @author Tae
 */
public class Initializer {
    
    
    public JFrame frame;
    public MainScreen mainScreen;
    public Canvas canvas;
    public Thread thread;
    
    public static final int BLOCK_SIZE_X=35;
    public static final int BLOCK_SIZE_Y=35;
    //point where block start
    public static final int BLOCK_START_X=25;
    public static final int BLOCK_START_Y=100;
    //point where block start
    public static final int ENEMYBLOCK_START_X=625;
    public static final int ENEMYBLOCK_START_Y=100;
    //#of blocks each
    public static final int BLOCK_NUM_WIDTH=10;
    public static final int BLOCK_NUM_HEIGHT=21;
    
    //size of window
    public static final int WINDOW_WIDTH=1000;
    public static final int WINDOW_HEIGHT=850;
    
    public static boolean ready = false;
    public static boolean start = false;
    public static boolean end = false;
    public static boolean over = false;
    public static boolean multiplay = false;
    
    public static char room = '0';
    public static int time=0;
    
    public static int myStack=0;
    public static int enemyStack=0;
    
    public static int myKnockdown=0;
    public static int enemyKnockdown=0;
    
    public static int myScore=0;
    public static int enemyScore=0;
    
    public static boolean reset = false;
    public static boolean winner = true;
    public static boolean enemyReady = false;
    
    
    public static int decDelay=0;
    
    public Initializer(){
        initBlocks();
        //frame = new JFrame("TETRIS");
        //mainScreen = new MainScreen(frame);
        //thread = new Thread(mainScreen);
        //thread.start();
        initData();
    }
    
    private void initData(){
        myStack=0;
        myKnockdown=0;
        myScore=0;
        enemyStack=0;
        enemyKnockdown=0;
        enemyScore=0;
    }
    
    private void initBlocks(){
        for(int i=0;i<BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<BLOCK_NUM_WIDTH;k++){
                BlockStatus.blocks[k][i] = new BlockStatus();
                BlockStatus.blocks[k][i].setBlockColor(7);
                BlockStatus.blocks[k][i].setBlockEmpty();
                BlockStatus.enemyblocks[k][i] = new BlockStatus();
                BlockStatus.enemyblocks[k][i].setBlockColor(7);
                BlockStatus.enemyblocks[k][i].setBlockEmpty();
            }
        }
        NextBlocks.initBlocks();
        BlockManager.bm.firstHold=true;
        BlockManager.bm.holdBlock=0;
        BlockManager.bm.holdable=true;
        BlockManager.bm.currentBlock=0;
        BlockManager.bm.score=0;
    }
}
