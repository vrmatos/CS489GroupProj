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
        Intent intent = new Intent(this, ChooseDiffActivity.class);
        startActivity(intent);
    }
}