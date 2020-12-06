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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_soln_layout);
        getSupportActionBar().hide();

        game = SpellingGameActivity.getGame();
        updateView();

        TextView tv = (TextView) findViewById(R.id.score);
        tv.setText(game.getScore() + "/15");
    }

    public void updateView(){
        for(int i = 1; i <= game.NUM_QUESTIONS; i++){
            int textview_id = this.getResources().getIdentifier("soln" + i, "id", this.getPackageName());
            TextView tv = (TextView) findViewById(textview_id);

            int image_id = this.getResources().getIdentifier("red_x_" + i, "id", this.getPackageName());
            ImageView iv = (ImageView) findViewById(image_id);

            game.updateSolnView(tv, iv, i-1);

        }
        endGame();

    }
    public void endGame(){
        //if current score is the best score, record that and bring according message
        if(game.isScoreBest()) {
            Log.w("SSA", "Inside endGame: " + game.isScoreBest());
            game.setBestScore(game.getScore());
        }
        game.setPreferences(this); //update preferences
    }

    public void playAgain(View v){
        Intent intent = new Intent(this, SpellingMainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }

    public void backHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }

    public void playWord(View v){
        game.play(v);
    }
}
