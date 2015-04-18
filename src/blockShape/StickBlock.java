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
public class StickBlock extends BlockInfo {

    
    @Override
    public void rotateBlock() {
        super.inputInfo();
        
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
        super.outputInfo();
    }
    
    @Override
    public int isRotatable(){
        super.inputInfo();
        
        int x=xAxis[rAxis]-1;
        int y=yAxis[rAxis]-1;
        int z;
        for(int i=0;i<4;i++){
            xAxis[i]-=x;
            yAxis[i]-=y;
            z=xAxis[i];
            xAxis[i]=yAxis[i]+x;
            yAxis[i]=z+y;
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
