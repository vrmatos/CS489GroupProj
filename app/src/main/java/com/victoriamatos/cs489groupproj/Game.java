package com.victoriamatos.cs489groupproj;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;

public class Game {
    /** Screen information */
    private int width;
    private int height;

    /** Ball and Ship variables */
    private Rect ship;
    private Point ball;
    private Point ball2;
    private int ballRadius;
    private int ball2Radius;

    private Point ball3;
    private int ball3Radius;

    private Point ball4;
    private int ball4Radius;

    private Point ball5;
    private int ball5Radius;

    /** The angle and speed for the ball */
    private float ballAngle;
    private float ballSpeed;
    private float ball2Angle;
    private float ball2Speed;

    private float ball3Angle;
    private float ball3Speed;

    private float ball4Angle;
    private float ball4Speed;

    private float ball5Angle;
    private float ball5Speed;

    private int deltaTime; //in milliSeconds

    /** Directions for Ball Movement */
    private boolean moveLeft;
    private boolean moveDown;

    /** Scores information */
    private int score;
    private int bestScore;

    /**
     * Persistent Data constructor
     * @param context
     */
    public Game(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setBestScore(pref.getInt("bestScore",0));
    }

    /**
     * Game constructor for GameView
     * @param context
     * @param newShip
     * @param newBall
     * @param newBallRadius
     * @param newBallSpeed
     * @param height (of screen)
     * @param width (of screen)
     */
    public Game(Context context, Rect newShip, Point newBall, Point newBall2, Point newBall3,Point newBall4,Point newBall5,
                int newBallRadius,int newBall2Radius, int newBall3Radius, int newBall4Radius, int newBall5Radius,
                float newBallSpeed, float newBall2Speed, float newBall3Speed,float newBall4Speed,float newBall5Speed,
                int height, int width){
        //account for persistent data
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        setBestScore(pref.getInt("bestScore",0));

        //initialize necessary Game information
        setShip(newShip);
        setBall(newBall);
        setBall2(newBall2);
        setBall3(newBall3);
        setBall4(newBall4);
        setBall5(newBall5);

        setBallSpeed(newBallSpeed);
        setBall2Speed(newBall2Speed);
        setBall3Speed(newBall3Speed);
        setBall4Speed(newBall4Speed);
        setBall5Speed(newBall5Speed);

        setBallAngle((float) Math.PI/4);
        setBall2Angle((float) Math.PI/4);
        setBall3Angle((float) Math.PI/4);
        setBall4Angle((float) Math.PI/4);
        setBall5Angle((float) Math.PI/4);


        setBallRadius(newBallRadius);
        setBall2Radius(newBall2Radius);
        setBall3Radius(newBall3Radius);
        setBall4Radius(newBall4Radius);
        setBall5Radius(newBall5Radius);


        this.height = height;
        this.width = width;
        moveLeft = false;
        moveDown = true;
        score = 0;
    }

    /** Getter methods */
    public Rect getShip(){ return ship;}

    public Point getBall(){ return ball;}
    public Point getBall2(){ return ball2;}
    public Point getBall3(){ return ball3;}
    public Point getBall4(){ return ball4;}
    public Point getBall5(){ return ball5;}

    public float getBallRadius(){ return ballRadius;}
    public float getBall2Radius(){ return ball2Radius;}
    public float getBall3Radius(){ return ball3Radius;}
    public float getBall4Radius(){ return ball4Radius;}
    public float getBall5Radius(){ return ball5Radius;}

    public int getBestScore(){return bestScore;}
    public int getScore(){return score;}

    /** Setter methods */
    public void setShip(Rect newShip){ship = newShip;}

    public void setBall(Point newBall){ball = newBall;}
    public void setBallAngle(float newBallAngle){ ballAngle = newBallAngle;}
    public void setBallRadius(int newBallRadius){ballRadius = newBallRadius;};

    public void setBall2(Point newBall2){ball2 = newBall2;}
    public void setBall2Angle(float newBall2Angle){ ball2Angle = newBall2Angle;}
    public void setBall2Radius(int newBall2Radius) { ball2Radius = newBall2Radius; }

    public void setBall3(Point newBall3){ball3 = newBall3;}
    public void setBall3Angle(float newBall3Angle){ ball3Angle = newBall3Angle;}
    public void setBall3Radius(int newBall3Radius) { ball3Radius = newBall3Radius; }

    public void setBall4(Point newBall4){ball4 = newBall4;}
    public void setBall4Angle(float newBall4Angle){ ball4Angle = newBall4Angle;}
    public void setBall4Radius(int newBall4Radius) { ball4Radius = newBall4Radius; }


    public void setBall5(Point newBall5){ball5 = newBall5;}
    public void setBall5Angle(float newBall5Angle){ ball5Angle = newBall5Angle;}
    public void setBall5Radius(int newBall5Radius) { ball5Radius = newBall5Radius; }




    public void setBestScore(int newBestScore){bestScore = newBestScore;}
    public void setDeltaTime(int newDeltaTime){
        if(newDeltaTime > 0)
            deltaTime = newDeltaTime;
    }
    public void setBallSpeed(float newBallSpeed){
        if(newBallSpeed > 0)
            ballSpeed = newBallSpeed;
    }
    public void setBall2Speed(float newBall2Speed){
        if(newBall2Speed > 0)
            ball2Speed = newBall2Speed;
    }
    public void setBall3Speed(float newBall3Speed){
        if(newBall3Speed > 0)
            ball3Speed = newBall3Speed;
    }
    public void setBall4Speed(float newBall4Speed){
        if(newBall4Speed > 0)
            ball4Speed = newBall4Speed;
    }
    public void setBall5Speed(float newBall5Speed){
        if(newBall5Speed > 0)
            ball5Speed = newBall5Speed;
    }


    /**
     * Moves the pong ball
     */
    public void moveBall(){
        // decides up/down movement
        if(moveDown)
            ball.y += ballSpeed * Math.sin(ballAngle) * deltaTime;
        else
            ball.y -= ballSpeed * Math.sin(ballAngle) * deltaTime;

        // decides left/right movement
        if(moveLeft)
            ball.x -= ballSpeed * Math.cos(ballAngle) * deltaTime;
        else
            ball.x += ballSpeed * Math.sin(ballAngle) * deltaTime;
    }

    public void moveBall2(){
        // decides up/down movement
        if(moveDown)
            ball2.y += ball2Speed * Math.sin(ball2Angle) * deltaTime;
        else
            ball2.y -= ball2Speed * Math.sin(ball2Angle) * deltaTime;
        // decides left/right movement
        if(moveLeft)
            ball2.x -= ball2Speed * Math.cos(ball2Angle) * deltaTime;
        else
            ball2.x += ball2Speed * Math.sin(ball2Angle) * deltaTime;
    }

    public void moveBall3(){
        // decides up/down movement
        if(moveDown)
            ball3.y += ball3Speed * Math.sin(ball3Angle) * deltaTime;
        else
            ball3.y -= ball3Speed * Math.sin(ball3Angle) * deltaTime;
        // decides left/right movement
        if(moveLeft)
            ball3.x -= ball3Speed * Math.cos(ball3Angle) * deltaTime;
        else
            ball3.x += ball3Speed * Math.sin(ball3Angle) * deltaTime;
    }

    public void moveBall4(){
        // decides up/down movement
        if(moveDown)
            ball4.y += ball4Speed * Math.sin(ball4Angle) * deltaTime;
        else
            ball4.y -= ball4Speed * Math.sin(ball4Angle) * deltaTime;
        // decides left/right movement
        if(moveLeft)
            ball4.x -= ball4Speed * Math.cos(ball4Angle) * deltaTime;
        else
            ball4.x += ball4Speed * Math.sin(ball4Angle) * deltaTime;
    }


    public void moveBall5(){
        // decides up/down movement
        if(moveDown)
            ball5.y += ball5Speed * Math.sin(ball5Angle) * deltaTime;
        else
            ball5.y -= ball5Speed * Math.sin(ball5Angle) * deltaTime;
        // decides left/right movement
        if(moveLeft)
            ball5.x -= ball5Speed * Math.cos(ball5Angle) * deltaTime;
        else
            ball5.x += ball5Speed * Math.sin(ball5Angle) * deltaTime;
    }


    /**
     * Moves the ship
     * @param newY, move ship by newy amt
     */
    public void moveShip(float newY){
        Log.w("Game", "Inside moveShip");
        float shift = ship.centerY() - newY;
        int currShipHeight = ship.height();
        if (ship.top - shift < 0){
            ship.top = 0;
            ship.bottom = currShipHeight;
        }else if (ship.bottom - shift > height){
            ship.top = height - currShipHeight;
            ship.bottom = height;
        } else{
            ship.top -= shift;
            ship.bottom -= shift;
        }
    }



    /** Check if ball has missed ship*/
    // public boolean missBall(){return (ball.y >= height);}

    /** Check if ball has hit a specific screen boundary */
    public boolean hitLeft()
    {
        return (ball.x - ballRadius <= 0);
    }
    public boolean hitRight()
    {
        return (ball.x + ballRadius >= width);
    }
    public boolean hitTop()
    {
        return (ball.y - ballRadius <= 0);
    }
    public boolean hitBottom()
    {
        return (ball.y >= height);
    }



    public boolean hitLeftBall2()
    {return (ball2.x - ball2Radius <= 0);
    }
    public boolean hitRightBall2()
    {
        return (ball2.x + ball2Radius >= width);
    }
    public boolean hitTopBall2()
    {
        return (ball2.y - ball2Radius <= 0);
    }
    public boolean hitBottomBall2()
    {
        return (ball2.y >= height);
    }


    public boolean hitLeftBall3()
    {return (ball3.x - ball3Radius <= 0);
    }
    public boolean hitRightBall3()
    {
        return (ball3.x + ball3Radius >= width);
    }
    public boolean hitTopBall3()
    {
        return (ball3.y - ball3Radius <= 0);
    }
    public boolean hitBottomBall3()
    {
        return (ball3.y >= height);
    }


    public boolean hitLeftBall4()
    {return (ball4.x - ball4Radius <= 0);
    }
    public boolean hitRightBall4()
    {
        return (ball4.x + ball4Radius >= width);
    }
    public boolean hitTopBall4()
    {
        return (ball4.y - ball4Radius <= 0);
    }
    public boolean hitBottomBall4()
    {
        return (ball4.y >= height);
    }



    public boolean hitLeftBall5()
    {return (ball5.x - ball5Radius <= 0);
    }
    public boolean hitRightBall5()
    {
        return (ball5.x + ball5Radius >= width);
    }
    public boolean hitTopBall5()
    {
        return (ball5.y - ball5Radius <= 0);
    }
    public boolean hitBottomBall5()
    {
        return (ball5.y >= height);
    }




    public boolean hitShip(){
        return ship.contains(ball.x,ball.y + ballRadius);
    }

    public boolean hitShip2(){
        return ship.contains(ball2.x,ball2.y + ball2Radius);
    }

    public boolean hitShip3(){
        return ship.contains(ball3.x,ball3.y + ball3Radius);
    }

    public boolean hitShip4(){
        return ship.contains(ball4.x,ball4.y + ball4Radius);
    }

    public boolean hitShip5(){
        return ship.contains(ball5.x,ball5.y + ball5Radius);
    }



    public boolean shipHitTop()
    {
        if(ship.top <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Process if any screen boundary has been hit. Update variables on directions
     * @return an int for specific boundaries hit, 0 if no hit
     */
    public int checkHit(){
        if(hitLeft()) {
            moveLeft = false;
            return 1;
        }else if (hitRight()) {
            moveLeft = true;
            return 2;
        }
        if(hitTop()) {
            moveDown = true;
            return 3;
        }
        else if(hitBottom()) {
            moveDown = false;
            return 4;
        }
        else if (hitShip())
        {
            //  score++;
            // moveDown = false;
            Log.w("MA", "ship was hit");
            return 5;
        }
        return 0;
    }

    public int checkHitBall2()
    {
        if(hitLeftBall2()) {
            moveLeft = false;
            return 1;
        }else if (hitRightBall2()) {
            moveLeft = true;
            return 2;
        }
        if(hitTopBall2()) {
            moveDown = true;
            return 3;
        }
        else if(hitBottomBall2()) {
            moveDown = false;
            return 4;
        }
        else if (hitShip2())
        {
            //score++;
            //moveDown = false;
            return 5;
        }
        return 0;
    }

    public int checkHitBall3()
    {
        if(hitLeftBall3()) {
            moveLeft = false;
            return 1;
        }else if (hitRightBall3()) {
            moveLeft = true;
            return 2;
        }
        if(hitTopBall3()) {
            moveDown = true;
            return 3;
        }
        else if(hitBottomBall3()) {
            moveDown = false;
            return 4;
        }
        else if (hitShip3())
        {
            //score++;
            // moveDown = false;
            return 5;
        }
        return 0;
    }

    public int checkHitBall4()
    {
        if(hitLeftBall4()) {
            moveLeft = false;
            return 1;
        }else if (hitRightBall4()) {
            moveLeft = true;
            return 2;
        }
        if(hitTopBall4()) {
            moveDown = true;
            return 3;
        }
        else if(hitBottomBall4()) {
            moveDown = false;
            return 4;
        }
        else if (hitShip4())
        {
            //score++;
            //moveDown = false;
            return 5;
        }
        return 0;
    }

    public int checkHitBall5()
    {
        if(hitLeftBall5()) {
            moveLeft = false;
            return 1;
        }else if (hitRightBall5()) {
            moveLeft = true;
            return 2;
        }
        if(hitTopBall5()) {
            moveDown = true;
            return 3;
        }
        else if(hitBottomBall5()) {
            moveDown = false;
            return 4;
        }
        else if (hitShip5())
        {
            // score++;
            // moveDown = false;
            return 5;
        }
        return 0;
    }

}