/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockShape;

import Database.DatabaseManager;
import SoundPack.SoundManager;
import tetris2.BlockStatus;
import tetris2.Initializer;
import tetris2.NextBlocks;

/**
 *
 * @author minjikim
 */
public class BlockManager {
    
    //initClass
    public static BlockManager bm = new BlockManager();
    public static StickBlock stick = new StickBlock();
    public static LBlock letterL = new LBlock();
    public static InverseLBlock inverseL = new InverseLBlock();
    public static ZBlock letterZ = new ZBlock();
    public static InverseZBlock inverseZ = new InverseZBlock();
    public static SquareBlock square = new SquareBlock();
    public static TBlock letterT = new TBlock();
    
    
    //CurrentBlock
    public int currentBlock=0;
    public int direction=0;
    public boolean fixed=false;
    
    //RotationOfBlock
    public int rotate=0;
    
    //RotationAxis
    public int[] xAxis= new int[4];
    public int[] yAxis= new int[4];
    
    //Hold
    public int holdBlock=0;
    public boolean holdable=true;
    public boolean firstHold = true;
    
    //Score
    public int score=0;
    public int combo=0;
    public int blockClear=0;
    
    //Sound
    public boolean soundOn=false;
    public int soundType=0;
    
    public int rAxis;
    
    //BlockInfo.
    public int[] blocks= new int[4];
    
    //RemainBlocks
    
    public BlockManager(){
    }
    
    public void setNextBlockToDisplay(){
        if(Initializer.start){
            currentBlock=NextBlocks.getNextBlock();
            NextBlocks.setNextRandomBlock();
            initAxis();//Just in case
            setInitBlockShape(currentBlock);
            holdable=true;
            isGameOver();
        }
        //System.out.println("POINT: "+score);
    }
    
    public void holdBlock(){
        if(holdable&&Initializer.start){
            holdable=false;
            resetPath();
            if(firstHold){
                holdBlock=currentBlock;
                initAxis();//Just in case
                setNextBlockToDisplay();
                firstHold=false;
            }else{
                int b=currentBlock;
                currentBlock=holdBlock;
                holdBlock=b;
                initAxis();//Just in case
                setInitBlockShape(currentBlock);
                isGameOver();
            }
        }
    }
    
    public void setBlockMoveDown(){
        if(!fixed){
            setBlockMove(0);
        }
    }
    
    public void setQuickDown(){
        if(!fixed){
            this.direction=0;
            quickDown();
        }
    }
    
    public void setBlockRotate(){
        if(!fixed){
            switch(currentBlock){
                case 0://stick
                    resetPath();
                    if(stick.isRotatable()==0)stick.rotateBlock();
                    updatePath();
                    break;
                    //checkRotatable(stick);
                case 1://L
                    resetPath();
                    if(letterL.isRotatable()==0)letterL.rotateBlock();
                    updatePath();
                    break;
                    //checkRotatable(letterL);
                case 2://inverseL
                    resetPath();
                    if(inverseL.isRotatable()==0)inverseL.rotateBlock();
                    updatePath();
                    break;
                    //checkRotatable(inverseL);
                case 3://Z
                    resetPath();
                    if(letterZ.isRotatable()==0)letterZ.rotateBlock();
                    updatePath();
                    break;
                    //checkRotatable(letterZ);
                case 4://inverseZ
                    resetPath();
                    if(inverseZ.isRotatable()==0)inverseZ.rotateBlock();
                    updatePath();
                    break;
                    //checkRotatable(inverseZ);
                case 5://square
                    resetPath();
                    if(square.isRotatable()==0)square.rotateBlock();
                    updatePath();
                    break;
                    //checkRotatable(square);
                case 6://letterT
                    resetPath();
                    if(letterT.isRotatable()==0)letterT.rotateBlock();
                    updatePath();
                    break;
                    //checkRotatable(letterT);
            }
        }
    }
    
    public void checkRotatable(BlockInfo blk){//work it later
        int i;
        int k;
        int trial=0;
        int jump=0;
    }
    
    public void setBlockMove(int direction){
        if(!fixed){
            this.direction=direction;
            switch(direction){
                case 0://down
                    moveBlock();
                    break;
                case 1://left
                    moveBlock();
                    break;
                case 2://right
                    moveBlock();
                    break;
            }
        }
    }
    
    private void isGameOver(){
        if(!checkInitPath()){//game over
            if(DatabaseManager.dbm.highScore<score)
            DatabaseManager.dbm.saveData(Integer.toString(DatabaseManager.dbm.internal_id), "high_score", "account status", Integer.toString(score));
            Initializer.start=false;
            Initializer.end=true;
        }
    }
    
    private void initAxis(){
        for(int i=0;i<4;i++){
            xAxis[i]=0;
            yAxis[i]=0;
        }
        fixed=false;
    }
    
    private void quickDown(){
        int point = 0;
        for(int j=0;j<=20;j++){
            if(checkPath()==1)break;
            resetPath();
            movePath();
            point+=10;
        }
        score+=point;
        fixPath();
        loadNextBlock();
    }
    
    private void setInitBlockShape(int shapeNum){
        rotate=0;
        switch(shapeNum){
            case 0:
                stick.initBlock();
                //initStickBlock();
                break;
            case 1:
                letterL.initBlock();
                //initStickBlock();
                break;
            case 2:
                inverseL.initBlock();
                //initStickBlock();
                break;
            case 3:
                letterZ.initBlock();
                //initStickBlock();
                break;
            case 4:
                inverseZ.initBlock();
                //initStickBlock();
                break;
            case 5:
                square.initBlock();
                //initStickBlock();
                break;
            case 6:
                letterT.initBlock();
                //initStickBlock();
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
                loadNextBlock();
                break;
        }
        
    }
    
    private void loadNextBlock(){
        isBlockFilled();
        setNextBlockToDisplay();
    }
    
    private void isBlockFilled(){
        boolean filled=false;
        blockClear=0;
        int k=Initializer.BLOCK_NUM_HEIGHT;//maximum height
        for(int i=0;i<k;i++){
            filled=true;
            for(int j=0;j<Initializer.BLOCK_NUM_WIDTH;j++){//check the line is filled
                if(BlockStatus.blocks[j][i].isBlockPlaced()==false){
                    filled=false;
                    break;
                }
            }
            if(filled){
                breakBlock(i);
                blockClear++;
                //i--;
                //k--;
            }
        }
        if(blockClear!=0){
            comboCounter();
            if(blockClear>2){
                soundOn=true;
                soundType=0;
            }else if(combo>1){
                soundOn=true;
                soundType=combo;
            }
            score+=(500*blockClear*combo);
        }else{
            if(combo!=0){
                endComboBonus();
            }
        }
    }
    
    private void comboCounter(){
        combo++;
        //if(!impact&&combo>1)soundManager.playCombo(combo);
        //if(!impact&&combo>1)soundManager.playAltCombo(combo);
    }
    
    private void endComboBonus(){
        //score+=combo*1000;
        combo=0;
    }
    
    private void breakBlock(int column){
        for(int j=0;j<Initializer.BLOCK_NUM_WIDTH;j++){
            BlockStatus.blocks[j][column].setBlockEmpty();
            BlockStatus.blocks[j][0].setBlockEmpty();
        }
        for(int i=column;i>0;i--){
            for(int j=0;j<Initializer.BLOCK_NUM_WIDTH;j++){
                BlockStatus.blocks[j][i].setBlockColorWithPath(BlockStatus.blocks[j][i-1].getBlockColor());
            }
        }
    }
    
    private void fixPath(){
        for(int i=0;i<4;i++){
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setFixedBlock();
        }
        //fixed=true;
    }
    
    private void resetPath(){
        for(int i=0;i<4;i++){
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setBlockEmpty();
        }
    }
    
    private void updatePath(){
        for(int i=0;i<4;i++){
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setBlockColor(currentBlock);
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
                        //if(BlockStatus.blocks[xAxis[i]][yAxis[i]+1].isBlockPlaced()==true)System.out.println("stuck: "+xAxis[i]+"/"+(yAxis[i]+1));
                        if(BlockStatus.blocks[xAxis[i]][yAxis[i]+1].isBlockPlaced()==true)return 1;//block is stuck
                    }else{
                        System.out.println("stuck2");
                        return 1;
                    }
                    break;
                case 1://left
                    //if(BlockStatus.blocks[xAxis[i]-1][yAxis[i]].isBlockPlaced()==true)return 2;
                    if(xAxis[i]-1<0)System.out.println("cannot move left");
                    if(xAxis[i]-1>=0){
                        if(BlockStatus.blocks[xAxis[i]-1][yAxis[i]].isBlockPlaced()==true)return 2;//block is stuck
                    }else{
                        return 2;
                    }
                    //cannot move to that pathway
                    break;
                case 2://right
                    //if(BlockStatus.blocks[xAxis[i]+1][yAxis[i]].isBlockPlaced()==true)return 2;
                    if(xAxis[i]+1>=Initializer.BLOCK_NUM_WIDTH)System.out.println("cannot move right");
                    if(xAxis[i]+1<Initializer.BLOCK_NUM_WIDTH){
                        if(BlockStatus.blocks[xAxis[i]+1][yAxis[i]].isBlockPlaced()==true)return 2;//block is stuck
                    }else{
                        return 2;
                    }
                    break;
                case 3://rotation
                    if(yAxis[i]+1<Initializer.BLOCK_NUM_HEIGHT){
                        //if(BlockStatus.blocks[xAxis[i]][yAxis[i]+1].isBlockPlaced()==true)System.out.println("stuck");
                        if(BlockStatus.blocks[xAxis[i]][yAxis[i]+1].isBlockPlaced()==true)return 1;//block is stuck
                    }else if(xAxis[i]-1<0||xAxis[i]+1>=Initializer.BLOCK_NUM_WIDTH){
                        return 2;
                    }else{
                        return 1;
                    }
                    break;
                case 4://currentPlace
                    //if(BlockStatus.blocks[xAxis[i]][yAxis[i]].isBlockPlaced()==true)System.out.println("stuck: "+xAxis[i]+"/"+(yAxis[i]+1));
                    if(BlockStatus.blocks[xAxis[i]][yAxis[i]].isBlockPlaced()==true)return 1;//block is stuck
                    break;
            }
        }
        return 0;//can move
    }
    
    private boolean checkInitPath(){
        for(int i=0;i<4;i++){
            //if(BlockStatus.blocks[xAxis[i]][yAxis[i]].isBlockPlaced()==true)System.out.println("stuck at startline: "+xAxis[i]+"/"+(yAxis[i]+1));
            if(BlockStatus.blocks[xAxis[i]][yAxis[i]].isBlockPlaced()==true)return false;//block is stuck
        }
        return true;//can move
    }
    
    
    /*
    private void rotateStickBlock(){
        int x=xAxis[rAxis]-1;
        int y=yAxis[rAxis]-1;
        int z;
        for(int i=0;i<4;i++){
            xAxis[i]-=x;
            yAxis[i]-=y;
            z=xAxis[i];
            xAxis[i]=yAxis[i]+x;
            yAxis[i]=z+y;
        }
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
