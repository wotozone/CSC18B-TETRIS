/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import imageRendering.ImageManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.*;


public class Testing extends JFrame implements Runnable, KeyListener{
 
    public ImageManager imageManager;
    public FPSManager fps;
    
    public static final int BLOCK_SIZE_X=35;
    public static final int BLOCK_SIZE_Y=35;
    //point where block start
    public static final int BLOCK_START_X=25;
    public static final int BLOCK_START_Y=75;
    //#of blocks each
    public static final int BLOCK_NUM_WIDTH=10;
    public static final int BLOCK_NUM_HEIGHT=20;
    
    //size of window
    public static final int WINDOW_WIDTH=700;
    public static final int WINDOW_HEIGHT=800;
    
    private BufferedImage bi = null;
    private ArrayList msList = null;
    private ArrayList enList = null;

    private boolean left = false, right = false, up = false, down = false, fire = false;
    private boolean start = false, end = false;

    private int w = 300, h = 500, x = 130, y = 450, xw = 20, xh = 20;
    
    private int moveDelay;


  public Testing() {
  
    // width, height크기를 가진 버퍼 이미지를 생성합니다.
    // 게임에 사용되는 이미지들을 버퍼에 그려준후 화면에 draw합니다.
    bi = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
    msList = new ArrayList(); // 무기 어레이
    enList = new ArrayList(); // 적 어레이

    // JFrame API를 참고하세요.
    this.addKeyListener(this);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
    this.setTitle("TETRIS"); //틀
    this.setResizable(false);  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);  
    
    
    NextBlocks.setSpecificBlock(1, 0);
    BlockManager.setNextBlockToDisplay();
    fps = new FPSManager();
    try {
        imageManager = new ImageManager();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
 }

 public void run() {

    try {

        // 적 생성 시간 제어 ( 2초에 한번씩 생성)
        int msCnt = 0;
        // 무기 발사 시간 제어 ( 1/100 초에 한번 발사 가능 )
        int enCnt = 0;
        
        moveDelay = 0;

        while(true) {

            Thread.sleep(10);  // 1/1000 초 sleep(일시정지)

            // 게임이 진행중인 경우에만 생성
            if(moveDelay>=100){//move per 0.1sec
                keyControl(); // 키 입력 제어
            }else{
                moveDelay+=10;
            }
            if(start) {
                /*
                if(enCnt > 2000) { // 2초에 한번씩 enemy를 생성합니다.
                    enCnt = 0;    
                }

                if(msCnt >= 100) {
                    msCnt = 0;     
                }

                msCnt += 10;
                enCnt += 10;

                */
            }
            draw(); //게임 화면 draw  
        }  
        }catch(Exception e){
            e.printStackTrace(); 
    } 
}


 // 게임 화면을 그립니다.
    public void draw() {

        Graphics gs = bi.getGraphics();
        //gs.setColor(Color.white);
        //gs.fillRect(0, 0, w, h);
        //gs.setColor(Color.black);
        gs.drawImage(imageManager.getBackgroundImage(), 0, 0, this);

        // 게임 현황 표시
        //gs.drawString("Enemy No : " + enList.size(), 180, 50);
        //gs.drawString("Ms No : " + msList.size(), 180, 70);
        //gs.drawString("START : Enter", 180, 90);
        fps.checkFrame();
        fps.setFrame();
        gs.drawString("FPS: "+fps.getFramePerSecond(), 20, 50);//Show Frame Per Second

        // 게임 오버시 화면에그려줌
        //if(end){   
        //    gs.drawString("G A M E     O V E R", 100, 250); 
        //}

        
        //Main playground
        gs.drawImage(imageManager.getOutsideFrame(), BLOCK_START_X-10, BLOCK_START_Y-10, this);
        for(int i=0;i<BLOCK_NUM_HEIGHT;i++){
            for(int k=0;k<BLOCK_NUM_WIDTH;k++){
                gs.drawImage(imageManager.getBlockColorImage(BlockStatus.blocks[k][i].getBlockColor()), 
                                                            BLOCK_START_X+(k*BLOCK_SIZE_X),
                                                            BLOCK_START_Y+(i*BLOCK_SIZE_Y), this);

            }
        }


        //Next blocks
        for(int i=0;i<5;i++){
            gs.setColor(ColorManager.NEXTBOX);
            gs.fillRect(400, 75+(i*120), 100, 100);
        }
        
        
        Graphics ge = this.getGraphics();

        ge.drawImage(bi, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

 // 키 입력 제한. 게임 영역을 넘어갔을 경우의 처리.
    public void keyControl() {
        if(left){
            System.out.println("Sdf");
            BlockManager.setBlockMove(1);
            moveDelay=0;
        }else if(right){
            BlockManager.setBlockMove(2);
            moveDelay=0;
        }else if(down){
            BlockManager.setBlockMove(0);
            moveDelay=0;
        }
        //if(0 < x) {   if(left) x -= 3;  } // 왼쪽 이동 제한
        //if(w > x + xw) { if(right) x += 3;  } // 오른쪽 이동 제한
        //if(25 < y) {   if(up) y -= 3;  } // 상단 이동제한
        //if(h > y + xh) { if(down) y += 3; } //  하단 이동제한
    }

 // 키 입력을 제어합니다.
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_LEFT:  
                left = true;       
                break;
            case KeyEvent.VK_RIGHT: 
                right = true;      
                break;
            case KeyEvent.VK_UP:   
                up = true;       
                break;
            case KeyEvent.VK_DOWN:   
                down = true;      
                break;
           } 
   }

 // 키를 뗀 상태를 제어합니다.
    public void keyReleased(KeyEvent ke){
    switch(ke.getKeyCode()) 
        {
            case KeyEvent.VK_LEFT: 
                left = false;  
                break;
            case KeyEvent.VK_RIGHT: 
                right = false; 
                break;
            case KeyEvent.VK_UP:  
                up = false;  
                break;
            case KeyEvent.VK_DOWN:
                down = false;  
                break;
        } 
    } 

    public void keyTyped(KeyEvent ke) {

    }
}


 


// 무기 객체
// 따로 java파일로 만들어도 됩니다
class Ms 
{
 int x;
 int y;
 int w = 5;
 int h = 5;

 // 객체의 초기 위치 세팅
 public Ms(int x, int y) {
  this.x = x;
  this.y = y; 
 }
 // 화면위로 1px씩 상승합니다.
 public void moveMs() {
  y--; 
 }
}

// 적 객체
// 따로 java파일로 만들어도 됩니다
class Enemy 
{
 int x;
 int y;
 int w = 10;
 int h = 10;
 // 객체의 초기 위치 세팅
 public Enemy(int x, int y)  {
  this.x = x;
  this.y = y; 
 }

 // 화면 아래로 1px씩 떨어집니다.
 public void moveEn() {
  y++; 
 } 
}
