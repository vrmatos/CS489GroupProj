/**
 * Authors: Victoria Matos, Julia Hart, Kaytin Matrangola
 */
package com.victoriamatos.cs489groupproj;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class SpellingGame {
    public static final int NUM_QUESTIONS = 15;

    public static String[] chosenWords;
    public static int[] correct;
    private static int score;
    private int bestScoreEasy;
    private int bestScoreMedium;
    private int bestScoreHard;
    private int level;
    public static TextToSpeech tts;

    public SpellingGame(Context context){
        Log.w("SG", "Init SpellingGame");
        //initialize chosenWords, correct, and score
        chosenWords = new String[NUM_QUESTIONS];
        correct = new int[NUM_QUESTIONS];
        score = 0;

        //persistent data
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setBestScoreEasy(pref.getInt("bestScoreEasy",0));
        setBestScoreMedium(pref.getInt("bestScoreMedium",0));
        setBestScoreHard(pref.getInt("bestScoreHard",0));

        //setup TextToSpeech
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                Log.w("MA", "inside onInit");
                if (i != TextToSpeech.ERROR) {
                    int result = tts.setLanguage(Locale.US);
                    Log.w("MA", "set language");

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.w("MA", "Language not supported");
                    }
                } else {
                    Log.w("MA", "Language not set");
                }

            }
        });

    }

    /**
     * Choose NUM_QUESTIONS amount of words to be spelled from the chosen level text file
     * @param context
     */
    public void chooseWords(Context context){
        //make that textfile set of words into an ArrayList
        ArrayList<String> allWords = new ArrayList<>();
        try {
            AssetManager am = context.getAssets();
            InputStream is;
            switch(level){
                case 2:
                    is = context.getAssets().open("medium.txt");
                    break;
                case 3:
                    is = context.getAssets().open("hard.txt");
                    break;
                default:
                    is = context.getAssets().open("easy.txt");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String word = br.readLine();
            while (word != null) {
                allWords.add(word);
                word = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            Log.w("GA","Error reading file, filename");
        }

        //choose 15 random words
        Random rand = new Random();
        int int_random;
        for(int i=0; i<NUM_QUESTIONS; i++){
            int_random = rand.nextInt(allWords.size());
            chosenWords[i] = allWords.get(int_random);
            allWords.remove(int_random);
            correct[i] = 0;
        }
    }

    /** Setter methods */
    public void setLevel(int newLevel){level = newLevel;}
    public void setBestScoreEasy(int newScore){bestScoreEasy = newScore;}
    public void setBestScoreMedium(int newScore){bestScoreMedium = newScore;}
    public void setBestScoreHard(int newScore){bestScoreHard = newScore;}

    /**
     * General setter for BestScore, done based on level
     * @param newBestScore
     */
    public void setBestScore(int newBestScore){
        switch (level){
            case 2:
                bestScoreMedium = newBestScore;
                Log.w("SG", "setBestScore, Best Score Medium: " + bestScoreMedium);
                break;
            case 3:
                bestScoreHard = newBestScore;
                Log.w("SG", "setBestScore, Best Score Hard: " + bestScoreHard);
                break;
            default:
                bestScoreEasy = newBestScore;
                Log.w("SG", "setBestScore, Best Score Easy: " + bestScoreEasy);
                break;
        }
    }

    /** Getter methods */
    public int getScore(){return score;}
    public int getBestScoreEasy(){return bestScoreEasy;}
    public int getBestScoreMedium(){return bestScoreMedium;}
    public int getBestScoreHard(){return bestScoreHard;}

    /**
     * Gets the best score based on the level
     * @return
     */
    public int getBestScore(){
        switch (level){
            case 2:
                return bestScoreMedium;
            case 3:
                return bestScoreHard;
            default:
                return bestScoreEasy;
        }
    }

    /**
     * Evaluates the words entered by the user as correct/incorrect
     * @param str
     * @param pos
     */
    public void evaluate(String str, int pos){
        if(str.equals(chosenWords[pos])){
            score++;
            correct[pos] = 1;
        }
    }

    /**
     * Plays the given word out loud
     * @param word
     */
    public void speakWord(String word) {
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    /**
     * Plays a word based on the specific button pressed (1-15)
     * @param v
     */
    public void play(View v) {
        switch (v.getId()) {
            case R.id.megaphone1:
                speakWord(chosenWords[0]);
                break;
            case R.id.megaphone2:
                speakWord(chosenWords[1]);
                break;
            case R.id.megaphone3:
                speakWord(chosenWords[2]);
                break;
            case R.id.megaphone4:
                speakWord(chosenWords[3]);
                break;
            case R.id.megaphone5:
                speakWord(chosenWords[4]);
                break;
            case R.id.megaphone6:
                speakWord(chosenWords[5]);
                break;
            case R.id.megaphone7:
                speakWord(chosenWords[6]);
                break;
            case R.id.megaphone8:
                speakWord(chosenWords[7]);
                break;
            case R.id.megaphone9:
                speakWord(chosenWords[8]);
                break;
            case R.id.megaphone10:
                speakWord(chosenWords[9]);
                break;
            case R.id.megaphone11:
                speakWord(chosenWords[10]);
                break;
            case R.id.megaphone12:
                speakWord(chosenWords[11]);
                break;
            case R.id.megaphone13:
                speakWord(chosenWords[12]);
                break;
            case R.id.megaphone14:
                speakWord(chosenWords[13]);
                break;
            case R.id.megaphone15:
                speakWord(chosenWords[14]);
                break;
        }
    }

    /**
     * updates the view with the solution to the SpellingGame
     * @param tv
     * @param iv
     * @param pos
     */
    public void updateSolnView(TextView tv, ImageView iv, int pos){
        tv.setText(chosenWords[pos]);
        if(correct[pos] == 1)
            iv.setImageResource(0);
        else
            tv.setTextColor(Color.RED);

    }

    /**
     * Check if current score is best score
     * @return true if it is
     */
    public boolean isScoreBest(){
        return score > getBestScore();
    }

    /**
     * Write persistent data
     * @param context
     */
    public void setPreferences(Context context){
        Log.w("SG","Set Pref: " + bestScoreEasy + " " + bestScoreMedium + " " + bestScoreHard);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("bestScoreEasy", bestScoreEasy);
        editor.putInt("bestScoreMedium", bestScoreMedium);
        editor.putInt("bestScoreHard", bestScoreHard);
        editor.commit();
    }

}
