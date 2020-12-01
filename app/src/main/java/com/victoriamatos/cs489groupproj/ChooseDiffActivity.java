package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseDiffActivity extends AppCompatActivity {
    public static int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_difficulty);
        getSupportActionBar().hide();
        level = 0;

    }

    public void playEasy(View v){
        Log.w("CDA","Inside playEasy");
        level = 1;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void playMedium(View v){
        level = 2;
        Log.w("CDA","Inside playMedium");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void playHard(View v){
        level = 3;
        Log.w("CDA","Inside playHard");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}
