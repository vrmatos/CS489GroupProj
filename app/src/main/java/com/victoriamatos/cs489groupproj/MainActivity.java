package com.victoriamatos.cs489groupproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void playSpelling(View v){
        Log.w("MA","Inside playSpelling");
        Intent intent = new Intent(this, SpellingMainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }
    public void playMath(View v){
        Intent intent = new Intent( this, MathMainActivity.class );
        startActivity( intent );
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }

    public void playGame(View v){
        Log.w("MA", "inside play game");
        Intent intent = new Intent( this, SpaceGameRules.class );
        startActivity( intent );
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }

    public void playAlphabet(View v){
        Log.w("MA", "inside play game");
        Intent intent = new Intent( this, AlphabetActivity.class );
        startActivity( intent );
        overridePendingTransition(R.anim.fade_in_and_scale,0);
    }
}