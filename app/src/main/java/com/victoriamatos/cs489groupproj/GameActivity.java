package com.victoriamatos.cs489groupproj;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public final int NUM_QUESTIONS = 15;
    private ArrayList<String> allWords;
    private String[] chosenWords;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        getSupportActionBar().hide();

        //make that textfile set of words into an ArrayList
        allWords = new ArrayList<>();
        try {
            //decide set of words based on ChooseDiffActivity's level
            AssetManager am = getApplicationContext().getAssets();
            InputStream is;
            switch(ChooseDiffActivity.level){
                case 2:
                    is = getApplicationContext().getAssets().open("medium.txt");
                    break;
                case 3:
                    is = getApplicationContext().getAssets().open("hard.txt");
                    break;
                default:
                    is = getApplicationContext().getAssets().open("easy.txt");
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
        chosenWords = new String[NUM_QUESTIONS];
        Random rand = new Random();
        int int_random;
        for(int i=0; i<NUM_QUESTIONS; i++){
            int_random = rand.nextInt(allWords.size());
            chosenWords[i] = allWords.get(int_random);
            allWords.remove(int_random);
        }

        //setup TTS
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
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

    public void calculate(View v) {
        //check how many inputs match the words

    }

    public void speakWord(String word) {
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void playWord(View v) {
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
}
