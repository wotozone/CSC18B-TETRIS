/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageRendering;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import tetris2.*;

/**
 *
 * @author Tae
 */
public class DrawImage {
    
    public ImageManager imageManager;
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
    
    public DrawImage(){
        fps = new FPSManager();
        try {
            imageManager = new ImageManager();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void draw(JFrame frame, BufferedImage bi){

        Graphics g = bi.getGraphics();
        g.clearRect(0, 0, 700, 800);//Clear the image

        //Background
        g.drawImage(imageManager.getBackgroundImage(), 0, 0, frame);

        
            System.out.println("33");
        //Main playground
        g.drawImage(imageManager.getOutsideFrame(), BLOCK_START_X-10, BLOCK_START_Y-10, frame);
        for(int i=0;i<BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<BLOCK_NUM_WIDTH;k++){
                g.drawImage(imageManager.getBlockColorImage(BlockStatus.blocks[k][i].getBlockColor()), 
                                                            BLOCK_START_X+(k*BLOCK_SIZE_X),
                                                            BLOCK_START_Y+(i*BLOCK_SIZE_Y), frame);

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

        //if(!buffStg.contentsLost())buffStg.show();//To prevent if image is not fully loaded
        //Graphics ge = canvas.getGraphics();

        //ge.drawImage(bi, 0, 0, canvas);
        
    }
    
}
