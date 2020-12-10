/**
 * Authors: Victoria Matos, Julia Hart, Kaytin Matrangola
 */
package com.victoriamatos.cs489groupproj;


import android.graphics.Point;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Timer;

public class SpaceGameActivity extends AppCompatActivity {
    private GameView gameView;
    private Game game;

    private GestureDetector detector;
    private int touched;

    private SoundPool pool;
    private int soundId;


    /**
     * Activity is initialized
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.w("GA", "Inside onCreate");
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //get rid of action bar
        touched = 0; //initialize touched to 0, no touches to screen yet

        //get window sizes
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        int statusBarHeight = 0;
        int statusBarId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarId > 0)
            statusBarHeight = getResources().getDimensionPixelSize(statusBarId);

        //create GameView
        gameView = new GameView(this, size.x, size.y - statusBarHeight);
        setContentView(gameView);
        game = gameView.getGame();

        //setup event handling
        TouchHandler th = new TouchHandler();
        detector = new GestureDetector(this, th);
        detector.setOnDoubleTapListener(th);

        //setup sound
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(1);
        pool = builder.build();
        soundId = pool.load(this,R.raw.explosion, 0);
    }

    /**
     * Play the sound of the pong ball hitting the ship
     */
    public void playHitSound() {
        Log.w("GA", "Inside playHitSound");
        pool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }



    /**
     * Actions for when a touch event occurs
     *
     * @param event
     * @return boolean
     */
    public boolean onTouchEvent(MotionEvent event) {
        Log.w("GA", "Inside onTouchEvent");
        touched++;
        if (touched == 1) {
            Timer gameTimer = new Timer();
            GameTimerTask task = new GameTimerTask(gameView);
            gameTimer.schedule(task, 0, GameView.DELTA_TIME);
        }
        detector.onTouchEvent(event);
        return true;
    }

    /**
     * TouchHandler class to process touches to screen
     */
    public class TouchHandler extends GestureDetector.SimpleOnGestureListener {

        /**
         * Moves ship on single confirmed tap
         *
         * @param event
         * @return boolean
         */
        public boolean onSingleTapConfirmed(MotionEvent event) {
            game.moveShip(event.getY());
            return true;
        }

        /**
         * Moves ship to follow scroll touch
         *
         * @param event1
         * @param event2
         * @param d1
         * @param d2
         * @return boolean
         */
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float d1, float d2) {
            game.moveShip(event2.getY());
            return true;
        }
    }
}


