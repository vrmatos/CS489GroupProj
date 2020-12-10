package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class SpellingGameActivity extends AppCompatActivity {
    private static SpellingGame game;

    /**
     * Activity is initialized
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        getSupportActionBar().hide();
        game = SpellingMainActivity.getGame();
        game.chooseWords(this);
    }

    /**
     * Calculates the score for the current SpellingGame before showing solution
     * @param v
     */
    public void calculate(View v) {
        Log.w("GA","Inside calculate");
        for(int i = 1; i <= game.NUM_QUESTIONS; i++){
            int edit_id = this.getResources().getIdentifier("word" + i, "id", this.getPackageName());
            EditText et = findViewById(edit_id);
            game.evaluate(et.getText().toString(), (i-1));
        }
        Intent intent = new Intent(this, SpellingSolnActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up,0);
    }

    /**
     * Plays a word from the SpellingGame
     * @param v
     */
    public void playWord(View v){
        game.play(v);
    }

}
