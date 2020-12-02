package com.victoriamatos.cs489groupproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MathMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_game_home);
    }

    public void goToMathHomePageAdd(View view){
        Intent intent = new Intent( this, MathGameActivity.class );
        intent.putExtra("type", '+');
        startActivity( intent );

    }

    public void goToMathHomePageSub(View view){
        Intent intent = new Intent( this, MathGameActivity.class );
        intent.putExtra("type", '-');
        startActivity( intent );

    }

    public void goToMathHomePageMult(View view){
        Intent intent = new Intent( this, MathGameActivity.class );
        intent.putExtra("type", 'x');
        startActivity( intent );
    }

    public void goToMathHomePageDiv(View view){
        Intent intent = new Intent( this, MathGameActivity.class );
        intent.putExtra("type", '/');
        startActivity( intent );

    }

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}