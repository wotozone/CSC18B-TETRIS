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
public class BlockManager {
    
    //initClass
    private static BlockManager blockManager = new BlockManager();
    
    //CurrentBlock
    public static int currentBlock=0;
    public static int posMoved=0;
    
    //RotationOfBlock
    public int rotate=0;
    
    //RotationAxis
    public int[] rAxis= new int[2];
    
    //BlockInfo.
    
    public static void setNextBlockToDisplay(){
        currentBlock=NextBlocks.getNextBlock();
        NextBlocks.setNextRandomBlock();
        blockManager.setInitBlockShape(currentBlock);
    }
    
    public static void setBlockMoveDown(){
        posMoved=0;
        blockManager.setNextBlockShape(0,currentBlock);
    }
    
    public static void setBlockRotate(){
        
    }
    
    public static void setBlockMove(int direction){
        switch(direction){
            case 0: posMoved=0;
                    blockManager.setNextBlockShape(0, currentBlock);
                    break;
            case 1: posMoved=1;
                    blockManager.setNextBlockShape(1, currentBlock);
                    break;
            case 2: posMoved=2;
                    blockManager.setNextBlockShape(2, currentBlock);
                    break;
        }
    }
    
    public void setInitBlockShape(int shapeNum){
        rotate=0;
        switch(shapeNum){
            case 0:
                rAxis[0]=4;
                rAxis[1]=1;
                setStickShape(0,false);
        }
    }
    
    public void setNextBlockShape(int direction, int shapeNum){
        setStickShape(7,true);
        setNewAxisPoint(direction,false);
        switch(shapeNum){
            case 0:
                setStickShape(0,false);
        }
    }
    
    public void setNewAxisPoint(int direction,boolean undo){
        if(direction==0){
            if(undo==false){
                rAxis[1]++;
            }else{
                rAxis[1]--;
            }
        }
        if(direction==1){
            if(undo==false){
                rAxis[0]--;
            }else{
                rAxis[0]++;
            }
        }
        if(direction==2){
            if(undo==false){
                rAxis[0]++;
            }else{
                rAxis[0]--;
            }
        }
    }
    
    public void setStickShape(int colorNum,boolean setempty){
        //int i=isRemoveBlock(remove, 0);
        int x=rAxis[0];
        int y=rAxis[1];
        if(rotate==0){
            checkBorder(posMoved,x,x,y-1,y+2);
            isBlockDuplicated(checkBlockDuplicates(x,y-1,colorNum,setempty));
            isBlockDuplicated(checkBlockDuplicates(x,y,colorNum,setempty));
            isBlockDuplicated(checkBlockDuplicates(x,y+1,colorNum,setempty));
            isBlockDuplicated(checkBlockDuplicates(x,y+2,colorNum,setempty));
        }else{
            isBlockDuplicated(checkBlockDuplicates(x-1,y,colorNum,setempty));
            isBlockDuplicated(checkBlockDuplicates(x,y,colorNum,setempty));
            isBlockDuplicated(checkBlockDuplicates(x+1,y,colorNum,setempty));
            isBlockDuplicated(checkBlockDuplicates(x+2,y,colorNum,setempty));
        }
    }
    
    private void checkBorder(int pos, int xMin, int xMax, int yMin, int yMax){
        if(xMin<0||xMax>=StartScreen.BLOCK_NUM_WIDTH||yMin<0||yMax>=StartScreen.BLOCK_NUM_HEIGHT){
            setNewAxisPoint(pos,true);
        }
    }
    
    private void isBlockDuplicated(boolean duplicate){
        
    }
    
    private boolean checkBlockDuplicates(int xAxis,int yAxis, int color,boolean empty){
        if(StartScreen.blocks[xAxis][yAxis].getBlockColor()!=7&&empty==false)return true;
        StartScreen.blocks[xAxis][yAxis].setBlockColor(color);
        return false;
    }
    
    private int isRemoveBlock(boolean remove, int colorNum){
        if(remove==true)return 7;
        return colorNum;
    }
    
}
