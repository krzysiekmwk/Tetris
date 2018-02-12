package pl.knowakowski.tetris;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by krzysiek on 05.02.2018.
 */

public class GameController implements Runnable{

    private Thread gameThread = null;
    private boolean isGameRunning = false;
    private int gameInterval = 300;
    private GameSurfaceView gameSurfaceView;

    private ArrayList<Block> gameBoard;
    private Figure actualBlock;

    GameController(GameSurfaceView gameSurfaceView){
        this.gameSurfaceView = gameSurfaceView;
        gameBoard = new ArrayList<>();
    }

    public void moveLeft(){
        actualBlock.moveLeft();
        repaint();
    }

    public void moveRight(){
        actualBlock.moveRight();
        repaint();

    }

    public void moveDown(){
        actualBlock.moveDown();
        repaint();
    }

    public void rotateClick(){
        actualBlock.moveRotate();
        repaint();
    }

    public void start(){
        createNewFigure();
        isGameRunning = true;

        gameThread = new Thread(this);
        gameThread.start();

        Paint paint = new Paint();
        paint.setARGB(255,255,0,0);
        gameBoard.add(new Block(5,15,paint));
    }

    private void createNewFigure(){
        actualBlock = new IBlock(gameBoard);
        //actualBlock = new SBlock(gameBoard);
    }

    private void repaint(){
        if (gameSurfaceView.isSurfaceReady()){
            gameSurfaceView.clearSurface();
            gameSurfaceView.drawFigure(actualBlock);
            gameSurfaceView.drawAllBlocks(gameBoard);
            gameSurfaceView.showSurface();
        }
    }

    @Override
    public void run() {
        while(isGameRunning) {
            if(actualBlock.canMoveDown()){
                moveDown();
            }else {
                //Check if can remove row
                createNewFigure();
            }
            try {
                Thread.sleep(gameInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
