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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        getSupportActionBar().hide();
        game = new SpellingGame(this);

        try {
            //decide set of words based on ChooseDiffActivity's level
            AssetManager am = getApplicationContext().getAssets();
            InputStream is;
            switch(SpellingMainActivity.level){
                case 2:
                    game.setLevel(2);
                    is = getApplicationContext().getAssets().open("medium.txt");
                    break;
                case 3:
                    game.setLevel(3);
                    is = getApplicationContext().getAssets().open("hard.txt");
                    break;
                default:
                    game.setLevel(1);
                    is = getApplicationContext().getAssets().open("easy.txt");
            }
            game.chooseWords(is);
        } catch (Exception e) {
            Log.w("GA","Error reading file, filename");
        }

    }

    public static SpellingGame getGame(){return game;}

    public void calculate(View v) {
        Log.w("GA","Inside calculate");
        for(int i = 1; i <= game.NUM_QUESTIONS; i++){
            int edit_id = this.getResources().getIdentifier("word" + i, "id", this.getPackageName());
            EditText et = (EditText) findViewById(edit_id);
            game.evaluate(et.getText().toString(), (i-1));
        }
        Intent intent = new Intent(this, SpellingSolnActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up,0);
    }

    public void playWord(View v){
        game.play(v);
    }

}
