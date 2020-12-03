package com.victoriamatos.cs489groupproj;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {
    private GameView gameView;
    private Game game;

    /**
     * GameTimerTask constructor
     * @param view
     */
    public GameTimerTask(GameView view){
        gameView = view;
        game = gameView.getGame();
    }

    /**
     * Run the game
     */
    @Override
    public void run(){
        //update model/game
        if(game.checkHit() == 5 || game.checkHitBall2()==5 || game.checkHitBall3() ==5 || game.checkHitBall4() ==5
                ||game.checkHitBall5()==5) {//4 = the hitPaddle number
           // ((SpaceGameActivity) gameView.getContext()).playHitSound();

        }
        game.moveBall();
        game.moveBall2();
        game.moveBall3();
        game.moveBall4();
        game.moveBall5();

        if(game.hitShip() || game.hitShip2() || game.hitShip3() || game.hitShip4() || game.hitShip5() || game.shipHitTop()) {
            cancel();
        }
        //update game view
        gameView.postInvalidate();
    }
}