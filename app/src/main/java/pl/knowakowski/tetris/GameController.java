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
        /*boolean canMoveLeft = true;
        for (Block block: gameBoard){
            if((block.getX()) == (actualBlock.getX() - 1) && (block.getY()) == (actualBlock.getY())) {
                canMoveLeft = false;
                break;
            }
        }

        if(canMoveLeft){
            if((actualBlock.getX() - 1) != 0)
                actualBlock.setX(actualBlock.getX() - 1);
        }

        repaint();*/
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
                createNewFigure();
            }
            /*if(actualBlock.getY() == 20){
                createNewFigure();
            }else{
                moveDown();
            }*/
            try {
                Thread.sleep(gameInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
