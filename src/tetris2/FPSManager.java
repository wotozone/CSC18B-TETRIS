/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

/**
 *
 * @author Tae
 */
public class FPSManager {
    
    private int framePerSecond=0;
    private int frames=0;
    private long totalTime=0;
    private long currentTime=0;
    private long lastTime=0;
    
    public FPSManager(){
        framePerSecond=0;
        frames=0;
        totalTime=0;
        currentTime=System.currentTimeMillis();
        lastTime=currentTime;
    }
    
    public void checkFrame(){
        lastTime=currentTime;
        currentTime=System.currentTimeMillis();
        //System.out.println(currentTime-lastTime);
        totalTime+=currentTime-lastTime;
        if(totalTime>1000){
            totalTime-=1000;
            framePerSecond=frames;
            frames=0;
        }
    }
    
    public void setFrame(){
        frames++;
    }
    
    public int getFramePerSecond(){
        return framePerSecond;
    }
    
}
