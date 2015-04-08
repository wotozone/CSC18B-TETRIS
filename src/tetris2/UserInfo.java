/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.io.Serializable;

/**
 *
 * @author minjikim
 */
public class UserInfo implements Serializable{
    
    private String userName;
    private String passWord;
    private int score;
    
    public UserInfo(){
        this("","",0);
    }
    
    public UserInfo(String userName,String passWord, int score){
        this.userName=userName;
        this.passWord=passWord;
        this.score=score;
    }
    
}
