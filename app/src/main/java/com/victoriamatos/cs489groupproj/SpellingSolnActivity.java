package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpellingSolnActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_soln_layout);
        getSupportActionBar().hide();
        updateView();

        TextView tv = (TextView) findViewById(R.id.score);
        tv.setText(SpellingGameActivity.getScore() + "/15");
    }

    public void updateView(){
        for(int i = 1; i <= SpellingGameActivity.NUM_QUESTIONS; i++){
            int textview_id = this.getResources().getIdentifier("soln" + i, "id", this.getPackageName());
            TextView tv = (TextView) findViewById(textview_id);
            tv.setText(SpellingGameActivity.chosenWords[i-1]);

            int image_id = this.getResources().getIdentifier("red_x_" + i, "id", this.getPackageName());
            ImageView iv = (ImageView) findViewById(image_id);
            if(SpellingGameActivity.correct[i-1] == 1)
                iv.setImageResource(0);
            else
                tv.setTextColor(Color.RED);

        }

    }

    public void playAgain(View v){
        Intent intent = new Intent(this, SpellingMainActivity.class);
        startActivity(intent);
    }

    public void backHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void speakWord(String word) {
        SpellingGameActivity.tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void playWord(View v) {
        switch (v.getId()) {
            case R.id.megaphone1:
                speakWord(SpellingGameActivity.chosenWords[0]);
                break;
            case R.id.megaphone2:
                speakWord(SpellingGameActivity.chosenWords[1]);
                break;
            case R.id.megaphone3:
                speakWord(SpellingGameActivity.chosenWords[2]);
                break;
            case R.id.megaphone4:
                speakWord(SpellingGameActivity.chosenWords[3]);
                break;
            case R.id.megaphone5:
                speakWord(SpellingGameActivity.chosenWords[4]);
                break;
            case R.id.megaphone6:
                speakWord(SpellingGameActivity.chosenWords[5]);
                break;
            case R.id.megaphone7:
                speakWord(SpellingGameActivity.chosenWords[6]);
                break;
            case R.id.megaphone8:
                speakWord(SpellingGameActivity.chosenWords[7]);
                break;
            case R.id.megaphone9:
                speakWord(SpellingGameActivity.chosenWords[8]);
                break;
            case R.id.megaphone10:
                speakWord(SpellingGameActivity.chosenWords[9]);
                break;
            case R.id.megaphone11:
                speakWord(SpellingGameActivity.chosenWords[10]);
                break;
            case R.id.megaphone12:
                speakWord(SpellingGameActivity.chosenWords[11]);
                break;
            case R.id.megaphone13:
                speakWord(SpellingGameActivity.chosenWords[12]);
                break;
            case R.id.megaphone14:
                speakWord(SpellingGameActivity.chosenWords[13]);
                break;
            case R.id.megaphone15:
                speakWord(SpellingGameActivity.chosenWords[14]);
                break;
        }
    }
}
