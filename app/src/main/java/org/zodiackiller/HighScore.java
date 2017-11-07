package org.zodiackiller;

/**
 * Created by showkot on 11/6/17.
 */

import java.io.Serializable;

/**
 * Created by showkot on 10/26/17.
 */

public class HighScore implements Serializable {
    public static String name;
    public  static String score;
    public static int point;
    public static String character = "Male";

    public HighScore(){

    }
    public HighScore(String name, String score){
        this.name = name;
        this.score = score;
    }


    // getting Name and Score
    public String getName(){
        return this.name;
    }

    public String getScore(){
        return this.score;
    }

    //Setting name and score

    public void setName(String s){
        this.name = s;
    }

    public void setScore(String s){
        this.score = s;
    }

}
