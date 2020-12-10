/**
 * Authors: Victoria Matos, Julia Hart, Kaytin Matrangola
 */
package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SpaceGameRules extends AppCompatActivity {

    public static Game game; // to access bestScore

    /**
     * app activity is initialized
     * @param savedInstanceState, the activity's previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_rules);
        game = new Game(this);
        getSupportActionBar().hide();
    }

    public void startGame(View v){
        Intent intent = new Intent(this, SpaceGameActivity.class);
        startActivity(intent);
    }

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
