package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpellingMainActivity extends AppCompatActivity {
    public static int level;
    private SpellingGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_difficulty);
        game = new SpellingGame(this);
        updateBestScores();
        getSupportActionBar().hide();
        level = 0;

    }

    public void updateBestScores(){
        TextView tv1 = findViewById(R.id.easy_score);
        tv1.setText(game.getBestScoreEasy() + "/" + game.NUM_QUESTIONS);
        Log.w("SMA","Best Score Easy: " + game.getBestScoreEasy());
        TextView tv2 = findViewById(R.id.medium_score);
        tv2.setText(game.getBestScoreMedium() + "/" + game.NUM_QUESTIONS);
        Log.w("SMA","Best Score Medium: " + game.getBestScoreMedium());
        TextView tv3 = findViewById(R.id.hard_score);
        tv3.setText(game.getBestScoreHard() + "/" + game.NUM_QUESTIONS);
        Log.w("SMA","Best Score Hard: " + game.getBestScoreHard());
    }

    public void playEasy(View v){
        Log.w("CDA","Inside playEasy");
        level = 1;
        Intent intent = new Intent(this, SpellingGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,0);
    }

    public void playMedium(View v){
        level = 2;
        Log.w("CDA","Inside playMedium");
        Intent intent = new Intent(this, SpellingGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,0);
    }

    public void playHard(View v){
        level = 3;
        Log.w("CDA","Inside playHard");
        Intent intent = new Intent(this, SpellingGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,0);
    }

}
