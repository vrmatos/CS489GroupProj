package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpellingSolnActivity extends AppCompatActivity {
    private SpellingGame game;

    /**
     * Activity is initialized
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_soln_layout);
        getSupportActionBar().hide();

        game = SpellingMainActivity.getGame();
        updateView();

        TextView tv = findViewById(R.id.score);
        tv.setText(game.getScore() + "/15");
    }

    /**
     * Updates the solution for each word
     */
    public void updateView(){
        for(int i = 1; i <= game.NUM_QUESTIONS; i++){
            int textview_id = this.getResources().getIdentifier("soln" + i, "id", this.getPackageName());
            TextView tv = findViewById(textview_id);

            int image_id = this.getResources().getIdentifier("red_x_" + i, "id", this.getPackageName());
            ImageView iv = findViewById(image_id);

            game.updateSolnView(tv, iv, i-1);

        }
        endGame();

    }

    /**
     * Sets in motion to determine is best score
     */
    public void endGame(){
        Log.w("SSA", "Inside endGame: " + game.isScoreBest());
        //if current score is the best score, record that and bring according message
        if(game.isScoreBest()) {
            game.setBestScore(game.getScore());
        }
        game.setPreferences(this); //update preferences
    }

    /**
     * Goes to SpellingMainActivity to play another round of SpellingGame
     * @param v
     */
    public void playAgain(View v){
        Intent intent = new Intent(this, SpellingMainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }

    /**
     * Goes to MainActivity to play another game
     * @param v
     */
    public void backHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }

    /**
     * Play a specific word from the SpellingGame
     * @param v
     */
    public void playWord(View v){
        game.play(v);
    }
}
