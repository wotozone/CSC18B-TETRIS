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
    private String password;
    private int score;
    
    public UserInfo(String userName,String password, int score){
        this.userName=userName;
        this.password=password;
        this.score=score;
    }
    
    public String getUserName(){
        return this.userName;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public int getScore(){
        return this.score;
    }
    
}
