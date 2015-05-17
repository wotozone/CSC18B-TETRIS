/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageRendering;

import java.awt.image.BufferedImage;

/**
 *
 * @author Tae
 */
public class ImageManager{
    
    public ImageLoader imageLoader;
    public static final int MAX_SCORE=8;
    
    private int[] scoreNums = new int[MAX_SCORE];
    public BufferedImage[] scoreImage = new BufferedImage[MAX_SCORE];
    
    public ImageManager() throws Exception{
        imageLoader=new ImageLoader();
        for(int i=0;i<7;i++){
            scoreNums[i]=10;
            scoreImage[i]=null;
        }
        scoreNums[7]=0;
        scoreImage[7]=imageLoader.NUMBER_ZERO;
    }
    
    
    public void setScoreImage(int score){
        readScore(score);
        for(int i=0;i<8;i++){
            System.out.println(scoreNums[i]);
            scoreImage[i]=getScoreImage(scoreNums[i]);
        }
    }
    
    private void readScore(int score){
        if(score>=100000000){
            overScore();
        }else{
            String str = String.valueOf(score);
            for(int i=0;i<8;i++){
                if(i<str.length()){
                    //System.out.println(str.length());
                    scoreNums[7-i]=charToInt(str.charAt(i));
                    System.out.println(scoreNums[7-i]+"//"+(7-i));
                    //System.out.println(i);
                }else{
                    scoreNums[7-i]=10;
                }
            }
        }
    }
    
    private void overScore(){
        for(int i=0;i<8;i++){
            scoreNums[i]=9;
        }
    }
    
    private int charToInt(char c){
        if(c=='0'){return 0;
        }else if(c=='1'){return 1;
        }else if(c=='2'){return 2;
        }else if(c=='3'){return 3;
        }else if(c=='4'){return 4;
        }else if(c=='5'){return 5;
        }else if(c=='6'){return 6;
        }else if(c=='7'){return 7;
        }else if(c=='8'){return 8;
        }else if(c=='9'){return 9;
        }
        return 0;
    }
    
    public BufferedImage getScoreImage(int value){
        switch(value){
            case(0):return imageLoader.NUMBER_ZERO;
            case(1):return imageLoader.NUMBER_ONE;
            case(2):return imageLoader.NUMBER_TWO;
            case(3):return imageLoader.NUMBER_THREE;
            case(4):return imageLoader.NUMBER_FOUR;
            case(5):return imageLoader.NUMBER_FIVE;
            case(6):return imageLoader.NUMBER_SIX;
            case(7):return imageLoader.NUMBER_SEVEN;
            case(8):return imageLoader.NUMBER_EIGHT;
            case(9):return imageLoader.NUMBER_NINE;
        }
        return null;
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
    
    public BufferedImage getLobbyBackgroundImage(){
        return imageLoader.LOBBY_BACKGROUND;
    }
    
    public BufferedImage getOutsideFrame(){
        return imageLoader.OUTSIDE_FRAME;
    }
    
    public BufferedImage getHoldBlockFrame(){
        return imageLoader.HOLDBLOCK_FRAME;
    }
    
    public BufferedImage getNextBlockFrame(){
        return imageLoader.NEXTBLOCK_FRAME;
    }
        
    public BufferedImage getScoreBoxFrame(){
        return imageLoader.SCOREBOX_FRAME;
    }
    
    public BufferedImage getGameOverImage(){
        return imageLoader.GAME_OVER;
    }
    
    public BufferedImage getNumberImage(int num){
        switch(num){
            case 0: return imageLoader.NUMBER_ZERO;
            case 1: return imageLoader.NUMBER_ONE;
            case 2: return imageLoader.NUMBER_TWO;
            case 3: return imageLoader.NUMBER_THREE;
            case 4: return imageLoader.NUMBER_FOUR;
            case 5: return imageLoader.NUMBER_FIVE;
            case 6: return imageLoader.NUMBER_SIX;
            case 7: return imageLoader.NUMBER_SEVEN;
            case 8: return imageLoader.NUMBER_EIGHT;
            case 9: return imageLoader.NUMBER_NINE;
            default: return imageLoader.ERROR_BLOCK;
        }
    }
    
    public BufferedImage getNextBlockImage(int value){
        switch(value){
            case 0: return imageLoader.STICK_BLOCK;
            case 1: return imageLoader.L_BLOCK;
            case 2: return imageLoader.INVERSEL_BLOCK;
            case 3: return imageLoader.Z_BLOCK;
            case 4: return imageLoader.INVERSEZ_BLOCK;
            case 5: return imageLoader.SQUARE_BLOCK;
            case 6: return imageLoader.T_BLOCK;
            default: return imageLoader.ERROR_BLOCK;
        }
    }
}
