/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import imageRendering.DrawImage;
import imageRendering.ImageManager;
import java.awt.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.*;
import static java.awt.image.BufferedImage.*;
import javax.swing.*;
import tetris2.threads.*;

/**
 *
 * @author Tae
 */
public class MainScreen  extends JFrame implements Runnable, KeyListener{

    private JFrame frame;
    
    private BufferedImage buffImg = null;
    
    
    //Custom Class
    public DropDownThread dropDownThread;
    public KeyActivatingThread keyActThread;
    public ImageManager imageManager;
    public FPSManager fps;
    public DrawImage draw;

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

    //object of blocks
    public static BlockStatus[][] blocks = new BlockStatus[10][20];
    
    public MainScreen(JFrame frame){
        this.frame=frame;
        buffImg = new BufferedImage(WINDOW_WIDTH,WINDOW_HEIGHT,BufferedImage.TYPE_INT_RGB);
        
        frame.addKeyListener(this);
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
            System.out.println("11");
        
        
        draw = new DrawImage();
    }
    
    
    public void run() {
        
        while(true){
            System.out.println("11");
            System.out.println("22");
            draw.draw(frame, buffImg);
        }
    }

    public void draw(){
        Graphics g = buffImg.getGraphics();
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
