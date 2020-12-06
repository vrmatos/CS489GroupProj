package com.victoriamatos.cs489groupproj;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AlphabetActivity extends AppCompatActivity {
    public static final int VOICE_CODE = 1;
    public static TextView textview_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabet_layout);
        textview_id = (TextView) findViewById(R.id.text);
        getSupportActionBar().hide();

        // check if voice recognition is installed
        PackageManager manager = getPackageManager();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        List<ResolveInfo> list = manager.queryIntentActivities( intent, 0 );
        if( list.size() > 0 ) {
            Log.w( "MA", "MA: speech recognition is supported" );
            askPermission( );

        } else {
            Log.w( "MA", "MA: Specify other means of user input here" );
        }
    }

    public void askPermission( ) {
        if( ContextCompat.checkSelfPermission( this, Manifest.permission.RECORD_AUDIO ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String [] { Manifest.permission.RECORD_AUDIO}, VOICE_CODE );
            Log.w( "MA", "MA: Permission is granted" );
            listen( );

        } else { // condition was granted earlier
            // start listening
            Log.w( "MA", "MA: Permission was granted earlier" );
            listen( );
        }
    }

    public void listen( ) {
        Intent intentToListen = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        intentToListen.putExtra( RecognizerIntent.EXTRA_PROMPT, "Say the alphabet in order" );
        intentToListen.putExtra( RecognizerIntent.EXTRA_MAX_RESULTS, 26 );
        intentToListen.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );

        startActivityForResult( intentToListen, VOICE_CODE );

    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );
        Log.w( "MA", "MA: inside onActivityResult, request: " + requestCode + "; resultCode: " + resultCode
                + ";data = " + data );

        if( requestCode == VOICE_CODE && resultCode == RESULT_OK && data != null ) {
            ArrayList<String> returnedWords = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //float[] scores = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
            Log.w("MA", "word: " + returnedWords.get(0));
            if ("A B C D E F G H I J K L M N O P Q R S T U V W X Y and Z".equals(returnedWords.get(0))
                    || "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".equals(returnedWords.get(0)) ||"ABCDEFGHIJKLMNOPQRSTUVWXYZ".equals(returnedWords.get(0))) {
                textview_id.setText("green");
                textview_id.setBackgroundColor(Color.GREEN);
                Toast toast = Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_LONG);
                toast.show();
            } else {
                textview_id.setBackgroundColor(Color.RED);
                Toast toast = Toast.makeText(getApplicationContext(), "Sorry that is not correct!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}