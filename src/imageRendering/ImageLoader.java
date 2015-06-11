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
    
    //FRAMES&BACKGROUNDS
    public final BufferedImage BACKGROUND;
    public final BufferedImage LOBBY_BACKGROUND;
    public final BufferedImage OUTSIDE_FRAME;
    public final BufferedImage NEXTBLOCK_FRAME;
    public final BufferedImage HOLDBLOCK_FRAME;
    public final BufferedImage SCOREBOX_FRAME;
    public final BufferedImage TIMESTAMP_FRAME;
    
    //INTERFACES
    public final BufferedImage GAME_OVER;
    public final BufferedImage GAME_READY;
    public final BufferedImage GAME_START;
    public final BufferedImage VERSUS;
    public final BufferedImage WINNER;
    public final BufferedImage LOSER;
    
    //BUTTONS
    public final BufferedImage GREEN_BUTTON;
    public final BufferedImage RED_BUTTON;
    public final BufferedImage GRAY_BUTTON;
    
    //BLOCKS
    public final BufferedImage RED_BLOCK;
    public final BufferedImage ORANGE_BLOCK;
    public final BufferedImage YELLOW_BLOCK;
    public final BufferedImage GREEN_BLOCK;
    public final BufferedImage SKY_BLOCK;
    public final BufferedImage BLUE_BLOCK;
    public final BufferedImage PURPLE_BLOCK;
    public final BufferedImage ERROR_BLOCK;
    public final BufferedImage EMPTY_BLOCK;
    
    //NEXTBLOCKS
    public final BufferedImage STICK_BLOCK;
    public final BufferedImage L_BLOCK;
    public final BufferedImage INVERSEL_BLOCK;
    public final BufferedImage Z_BLOCK;
    public final BufferedImage INVERSEZ_BLOCK;
    public final BufferedImage T_BLOCK;
    public final BufferedImage SQUARE_BLOCK;
    
    //NUMBERS
    public final BufferedImage NUMBER_ONE;
    public final BufferedImage NUMBER_TWO;
    public final BufferedImage NUMBER_THREE;
    public final BufferedImage NUMBER_FOUR;
    public final BufferedImage NUMBER_FIVE;
    public final BufferedImage NUMBER_SIX;
    public final BufferedImage NUMBER_SEVEN;
    public final BufferedImage NUMBER_EIGHT;
    public final BufferedImage NUMBER_NINE;
    public final BufferedImage NUMBER_ZERO;
    
    //LOBBY IMAGE
    
    
    
    public ImageLoader() throws Exception{
        //FRAMES
        BACKGROUND = ImageIO.read(new FileInputStream("Images/background.png"));
        LOBBY_BACKGROUND = ImageIO.read(new FileInputStream("Images/Lobby_Background2.jpg"));
        OUTSIDE_FRAME = ImageIO.read(new FileInputStream("Images/outsideframe.png"));
        NEXTBLOCK_FRAME = ImageIO.read(new FileInputStream("Images/nextblock2.png"));
        HOLDBLOCK_FRAME = ImageIO.read(new FileInputStream("Images/holdblock2.png"));
        SCOREBOX_FRAME = ImageIO.read(new FileInputStream("Images/scorebox3.png"));
        TIMESTAMP_FRAME = ImageIO.read(new FileInputStream("Images/timestamp.png"));
        
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
        
        //INTERFACES
        GAME_OVER = ImageIO.read(new FileInputStream("Images/GAMEOVER.png"));
        GAME_READY = ImageIO.read(new FileInputStream("Images/readygame.png"));
        GAME_START = ImageIO.read(new FileInputStream("Images/startgame.png"));
        VERSUS = ImageIO.read(new FileInputStream("Images/VS.png"));
        WINNER = ImageIO.read(new FileInputStream("Images/winner.png"));
        LOSER = ImageIO.read(new FileInputStream("Images/loser.png"));
        
        //BUTTONS
        RED_BUTTON = ImageIO.read(new FileInputStream("Images/redbutton_big.png"));
        GREEN_BUTTON = ImageIO.read(new FileInputStream("Images/greenbutton_big.png"));
        GRAY_BUTTON = ImageIO.read(new FileInputStream("Images/graybutton_big.png"));
        
        //NEXTBLOCKS
        STICK_BLOCK = ImageIO.read(new FileInputStream("Images/nextStick.png"));
        L_BLOCK = ImageIO.read(new FileInputStream("Images/nextL.png"));
        INVERSEL_BLOCK = ImageIO.read(new FileInputStream("Images/nextInverseL.png"));
        Z_BLOCK = ImageIO.read(new FileInputStream("Images/nextZ.png"));
        INVERSEZ_BLOCK = ImageIO.read(new FileInputStream("Images/nextInverseZ.png"));
        T_BLOCK = ImageIO.read(new FileInputStream("Images/nextT.png"));
        SQUARE_BLOCK = ImageIO.read(new FileInputStream("Images/nextSquare.png"));
        
        //NUMBERS
        NUMBER_ONE = ImageIO.read(new FileInputStream("Images/one.png"));
        NUMBER_TWO = ImageIO.read(new FileInputStream("Images/two.png"));
        NUMBER_THREE = ImageIO.read(new FileInputStream("Images/three.png"));
        NUMBER_FOUR = ImageIO.read(new FileInputStream("Images/four.png"));
        NUMBER_FIVE = ImageIO.read(new FileInputStream("Images/five.png"));
        NUMBER_SIX = ImageIO.read(new FileInputStream("Images/six.png"));
        NUMBER_SEVEN = ImageIO.read(new FileInputStream("Images/seven.png"));
        NUMBER_EIGHT = ImageIO.read(new FileInputStream("Images/eight.png"));
        NUMBER_NINE = ImageIO.read(new FileInputStream("Images/nine.png"));
        NUMBER_ZERO = ImageIO.read(new FileInputStream("Images/zero.png"));
        
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
