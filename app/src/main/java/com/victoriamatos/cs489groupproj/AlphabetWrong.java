/**
 * Authors: Victoria Matos, Julia Hart, Kaytin Matrangola
 */
package com.victoriamatos.cs489groupproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlphabetWrong extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.alphabet_incorrect);
            getSupportActionBar().hide();
            String my_string = getIntent().getStringExtra("attempt");
            TextView nameTV = (TextView) findViewById(R.id.whatsWrong);
            String name = "This is what you said: " + my_string;
            nameTV.setText(name);
        }

    public void goHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    }

