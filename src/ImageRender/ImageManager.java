/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageRender;

import java.awt.image.BufferedImage;

/**
 *
 * @author Tae
 */
public class ImageManager{
    
    public ImageLoader imageLoader;
    
    public ImageManager() throws Exception{
        imageLoader=new ImageLoader();
    }
    
    public BufferedImage getBlockColorImage(int value){
        switch(value){
            case(0):return imageLoader.SKY_BLOCK;
            case(1):return imageLoader.BLUE_BLOCK;
            case(2):return imageLoader.ORANGE_BLOCK;
            case(3):return imageLoader.GREEN_BLOCK;
            case(4):return imageLoader.RED_BLOCK;
            case(5):return imageLoader.YELLOW_BLOCK;
            case(6):return imageLoader.PURPLE_BLOCK;
            case(7):return imageLoader.EMPTY_BLOCK;
        }
        return imageLoader.EMPTY_BLOCK;
        
    }
    
    public BufferedImage getBackgroundImage(){
        return imageLoader.BACKGROUND;
    }
    
    public BufferedImage getOutsideFrame(){
        return imageLoader.OUTSIDE_FRAME;
    }
    
    
}
