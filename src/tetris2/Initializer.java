/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

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
    public static final int BLOCK_START_Y=75;
    //#of blocks each
    public static final int BLOCK_NUM_WIDTH=10;
    public static final int BLOCK_NUM_HEIGHT=21;
    
    //size of window
    public static final int WINDOW_WIDTH=700;
    public static final int WINDOW_HEIGHT=800;
    
    public static boolean start = false;
    public static boolean end = false;
    
    public Initializer(){
        initBlocks();
        //frame = new JFrame("TETRIS");
        //mainScreen = new MainScreen(frame);
        //thread = new Thread(mainScreen);
        //thread.start();
    }
    
    
    private void initBlocks(){
        for(int i=0;i<BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<BLOCK_NUM_WIDTH;k++){
                BlockStatus.blocks[k][i] = new BlockStatus();
                BlockStatus.blocks[k][i].setBlockColor(7);
                BlockStatus.blocks[k][i].setBlockEmpty();
            }
        }
        NextBlocks.initBlocks();
    }
}
