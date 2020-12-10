/**
 * Authors: Victoria Matos, Julia Hart, Kaytin Matrangola
 */
package com.victoriamatos.cs489groupproj;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MathGame {
    private static int UPPERBOUND = 21;
    private int highScoreAdd;
    private int highScoreSub;
    private int highScoreMult;
    private int highScoreDiv;

    private int currScore;
    private int num1;
    private int num2;
    private int answer;
    private char type;

    public MathGame(Context context, char type){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setHighScoreAdd(pref.getInt("highScoreAdd", highScoreAdd));
        setHighScoreSub(pref.getInt("highScoreSub", highScoreSub));
        setHighScoreMult(pref.getInt("highScoreMult", highScoreMult));
        setHighScoreDiv(pref.getInt("highScoreDiv", highScoreDiv));
        currScore = 0;
        this.type = type;
        setNums();
        findAnswer();
    }






    public void setPreference(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("highScoreAdd", highScoreAdd);
        editor.putInt("highScoreSub", highScoreSub);
        editor.putInt("highScoreMult", highScoreMult);
        editor.putInt("highScoreDiv", highScoreDiv);
        editor.commit();
    }


    public void newQuestion(){
        setNums();
        findAnswer();
    }


    public void setNums() {
        Random rand1 = new Random();
        Random rand2 = new Random();
        num1 = rand1.nextInt(UPPERBOUND);
        num2 = rand2.nextInt(UPPERBOUND);
        if(num1 < num2 && type == '-')
        {
            int t = num1;
            num1 = num2;
            num2 = t;
        }
        if(type == '/'){
            if(num2 == 0){
                num2++;
            }
            while(num1 % num2 != 0) {
                Log.w("MA", "changing nums");
                num1 = rand1.nextInt(UPPERBOUND);
                num2 = rand2.nextInt(UPPERBOUND - 1) + 1 ;
            }
        }
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getCurrScore() {
        return currScore;
    }

    public void incCurrScore() {
        currScore++;
    }

    public int getAnswer() {
        return answer;
    }

    public void findAnswer() {
        switch (type)
        {
            case('+'):
                answer = num1 + num2;
                break;
            case('-'):
                answer = num1 - num2;
                break;
            case('x'):
                answer = num1 * num2;
                break;
            case('/'):
                answer = num1 / num2;
                break;
            default:
                answer = 0;
        }

    }

    public boolean isCorrectAnswer(int guessed){
        if(guessed == answer)
        {
            incCurrScore();
            return true;
        }
        return false;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setHighScoreAdd(int highScoreAdd) {
        this.highScoreAdd = highScoreAdd;
    }

    public void setHighScoreSub(int highScoreSub) {
        this.highScoreSub = highScoreSub;
    }

    public void setHighScoreMult(int highScoreMult) {
        this.highScoreMult = highScoreMult;
    }

    public void setHighScoreDiv(int highScoreDiv) {
        this.highScoreDiv = highScoreDiv;
    }

    public int getHighScoreAdd() {
        return highScoreAdd;
    }

    public int getHighScoreSub() {
        return highScoreSub;
    }

    public int getHighScoreMult() {
        return highScoreMult;
    }
}

