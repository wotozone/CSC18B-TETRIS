/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author minjikim
 */
public class RegularExpression {
    
    public static String[] users;
    public static int totalUsersNum=0;
    
    public static void checkDate(String s){
        Pattern p = Pattern.compile("([0][1-9]|[1][0-2])-([0][1-9]|[1][0-9]|[2][0-9]|[3][0-2])-([1-2][0-9][0-9][0-9])");
        Matcher m = p.matcher(s);
        boolean b = m.matches();
        if(b){System.out.println("it matches :" + s);
        }else{System.out.println("it does not match :" + s);}
    }
    
    public static boolean checkPassword(String s){
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})");
        Matcher m = p.matcher(s);
        //String r="excellent";
        if(m.matches()==true){
            //if(s.length()<10)r="poor";
            //else if(s.length()<15)r="good";
            return true;
        }else{
            return false;
            //r="unacceptable";
        }
        //System.out.println(r);
    }
    
    public static boolean checkID(String s){
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{4,10}$");
        Matcher m = p.matcher(s);
        if(m.matches()==true)return true;
        return false;
    }
    
    public static boolean checkNickname(String s){
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{4,8}$");
        Matcher m = p.matcher(s);
        if(m.matches()==true)return true;
        return false;
    }
}
