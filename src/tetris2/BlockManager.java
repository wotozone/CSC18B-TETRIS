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
    public static BlockManager bm = new BlockManager();
    
    //CurrentBlock
    public int currentBlock=0;
    public int direction=0;
    
    //RotationOfBlock
    public int rotate=0;
    
    //RotationAxis
    public int[] xAxis= new int[4];
    public int[] yAxis= new int[4];
    
    public int rAxis;
    
    //BlockInfo.
    public int[] blocks= new int[4];
    
    //RemainBlocks
    
    public void setNextBlockToDisplay(){
        currentBlock=NextBlocks.getNextBlock();
        NextBlocks.setNextRandomBlock();
        setInitBlockShape(currentBlock);
    }
    
    public void setBlockMoveDown(){
        setBlockMove(0);
    }
    
    public void setBlockRotate(){
        
    }
    
    public void setBlockMove(int direction){
        this.direction=direction;
        switch(direction){
            case 0:
                moveBlock();
                break;
            case 1: 
                moveBlock();
                break;
            case 2: 
                moveBlock();
                break;
        }
    }
    
    private void setInitBlockShape(int shapeNum){
        rotate=0;
        switch(shapeNum){
            case 0:
                initStickBlock();
                break;
        }
    }
    
    private void moveBlock(){
        //checkPath
        switch(checkPath()){
            case 0://moveBlocks
                resetPath();
                movePath();
                break;
            case 1://fixBlocks
                fixPath();
                break;
        }
        
    }
    
    private void fixPath(){
        for(int i=0;i<4;i++){
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setFixedBlock();
        }
    }
    
    private void resetPath(){
        for(int i=0;i<4;i++){
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setBlockEmpty();
        }
    }
    
    private void movePath(){
        for(int i=0;i<4;i++){
            switch(direction){
                case 0://down
                    yAxis[i]++;
                    break;
                case 1://left
                    xAxis[i]--;
                    break;
                case 2://right
                    xAxis[i]++;
                    break;
            }
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setBlockColor(currentBlock);
        }
    }
    
    private int checkPath(){
        for(int i=0;i<4;i++){
            switch(direction){
                case 0://down
                    if(yAxis[i]+1<Initializer.BLOCK_NUM_HEIGHT){
                        if(BlockStatus.blocks[xAxis[i]][yAxis[i]+1].isBlockPlaced()==true)System.out.println("stuck");
                        if(BlockStatus.blocks[xAxis[i]][yAxis[i]+1].isBlockPlaced()==true)return 1;//block is stuck
                    }else{
                        return 1;
                    }
                    break;
                case 1://left
                    //if(BlockStatus.blocks[xAxis[i]-1][yAxis[i]].isBlockPlaced()==true)return 2;
                    if(xAxis[i]-1<0)return 2;
                    //cannot move to that pathway
                    break;
                case 2://right
                    //if(BlockStatus.blocks[xAxis[i]+1][yAxis[i]].isBlockPlaced()==true)return 2;
                    System.out.println(xAxis[i]+1);
                    if(xAxis[i]+1>=Initializer.BLOCK_NUM_WIDTH)return 2;
                    break;
            }
        }
        return 0;//can move
    }
    
    private void initStickBlock(){
        //rotateAxis
        rAxis=1;
        //initAxis
        xAxis[0]=4;
        yAxis[0]=0;
        xAxis[1]=4;
        yAxis[1]=1;
        xAxis[2]=4;
        yAxis[2]=2;
        xAxis[3]=4;
        yAxis[3]=3;
        for(int i=0;i<4;i++){
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setBlockColor(currentBlock);
        }
    }
    
    /*
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
        if(xMin<0||xMax>=Testing.BLOCK_NUM_WIDTH||yMin<0||yMax>=Testing.BLOCK_NUM_HEIGHT){
            setNewAxisPoint(pos,true);
        }
    }
    
    private void isBlockDuplicated(boolean duplicate){
        
    }
    
    private boolean checkBlockDuplicates(int xAxis,int yAxis, int color,boolean empty){
        if(BlockStatus.blocks[xAxis][yAxis].getBlockColor()!=7&&empty==false)return true;
        BlockStatus.blocks[xAxis][yAxis].setBlockColor(color);
        return false;
    }
    
    private int isRemoveBlock(boolean remove, int colorNum){
        if(remove==true)return 7;
        return colorNum;
    }
    */
    
}
