/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

/**
 *
 * @author minjikim
 */
public class BlockStatus {
    
    private int blockColor=0;
    private boolean isBlock=false;
    
    public static BlockStatus[][] blocks = new BlockStatus[Initializer.BLOCK_NUM_WIDTH][Initializer.BLOCK_NUM_HEIGHT];
    
    public void setBlockColor(int colorNum){
        blockColor=colorNum;
        //if(isBlock==false)isBlock=true;
        if(colorNum==7)setBlockEmpty();
    }
    
    public void setBlockColorWithPath(int colorNum){
        //if(isBlock==false)isBlock=true;
        if(colorNum==7){
            setBlockEmpty();
        }else{
            blockColor=colorNum;
            isBlock=true;
        }
    }
    
    public void setFixedBlock(){
        isBlock=true;
    }
    
    public void setBlockEmpty(){
        blockColor=7;
        isBlock=false;
    }
    
    public int getBlockColor(){
        return blockColor;
    }
    
    public boolean isBlockPlaced(){
        return isBlock;
    }
    
}
