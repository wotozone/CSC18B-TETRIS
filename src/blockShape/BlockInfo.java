/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockShape;

/**
 *
 * @author Tae
 */
abstract class BlockInfo {
    
    protected int currentBlock=0;
    protected int direction=0;
    
    //RotationOfBlock
    protected int rotate=0;
    
    //RotationAxis
    protected int[] xAxis= new int[4];
    protected int[] yAxis= new int[4];
    
    protected int rAxis;
    
    public void inputInfo() {
        this.currentBlock=BlockManager.bm.currentBlock;
        this.direction=BlockManager.bm.direction;
        this.rotate=BlockManager.bm.rotate;
        this.rAxis=BlockManager.bm.rAxis;
        
        for(int i=0;i<4;i++){
            this.xAxis[i]=BlockManager.bm.xAxis[i];
            this.yAxis[i]=BlockManager.bm.yAxis[i];
        }
    }
    
    public void outputInfo(){
        BlockManager.bm.currentBlock=this.currentBlock;
        BlockManager.bm.direction=this.direction;
        BlockManager.bm.rotate=this.rotate;
        BlockManager.bm.rAxis=this.rAxis;
        
        for(int i=0;i<4;i++){
            BlockManager.bm.xAxis[i]=this.xAxis[i];
            BlockManager.bm.yAxis[i]=this.yAxis[i];
        }
    }
    
    public int isRotatable(){
        return 0;
    }
    
    public void rotateBlock(){
        System.out.println("Method is empty");
    }
    
    public void initBlock(){
        System.out.println("Method is empty");
    }
    
}
