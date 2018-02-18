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
    private SurfaceHolder surfaceHolder;
    private Callback callback;
    private int scorePoints;

    private ArraySet<Block> gameBoard;
    private Figure actualFigure;
    private Figure nextFigure;

    private Random random;

    private static Canvas canvas;

    // MainThread thread;

    GameController(GameSurfaceView gameSurfaceView, Callback callback){
        this.gameSurfaceView = gameSurfaceView;
        surfaceHolder = gameSurfaceView.getHolder();
        this.callback = callback;
        gameBoard = new ArraySet<>();
        random = new Random();

        //thread = new MainThread(gameSurfaceView.getHolder(),this);
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
        showScorePoints();

        randomNewFigure();
        createNewFigure();
        randomNewFigure(); //TODO show randomFigure

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

    private void createNewFigure(){
        actualFigure = nextFigure;
    }

    private void repaint(){
        try{
            canvas = this.surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            synchronized (surfaceHolder){
                gameSurfaceView.drawFigure(actualFigure, canvas);
                gameSurfaceView.drawAllBlocks(gameBoard, canvas);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(canvas != null){
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
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

    @Override
    public void run() {
        while(isGameRunning) {
            if(actualFigure.canMoveDown()){
                moveDown();
            }else {

                scorePoints += removeFullRows();
                createNewFigure();
                randomNewFigure();

                //TODO show random Figure
                showScorePoints();

            }
            try {
                Thread.sleep(gameInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
