package com.victoriamatos.cs489groupproj;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;


public class GameView extends View {
    public static int TARGET = R.drawable.spaceship;
    public static int DELTA_TIME = 100;
    public static boolean win;
    public static boolean gameOver;
    Bitmap ships;
    private Game game;
    private Paint paint;
    //Boolean win;
    Rect ship;
    MainActivity act;
    //  Boolean gameOver =false;

    /**
     * GameView constructor
     * @param context
     * @param width (of screen)
     * @param height (of screen)
     */
    public GameView(Context context, int width, int height) {
        super(context);

        //setup paint object for ball and ship
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setStrokeWidth(10.0f);
        paint.setAntiAlias(true);

        //setup game
        ship = new Rect(350, height-100, 410,height);

        Point ball = new Point(100, 50);
        Point ball2 = new Point(200, 400);
        Point ball3 = new Point(300, 600);
        Point ball4 = new Point(200, 100);
        Point ball5 = new Point(300, 200);

        ships = BitmapFactory.decodeResource( getResources(), TARGET );

        game = new Game(context, ship, ball, ball2, ball3, ball4, ball5, 20,
                40, 30, 40,40, width*0.0005f, width*0.0005f,width*0.0005f, width*0.0005f, width*0.0005f,  height, width);
        game.setDeltaTime(DELTA_TIME);

    }

    /**
     * Draws to screen
     * @param canvas
     */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.w("GV", "Inside onDraw");
        setBackgroundColor(Color.BLACK);
        //draw the pong ball
        paint.setColor(Color.WHITE);
        canvas.drawCircle(game.getBall().x, game.getBall().y, game.getBallRadius(), paint);
        // paint.setColor(Color.MAGENTA);
        canvas.drawCircle(game.getBall2().x, game.getBall2().y, game.getBall2Radius(), paint);
        canvas.drawCircle(game.getBall3().x, game.getBall3().y, game.getBall3Radius(), paint);
        canvas.drawCircle(game.getBall4().x, game.getBall4().y, game.getBall4Radius(), paint);
        canvas.drawCircle(game.getBall5().x, game.getBall5().y, game.getBall5Radius(), paint);

        paint.setColor(Color.BLACK);
        //draw the ship
        canvas.drawRect(game.getShip(), paint);

        canvas.drawBitmap( ships, null,ship, paint );


        //if the ball is missed, run the endGame
        if (game.hitShip() || game.hitShip2() || game.hitShip3() || game.hitShip4() || game.hitShip5()) {
            win = false;
            endGame(canvas);
        } else if (game.shipHitTop()) {
            win = true;
            endGame(canvas);
        }
    }

    /**
     * Actions to perform once the ball has been missed and the game is over
     * @param
     */
    public void endGame(Canvas canvas) {
        paint.setTextSize(50);
        //if current score is the best score, record that and bring according message
        if (win) {
            paint.setColor(Color.BLUE);
            canvas.drawText("Congrats! You Win!!", 100, 550, paint);
        } else {
            paint.setColor(Color.BLUE);
            canvas.drawText("Try again! You Lose!", 100, 550, paint);
        }
    }



    /**
     * Returns GameView's Game object
     * @return
     */
    public Game getGame(){return game;}
}
