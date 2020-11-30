package com.victoriamatos.cs489groupproj;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    public final int NUM_QUESTIONS = 15;
    private ArrayList<String> allWords;
    private String[] chosenWords;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        //decide set of words based on ChooseDiffActivity's level

        //make that textfile set of words into an ArrayList
        allWords = new ArrayList<>();
        chosenWords = new String[NUM_QUESTIONS];

        //setup TTS
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int i) {
                Log.w("MA", "inside onInit");

                if(i != TextToSpeech.ERROR){
                    int result = tts.setLanguage(Locale.US);
                    Log.w("MA", "set language");

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.w("MA","Language not supported");
                    }
                }else{
                    Log.w("MA", "Language not set");
                }

            }
        });

    }

    public void calculate(View v){
        //check how many inputs match the words

    }

    public void speakWord(String word){
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void play1(View v){
        speakWord(chosenWords[0]);
    }

    public void play2(View v){
        speakWord(chosenWords[1]);
    }

    public void play3(View v){
        speakWord(chosenWords[2]);
    }

    public void play4(View v){
        speakWord(chosenWords[3]);
    }

    public void play5(View v){
        speakWord(chosenWords[4]);
    }

    public void play6(View v){
        speakWord(chosenWords[5]);
    }

    public void play7(View v){
        speakWord(chosenWords[6]);
    }

    public void play8(View v){
        speakWord(chosenWords[7]);
    }

    public void play9(View v){
        speakWord(chosenWords[8]);
    }

    public void play10(View v){
        speakWord(chosenWords[9]);
    }

    public void play11(View v){
        speakWord(chosenWords[10]);
    }

    public void play12(View v){
        speakWord(chosenWords[11]);
    }

    public void play13(View v){
        speakWord(chosenWords[12]);
    }

    public void play14(View v){
        speakWord(chosenWords[13]);
    }

    public void play15(View v){
        speakWord(chosenWords[14]);
    }

}
