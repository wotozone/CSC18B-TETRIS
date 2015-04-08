/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.util.Random;

/**
 *
 * @author minjikim
 */
public class NextBlocks {
    
    private static int firstBlock= 0;
    private static int secondBlock= 0;
    private static int thirdBlock= 0;
    private static int fourthBlock= 0;
    private static int fifthBlock= 0;
    
    private static Random ran = new Random();
    
    public static void initBlocks(){
        firstBlock= ran.nextInt(6);
        secondBlock= ran.nextInt(6);
        thirdBlock= ran.nextInt(6);
        fourthBlock= ran.nextInt(6);
        fifthBlock= ran.nextInt(6);
    }
    
    public static void setNextRandomBlock(){
        firstBlock= secondBlock;
        secondBlock= thirdBlock;
        thirdBlock= fourthBlock;
        fourthBlock= fifthBlock;
        fifthBlock= ran.nextInt(6);
    }
    
    public static void setSpecificBlock(int place, int blocknum){
        switch(place){
            case 1: firstBlock=blocknum;
                    break;
            case 2: secondBlock=blocknum;
                    break;
            case 3: thirdBlock=blocknum;
                    break;
            case 4: fourthBlock=blocknum;
                    break;
            case 5: fifthBlock=blocknum;
                    break;
        }
    }
    
    public static int getNextBlock(){
        return firstBlock;
    }
    
    public static int getSpecificBlock(int place){
        switch(place){
            case 1: return firstBlock;
            case 2: return secondBlock;
            case 3: return thirdBlock;
            case 4: return fourthBlock;
            case 5: return fifthBlock;
        }
        return 0;
    }
    
    
}
