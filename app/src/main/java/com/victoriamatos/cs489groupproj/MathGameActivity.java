package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MathGameActivity extends AppCompatActivity {

    private int high_score_add;
    private int high_score_sub;
    private int high_score_mult;
    private int high_score_div;
    private Button contButton;
    private Button overButton;
    private TextView isHighET;

    private SoundPool pool;
    private int hitSoundId;


    private static MathGame mathGame;
    private int curr_score;
    private TextView currScoreEt;
    private TextView equationET;
    private char myType;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_math_game);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            myType = (char) bd.get("type");
        }
        TextView highScoreET = (TextView) findViewById(R.id.highScore);
        mathGame = new MathGame(this, myType);
        if (mathGame != null) {
            high_score_add = mathGame.getHighScoreAdd();
            high_score_sub = mathGame.getHighScoreSub();
            high_score_mult = mathGame.getHighScoreMult();
            high_score_div = mathGame.getHighScoreMult();

            switch (myType)
            {
                case('+'):
                    highScoreET.setText("Your High Score: " + String.valueOf(high_score_add));
                    break;
                case('-'):
                    highScoreET.setText("Your High Score: " + String.valueOf(high_score_sub));
                    break;
                case('x'):
                    highScoreET.setText("Your High Score: " + String.valueOf(high_score_mult));
                    break;
                case('/'):
                    highScoreET.setText("Your High Score: " + String.valueOf(high_score_div));
                    break;
                default:
                    highScoreET.setText("Your High Score: " + String.valueOf(0));
            }
            mathGame.setPreference(this);
            mathGame.setHighScoreAdd(high_score_add);
            mathGame.setHighScoreSub(high_score_sub);
            mathGame.setHighScoreMult(high_score_mult);
            mathGame.setHighScoreDiv(high_score_div);

            //sound
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams( 2 );
            pool = builder.build();
            hitSoundId = pool.load( this, R.raw.ding, 1 );


        }
    }


    public void startGame(View view)
    {

        isHighET = (TextView) findViewById(R.id.highScore);

        // get rid of you are correct
        // put in noise when correct
        //check nexus 5x 26
        if(overButton != null)
        {
            overButton.setVisibility(View.INVISIBLE);
        }
        setContentView(R.layout.math_game_play);
        currScoreEt = (TextView) findViewById(R.id.currScore);
        curr_score = mathGame.getCurrScore();
        currScoreEt.setText("Current Score: " + String.valueOf(curr_score));
        equationET = (TextView) findViewById(R.id.question);
        String n1 = String.valueOf(mathGame.getNum1());
        String n2 = String.valueOf(mathGame.getNum2());

        equationET.setText(n1 + myType + n2 + " = ?" );
    }

    public void checkAnswer(View view)
    {
        contButton = (Button) findViewById(R.id.cont);

        isHighET = (TextView) findViewById(R.id.highScore);
        overButton = (Button) findViewById(R.id.newGame);

        mathGame.findAnswer();
        Log.w("MA", "answer = "+mathGame.getAnswer());
        EditText givenAnswerET = (EditText) findViewById(R.id.answer);
        TextView correctET = (TextView) findViewById(R.id.answerResult);

        int givenAnser = Integer.valueOf(givenAnswerET.getText().toString());
        if(mathGame.isCorrectAnswer(givenAnser)){
            playHitSound();
            //mathGame.incCurrScore();
            currScoreEt = (TextView) findViewById(R.id.currScore);
            curr_score = mathGame.getCurrScore();
            currScoreEt.setText("Current Score: " + String.valueOf(curr_score));
            correctET.setVisibility(View.VISIBLE);

            correctET.setText("You Are Correct!");
            if(contButton != null) {
                contButton.setVisibility(View.VISIBLE);
            }
        }
        //game is over
        else
        {

            String correctAns = String.valueOf(mathGame.getAnswer());
            correctET.setVisibility(View.VISIBLE);
            correctET.setText("Incorrect. The correct answer was: " + correctAns);
            overButton.setVisibility(View.VISIBLE);
            curr_score = mathGame.getCurrScore();
            switch (myType)
            {
                case('+'):
                    if(curr_score > high_score_add)
                    {
                        mathGame.setHighScoreAdd(curr_score);
                        isHighET.setText("New High Score: " + String.valueOf(curr_score));
                    }
                    else{
                        if(isHighET != null) {
                            isHighET.setText("You did not reach your high score :(");
                        }
                    }
                    break;
                case('-'):
                    if(curr_score > high_score_sub)
                    {
                        mathGame.setHighScoreSub(curr_score);
                        isHighET.setText("New High Score: " + String.valueOf(curr_score));
                    }
                    else{
                        isHighET.setText("You did not reach your high score :(");
                    }
                    break;
                case('x'):
                    if(curr_score > high_score_mult)
                    {
                        mathGame.setHighScoreMult(curr_score);
                        isHighET.setText("New High Score: " + String.valueOf(curr_score));
                    }
                    else{
                        isHighET.setText("You did not reach your high score :(");
                    }
                    break;
                case('/'):
                    if(curr_score > high_score_div)
                    {
                        mathGame.setHighScoreDiv(curr_score);
                        isHighET.setText("New High Score: " + String.valueOf(curr_score));
                    }
                    else{
                        isHighET.setText("You did not reach your high score :(");
                    }
                    break;
                default:

            }
            mathGame.setPreference(this);
        }
    }

    public void newQuestion(View view){
        contButton.setVisibility(View.INVISIBLE);
        TextView correctET = (TextView) findViewById(R.id.answerResult);

        if(correctET != null) {
            correctET.setVisibility(View.INVISIBLE);
        }
        mathGame.newQuestion();
        equationET = (TextView) findViewById(R.id.question);
        String n1 = String.valueOf(mathGame.getNum1());
        String n2 = String.valueOf(mathGame.getNum2());
        equationET.setText(n1 + myType + n2 + " = ?" );
    }

    public void newGame(View view){
        Intent intent = new Intent( this, MathMainActivity.class );
        startActivity( intent );
    }
    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void playHitSound( ) {
        pool.play( hitSoundId, 1.0f, 1.0f, 0, 0, 1.0f );
    }


}

