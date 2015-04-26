/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockShape;

import tetris2.BlockStatus;
import tetris2.Initializer;

/**
 *
 * @author Tae
 */
public class SquareBlock extends BlockInfo {

    
    @Override
    public void rotateBlock() {
        super.inputInfo();
        
        if(rotate==0){
            rotate++;
        }else{
            rotate--;
        }
        super.outputInfo();
    }

    @Override
    public void initBlock() {
        super.inputInfo();
        //rotateAxis
        rAxis=0;
        //initAxis
        xAxis[0]=4;
        yAxis[0]=1;
        xAxis[1]=5;
        yAxis[1]=1;
        xAxis[2]=4;
        yAxis[2]=2;
        xAxis[3]=5;
        yAxis[3]=2;
        for(int i=0;i<4;i++){
            BlockStatus.blocks[xAxis[i]][yAxis[i]].setBlockColor(currentBlock);
        }
        super.outputInfo();
    }
    
    @Override
    public int isRotatable(){
        super.inputInfo();
        
        for(int i=0;i<4;i++){
            if(yAxis[i]<0||yAxis[i]>=Initializer.BLOCK_NUM_HEIGHT){
                return 10;
            }else if(xAxis[i]<0){
                return 1;
            }else if(xAxis[i]>=Initializer.BLOCK_NUM_WIDTH){
                return 2;
            }
        }
        return 0;
    }
}
