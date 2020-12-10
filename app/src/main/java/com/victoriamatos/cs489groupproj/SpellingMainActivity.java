package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpellingMainActivity extends AppCompatActivity {
    private static SpellingGame game;

    /**
     * Activity is initialized
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_difficulty);
        game = new SpellingGame(this);
        updateBestScores();
        getSupportActionBar().hide();

    }

    /**
     * Updates the best Easy, Medium, and Hard Scores with persistent data
     */
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

    /**
     * Play SpellingGame on the Easy level
     * @param v
     */
    public void playEasy(View v){
        Log.w("CDA","Inside playEasy");
        game.setLevel(1);
        Intent intent = new Intent(this, SpellingGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,0);
    }

    /**
     * Play SpellingGame on the Medium level
     * @param v
     */
    public void playMedium(View v){
        Log.w("CDA","Inside playMedium");
        game.setLevel(2);
        Intent intent = new Intent(this, SpellingGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,0);
    }

    /**
     * Play SpellingGame on the Hard level
     * @param v
     */
    public void playHard(View v){
        Log.w("CDA","Inside playHard");
        game.setLevel(3);
        Intent intent = new Intent(this, SpellingGameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,0);
    }

    public static SpellingGame getGame(){return game;}

}
