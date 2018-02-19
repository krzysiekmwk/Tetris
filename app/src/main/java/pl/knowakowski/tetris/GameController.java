package pl.knowakowski.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import pl.knowakowski.tetris.blocks.*;

/**
 * Created by krzysiek on 05.02.2018.
 */

public class GameController implements Runnable{

    private Thread gameThread = null;
    private boolean isGameRunning = false;
    private int gameInterval = 300;

    private GameSurfaceView gameSurfaceView;
    private NextFigureSurfaceView nextFigureSurfaceView;
    private SurfaceHolder gameSurfaceHolder;
    private SurfaceHolder nextFigureSurfaceHolder;
    private Callback callback;
    private int scorePoints;

    private ArraySet<Block> gameBoard;
    private Figure actualFigure;
    private Figure nextFigure;

    private Random random;

    private static Canvas canvasGameView;
    private static Canvas nextFigureView;

    GameController(GameSurfaceView gameSurfaceView, NextFigureSurfaceView nextFigureSurfaceView,Callback callback){
        this.gameSurfaceView = gameSurfaceView;
        gameSurfaceHolder = gameSurfaceView.getHolder();

        this.nextFigureSurfaceView = nextFigureSurfaceView;
        nextFigureSurfaceHolder = nextFigureSurfaceView.getHolder();

        this.callback = callback;
        gameBoard = new ArraySet<>();
        random = new Random();
    }

    public void moveLeft(){
        actualFigure.moveLeft();
        repaint();
    }

    public void moveRight(){
        actualFigure.moveRight();
        repaint();

    }

    public void moveDown(){
        actualFigure.moveDown();
        repaint();
    }

    public void rotateClick(){
        actualFigure.moveRotate();
        repaint();
    }

    public void start(){
        resetGame();
        /*scorePoints = 0;
        showScorePoints();

        randomNewFigure();
        createNewFigure();
        randomNewFigure();*/

        isGameRunning = true;

        Paint paint = new Paint();
        paint.setARGB(255,255,0,0);
        /*gameBoard.add(new Block(1,20,paint));
        gameBoard.add(new Block(2,20,paint));
        gameBoard.add(new Block(3,20,paint));
        gameBoard.add(new Block(4,20,paint));
        gameBoard.add(new Block(5,20,paint));
        gameBoard.add(new Block(6,20,paint));
        gameBoard.add(new Block(7,20,paint));
        gameBoard.add(new Block(8,20,paint));
        gameBoard.add(new Block(10,20,paint));

        gameBoard.add(new Block(1,19,paint));
        gameBoard.add(new Block(3,19,paint));
        gameBoard.add(new Block(4,19,paint));
        gameBoard.add(new Block(5,19,paint));
        gameBoard.add(new Block(6,19,paint));
        gameBoard.add(new Block(7,19,paint));
        gameBoard.add(new Block(8,19,paint));
        gameBoard.add(new Block(9,19,paint));
        gameBoard.add(new Block(10,19,paint));

        gameBoard.add(new Block(1,18,paint));
        gameBoard.add(new Block(4,18,paint));
        gameBoard.add(new Block(5,18,paint));
        gameBoard.add(new Block(6,18,paint));
        gameBoard.add(new Block(7,18,paint));
        gameBoard.add(new Block(8,18,paint));
        gameBoard.add(new Block(9,18,paint));
        gameBoard.add(new Block(10,18,paint));

        gameBoard.add(new Block(1,17,paint));
        gameBoard.add(new Block(3,17,paint));
        gameBoard.add(new Block(4,17,paint));
        gameBoard.add(new Block(5,17,paint));
        gameBoard.add(new Block(6,17,paint));
        gameBoard.add(new Block(7,17,paint));
        gameBoard.add(new Block(8,17,paint));
        gameBoard.add(new Block(9,17,paint));
        gameBoard.add(new Block(10,17,paint));

        gameBoard.add(new Block(5,16,paint));*/

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void stop(){
        while (true){
            try{
                isGameRunning = false;
                gameThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void randomNewFigure(){
        /*
            0 - 4    - IBlock - 5%
            5 - 19   - JBlock - 15%
            20 - 34  - LBlock - 15%
            35 - 39  - OBlock - 5%
            40 - 59  - SBlock - 20%
            60 - 84  - ZBlock - 25%
            85 - 99  - TBlock - 15%
         */
        int randomValue = random.nextInt(100);

        if(randomValue >= 0 && randomValue <= 4)
            nextFigure = new IBlock(gameBoard);
        if(randomValue >= 5 && randomValue <= 19)
            nextFigure = new JBlock(gameBoard);
        if(randomValue >= 20 && randomValue <= 34)
            nextFigure = new LBlock(gameBoard);
        if(randomValue >= 35 && randomValue <= 39)
            nextFigure = new OBlock(gameBoard);
        if(randomValue >= 40 && randomValue <= 59)
            nextFigure = new SBlock(gameBoard);
        if(randomValue >= 60 && randomValue <= 84)
            nextFigure = new ZBlock(gameBoard);
        if(randomValue >= 85 && randomValue <= 99)
            nextFigure = new TBlock(gameBoard);
    }

    private boolean checkIfEndGame(){
        for (Block blockInGameBoard : gameBoard) {
            if(blockInGameBoard.getY() == 1){
                return true;
            }
        }
        return false;
    }

    private void createNewFigure(){
        actualFigure = nextFigure;
    }

    private void repaint(){
        try{
            canvasGameView = this.gameSurfaceHolder.lockCanvas();
            synchronized (gameSurfaceHolder){
                canvasGameView.drawColor(Color.BLACK);
                gameSurfaceView.drawFigure(actualFigure, canvasGameView);
                gameSurfaceView.drawAllBlocks(gameBoard, canvasGameView);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(canvasGameView != null){
                try{
                    gameSurfaceHolder.unlockCanvasAndPost(canvasGameView);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void showNextFigure(){
        try{
            nextFigureView = this.nextFigureSurfaceHolder.lockCanvas();
            synchronized (nextFigureSurfaceHolder){
                nextFigureView.drawColor(Color.BLACK);
                nextFigureSurfaceView.showNextFigure(nextFigure, nextFigureView);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(nextFigureView != null){
                try{
                    nextFigureSurfaceHolder.unlockCanvasAndPost(nextFigureView);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private int removeFullRows(){
        ArrayList<Integer> numberOfRemovedRows = new ArrayList<>();
        HashSet<Block> tmp = new HashSet<>();
        for(int i = 0; i <= 20; i++){
            int count = 0;

            for (Block blockInGameBoard : gameBoard) {
                if(blockInGameBoard.getY() == i){
                    count++;
                }
            }

            if(count == 10){
                for (Block blockInGameBoard : gameBoard) {
                    if(blockInGameBoard.getY() == i){
                        tmp.add(blockInGameBoard);
                    }
                }
                numberOfRemovedRows.add(i);
            }
        }

        gameBoard.removeAll(tmp);

        Collections.sort(numberOfRemovedRows);
        for (int row : numberOfRemovedRows) {
            for (Block blockInGameBoard : gameBoard) {
                if (blockInGameBoard.getY() < row){
                    blockInGameBoard.setY(blockInGameBoard.getY() + 1);
                }

            }
        }

        return numberOfRemovedRows.size();
    }

    private void showScorePoints(){
        callback.updateTextView(scorePoints + "");
    }

    private void resetGame(){
        gameBoard.clear();
        randomNewFigure();
        createNewFigure();
        randomNewFigure();
        repaint();
        scorePoints = 0;
        showScorePoints();
        showNextFigure();
    }

    @Override
    public void run() {
        while(isGameRunning) {
            try {
                if(!gameSurfaceView.isSurfaceReady() && !nextFigureSurfaceView.isSurfaceReady())
                    Thread.sleep(10);
                else {
                    showNextFigure();
                    if (actualFigure.canMoveDown()) {
                        moveDown();

                    } else {
                        if (checkIfEndGame()) {
                            resetGame();
                            Thread.sleep(2000);
                        }
                        else {
                            scorePoints += removeFullRows();
                            createNewFigure();
                            randomNewFigure();

                            showScorePoints();
                        }

                    }

                    Thread.sleep(gameInterval);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause() {

    }


    public void play() {

    }
}
