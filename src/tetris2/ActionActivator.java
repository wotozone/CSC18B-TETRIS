/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import blockShape.BlockManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author minjikim
 */
public class ActionActivator extends KeyAdapter {
    
    public static boolean pressLeft=false;
    public static boolean pressRight=false;
    public static boolean pressUp=false;
    public static boolean pressDown=false;
    public static boolean pressSpace=false;
    public static boolean pressHold=false;
    
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                System.out.println("left");
                BlockManager.bm.setBlockMove(1);
                pressLeft=true;
                break;
            case KeyEvent.VK_RIGHT:
                BlockManager.bm.setBlockMove(2);
                pressRight=true;
                break;
            case KeyEvent.VK_UP:
                pressUp=true;
                break;
            case KeyEvent.VK_DOWN:
                BlockManager.bm.setBlockMove(0);
                pressDown=true;
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                System.out.println("left");
                pressLeft=false;
                break;
            case KeyEvent.VK_RIGHT:
                pressRight=false;
                break;
            case KeyEvent.VK_UP:
                pressUp=false;
                break;
            case KeyEvent.VK_DOWN:
                pressDown=false;
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                System.out.println("left");
                BlockManager.bm.setBlockMove(1);
                break;
            case KeyEvent.VK_RIGHT:
                BlockManager.bm.setBlockMove(2);
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                BlockManager.bm.setBlockMove(0);
                break;
        }
    }
    
}
