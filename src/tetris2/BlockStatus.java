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
    
    public static BlockStatus[][] blocks = new BlockStatus[10][20];
    
    public void setBlockColor(int colorNum){
        blockColor=colorNum;
        if(isBlock==false)isBlock=true;
        if(colorNum==7)setBlockEmpty();
    }
    
    public void setBlockEmpty(){
        blockColor=7;
        if(isBlock==true)isBlock=false;
    }
    
    public int getBlockColor(){
        return blockColor;
    }
    
    public boolean isBlockEmpty(){
        if(isBlock==true){
            return false;
        }else{
            return true;
        }
    }
    
}
