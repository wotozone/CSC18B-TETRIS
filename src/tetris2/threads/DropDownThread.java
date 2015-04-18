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
        while(true){
            try{
                sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            BlockManager.bm.setBlockMoveDown();
        }
    }
    
}
