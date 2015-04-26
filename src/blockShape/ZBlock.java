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
public class ZBlock extends BlockInfo {

    
    @Override
    public void rotateBlock() {
        super.inputInfo();
        
        int x=xAxis[rAxis]-1;
        int y=yAxis[rAxis]-1;
        if(rotate==0){
            xAxis[0]=1+x;
            yAxis[0]=2+y;
            //xAxis[1]=1;
            //yAxis[1]=2;
            xAxis[2]=2+x;
            yAxis[2]=1+y;
            xAxis[3]=2+x;
            yAxis[3]=0+y;
        }else{
            xAxis[0]=0+x;
            yAxis[0]=1+y;
            //xAxis[1]=1;
            //yAxis[1]=2;
            xAxis[2]=1+x;
            yAxis[2]=2+y;
            xAxis[3]=2+x;
            yAxis[3]=2+y;
        }
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
        rAxis=1;
        //initAxis
        xAxis[0]=3;
        yAxis[0]=1;
        xAxis[1]=4;
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
        
        int x=xAxis[rAxis]-1;
        int y=yAxis[rAxis]-1;
        if(rotate==0){
            xAxis[0]=1+x;
            yAxis[0]=2+y;
            //xAxis[1]=1;
            //yAxis[1]=2;
            xAxis[2]=2+x;
            yAxis[2]=1+y;
            xAxis[3]=2+x;
            yAxis[3]=0+y;
        }else{
            xAxis[0]=0+x;
            yAxis[0]=1+y;
            //xAxis[1]=1;
            //yAxis[1]=2;
            xAxis[2]=1+x;
            yAxis[2]=2+y;
            xAxis[3]=2+x;
            yAxis[3]=2+y;
        }
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
