/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.*;
import ImageRender.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Tae
 */
public class MainCanvas {
    
    //System Class
    public JFrame frame;
    public Canvas canvas;
    public BufferStrategy buffStg;
    
    //Custom Class
    public DropDownThread dropDownThread;
    public ImageManager imageManager;
    public FPSManager fps;

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

    //Images
    

    public MainCanvas(){
            frame = new JFrame("TETRIS");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            canvas = new Canvas();
            canvas.setBackground(Color.WHITE);
            canvas.setPreferredSize(new Dimension(700, 800));
            frame.add(canvas);
            

            //Load buffered image
            canvas.createBufferStrategy(2);
            buffStg = canvas.getBufferStrategy();

            //Rest of'em
            frame.pack();

            frame.setLocationRelativeTo(null);
            frame.setAlwaysOnTop(true);
            frame.setResizable(false);
            
            
            /*
            ####################################################################
            ###                                                              ###
            ###      INITIALIZING GAME                                       ###
            ###                                                              ###
            ####################################################################
            */
            
            //initBlock
            initBlocks();

            //addKeyListener
            frame.addKeyListener(new ActionActivator());

            //StartGame
            initGame();
            
            //setUp
            dropDownThread = new DropDownThread();
            dropDownThread.start();
            
            /*
            ####################################################################
            ###                                                              ###
            ###                   DRAW IMAGES                                ###
            ###                                                              ###
            ####################################################################
            */
            
            //Draw image
            while(true){
                Graphics g = buffStg.getDrawGraphics();
                g.clearRect(0, 0, 700, 800);//Clear the image

                //Background
                g.drawImage(imageManager.getBackgroundImage(), 0, 0, canvas);
                
                //Main playground
                g.drawImage(imageManager.getOutsideFrame(), BLOCK_START_X-10, BLOCK_START_Y-10, canvas);
                for(int i=0;i<BLOCK_NUM_HEIGHT;i++){
                    for(int k=0;k<BLOCK_NUM_WIDTH;k++){
                        g.drawImage(imageManager.getBlockColorImage(blocks[k][i].getBlockColor()), 
                                                                    BLOCK_START_X+(k*BLOCK_SIZE_X), BLOCK_START_Y+(i*BLOCK_SIZE_Y), canvas);
                        
                    }
                }
                
                //Next blocks
                for(int i=0;i<5;i++){
                    g.setColor(ColorManager.NEXTBOX);
                    g.fillRect(400, 75+(i*120), 100, 100);
                }
                
                //show FPS
                fps.checkFrame();
                fps.setFrame();
                g.drawString("FPS: "+fps.getFramePerSecond(), 0, 15);//Show Frame Per Second
                
                //Backup
                if(!buffStg.contentsLost())buffStg.show();//To prevent if image is not fully loaded
            }
    }

    private void initGame(){
        NextBlocks.setSpecificBlock(1, 0);
        BlockManager.setNextBlockToDisplay();
        fps = new FPSManager();
        try {
            imageManager = new ImageManager();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    
    
}
