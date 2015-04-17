/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author minjikim
 */
public class ColorManager extends Color{

    final static Color BACKGROUND = new Color(100,100,200);
    
    final static Color FOREGROUND = new Color(255,0,50);
    
    public final static Color NEXTBOX = new Color(200,200,200);
    
    final static Color BOXLINES = new Color(100,100,100);
    
    //blocks
    final static Color RED = new Color(255,50,50);
    final static Color BLUE = new Color(50,50,255);
    final static Color SKY = new Color(150,150,255);
    final static Color ORANGE = new Color(255,200,150);
    final static Color GREEN = new Color(50,255,50);
    final static Color YELLOW = new Color(255,255,50);
    final static Color PURPLE = new Color(255,50,255);
    final static Color EMPTY = new Color(50,50,100);
    final static Color ERROR = new Color(255,255,255);
    
    public ColorManager(int r, int g, int b) {
        super(r, g, b);
    }
    
    public static Color getBlockColor(int value){
        switch(value){
            case(0):return SKY;
            case(1):return BLUE;
            case(2):return ORANGE;
            case(3):return GREEN;
            case(4):return RED;
            case(5):return YELLOW;
            case(6):return PURPLE;
            case(7):return EMPTY;
        }
        return ERROR;
    }
    
}
