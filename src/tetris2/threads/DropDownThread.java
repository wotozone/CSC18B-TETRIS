/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2.threads;

import blockShape.BlockManager;

/**
 *
 * @author Tae
 */
public class DropDownThread extends Thread {
    
    public DropDownThread() {
    }
    
    
    
    public void run(){
        int delay=0;
        while(true){
            try{
                sleep(10);
                if(delay>=100){
                    BlockManager.bm.setBlockMoveDown();
                    delay=0;
                }else{
                    delay++;
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
}
