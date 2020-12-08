package com.victoriamatos.cs489groupproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

      //  AdView adView = new AdView( this );
        //adView.setAdSize( AdSize.SMART_BANNER );
       // String adUnitId ="ca-app-pub-3940256099942544/6300978111";
        //adView.setAdUnitId( adUnitId );

        //AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        // define t
        // arget area
        //adRequestBuilder.addKeyword("workout").addKeyword("fitness");
        //adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        //AdRequest adRequest = adRequestBuilder.build();

      //  LinearLayout adLayout = (LinearLayout) findViewById( R.id.ad_view );
        //adLayout.addView( adView );

        //adView.loadAd( adRequest );
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