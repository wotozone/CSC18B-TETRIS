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
public class LBlock extends BlockInfo{

    
    @Override
    public void rotateBlock() {
        super.inputInfo();
        /*
        int x=xAxis[rAxis];
        int y=yAxis[rAxis];
        double r=((Math.PI/2)*(rotate+2));
        double r2=(((Math.PI/2)*(rotate+3)));
        for(int i=0;i<4;i++){
            xAxis[i]=0;
            yAxis[i]=0;
            switch(i){
                case 0:
                    xAxis[i]-=(2*Math.cos(r));
                    yAxis[i]-=(2*Math.sin(r));
                    break;
                case 1:
                    xAxis[i]-=Math.cos(r);
                    yAxis[i]-=Math.sin(r);
                    break;
                case 3:
                    xAxis[i]-=Math.cos(r2);
                    yAxis[i]-=Math.sin(r2);
                    break;
            }
            xAxis[i]+=x;
            yAxis[i]+=y;
        }
        if(rotate>2){
            rotate=0;
        }else{
            rotate++;
        }*/
        int x=xAxis[rAxis]-1;
        int y=yAxis[rAxis]-1;
        switch(rotate){
            case 0:
                xAxis[0]=2+x;
                yAxis[0]=1+y;
                xAxis[2]=0+x;
                yAxis[2]=1+y;
                xAxis[3]=0+x;
                yAxis[3]=2+y;
                break;
            case 1:
                xAxis[0]=1+x;
                yAxis[0]=2+y;
                xAxis[2]=1+x;
                yAxis[2]=0+y;
                xAxis[3]=0+x;
                yAxis[3]=0+y;
                break;
            case 2:
                xAxis[0]=0+x;
                yAxis[0]=1+y;
                xAxis[2]=2+x;
                yAxis[2]=1+y;
                xAxis[3]=2+x;
                yAxis[3]=0+y;
                break;
            case 3:
                xAxis[0]=1+x;
                yAxis[0]=0+y;
                xAxis[2]=1+x;
                yAxis[2]=2+y;
                xAxis[3]=2+x;
                yAxis[3]=2+y;
        }
        if(rotate>2){
            rotate=0;
        }else{
            rotate++;
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
        yAxis[0]=1;
        xAxis[1]=4;
        yAxis[1]=2;
        xAxis[2]=4;
        yAxis[2]=3;
        xAxis[3]=5;
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
        switch(rotate){
            case 0:
                xAxis[0]=2+x;
                yAxis[0]=1+y;
                xAxis[2]=0+x;
                yAxis[2]=1+y;
                xAxis[3]=0+x;
                yAxis[3]=2+y;
                break;
            case 1:
                xAxis[0]=1+x;
                yAxis[0]=2+y;
                xAxis[2]=1+x;
                yAxis[2]=0+y;
                xAxis[3]=0+x;
                yAxis[3]=0+y;
                break;
            case 2:
                xAxis[0]=0+x;
                yAxis[0]=1+y;
                xAxis[2]=2+x;
                yAxis[2]=1+y;
                xAxis[3]=2+x;
                yAxis[3]=0+y;
                break;
            case 3:
                xAxis[0]=1+x;
                yAxis[0]=0+y;
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
        /*
        int x=xAxis[rAxis];
        int y=yAxis[rAxis];
        double r=((Math.PI/2)*(rotate+2));
        double r2=(((Math.PI/2)*(rotate+3)));
        for(int i=0;i<4;i++){
            xAxis[i]=0;
            yAxis[i]=0;
            switch(i){
                case 0:
                    xAxis[i]-=(2*Math.cos(r));
                    yAxis[i]-=(2*Math.sin(r));
                    break;
                case 1:
                    xAxis[i]-=Math.cos(r);
                    yAxis[i]-=Math.sin(r);
                    break;
                case 3:
                    xAxis[i]-=Math.cos(r2);
                    yAxis[i]-=Math.sin(r2);
                    break;
            }
            xAxis[i]+=x;
            yAxis[i]+=y;
            if(yAxis[i]<0||yAxis[i]>=Initializer.BLOCK_NUM_HEIGHT){
                return 10;
            }else if(xAxis[i]<0){
                return 1;
            }else if(xAxis[i]>=Initializer.BLOCK_NUM_WIDTH){
                return 2;
            }
        }*/
        return 0;
    }
    
}
