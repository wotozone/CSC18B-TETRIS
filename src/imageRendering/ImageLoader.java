/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageRendering;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Tae
 */
public class ImageLoader{
    
    public final BufferedImage BACKGROUND;
    public final BufferedImage OUTSIDE_FRAME;
    public final BufferedImage RED_BLOCK;
    public final BufferedImage ORANGE_BLOCK;
    public final BufferedImage YELLOW_BLOCK;
    public final BufferedImage GREEN_BLOCK;
    public final BufferedImage SKY_BLOCK;
    public final BufferedImage BLUE_BLOCK;
    public final BufferedImage PURPLE_BLOCK;
    public final BufferedImage ERROR_BLOCK;
    public final BufferedImage EMPTY_BLOCK;
    
    public ImageLoader() throws Exception{
        //FRAMES
        BACKGROUND = ImageIO.read(new FileInputStream("Images/BACKGROUND.jpg"));
        OUTSIDE_FRAME = ImageIO.read(new FileInputStream("Images/OUTSIDEFRAME.jpg"));
        
        //BLOCKS
        RED_BLOCK = ImageIO.read(new FileInputStream("Images/REDBLOCK.jpg"));
        ORANGE_BLOCK = ImageIO.read(new FileInputStream("Images/ORANGEBLOCK.jpg"));
        YELLOW_BLOCK = ImageIO.read(new FileInputStream("Images/YELLOWBLOCK.jpg"));
        GREEN_BLOCK = ImageIO.read(new FileInputStream("Images/GREENBLOCK.jpg"));
        SKY_BLOCK = ImageIO.read(new FileInputStream("Images/SKYBLOCK.jpg"));
        BLUE_BLOCK = ImageIO.read(new FileInputStream("Images/BLUEBLOCK.jpg"));
        PURPLE_BLOCK = ImageIO.read(new FileInputStream("Images/PURPLEBLOCK.jpg"));
        ERROR_BLOCK = ImageIO.read(new FileInputStream("Images/ERRORBLOCK.jpg"));
        EMPTY_BLOCK = ImageIO.read(new FileInputStream("Images/EMPTYBLOCK.jpg"));
        /*
        BACKGROUND = ImageIO.read(new FileInputStream("Images/BACKGROUND.jpg"));
        RED_BLOCK = ImageIO.read(new FileInputStream("Images/RED_BLOCK.jpg"));
        ORANGE_BLOCK = ImageIO.read(new FileInputStream("Images/ORANGE_BLOCK.jpg"));
        YELLOW_BLOCK = ImageIO.read(new FileInputStream("Images/YELLOW_BLOCK.jpg"));
        GREEN_BLOCK = ImageIO.read(new FileInputStream("Images/GREEN_BLOCK.jpg"));
        SKY_BLOCK = ImageIO.read(new FileInputStream("Images/SKY_BLOCK.jpg"));
        BLUE_BLOCK = ImageIO.read(new FileInputStream("Images/BLUE_BLOCK.jpg"));
        PURPLE_BLOCK = ImageIO.read(new FileInputStream("Images/PURPLE_BLOCK.jpg"));
        ERROR_BLOCK = ImageIO.read(new FileInputStream("Images/ERROR_BLOCK.jpg"));
        EMPTY_BLOCK = ImageIO.read(new FileInputStream("Images/BACKGROUND_BLOCK.jpg"));
        */
    }
    
}
