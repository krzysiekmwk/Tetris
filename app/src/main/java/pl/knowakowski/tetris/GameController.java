package pl.knowakowski.tetris;

import android.graphics.Color;
import android.graphics.Paint;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by krzysiek on 05.02.2018.
 */

public class GameController implements Runnable{

    private int panelWidth = 10;
    private int panelHeight = 20;
    private Thread gameThread = null;
    private boolean isGameRunning = false;
    private int gameInterval = 300;
    private GameSurfaceView gameSurfaceView;

    //private Block gameBoard[][] = new Block[panelWidth][panelHeight];
    private ArrayList<Block> gameBoard = new ArrayList<>();
    private Block actualBlock;

    GameController(GameSurfaceView gameSurfaceView){
        this.gameSurfaceView = gameSurfaceView;
    }

    public void leftClick(){
        actualBlock.setX(actualBlock.getX() - 1);
        repaint();
    }

    public void rightClick(){
        actualBlock.setX(actualBlock.getX() + 1);
        repaint();
    }

    public void downClick(){
        actualBlock.setY(actualBlock.getY() + 1);
        repaint();
    }

    public void rotateClick(){

        repaint();
    }

    public void start(){
        actualBlock = createNewBlock();
        isGameRunning = true;

        gameThread = new Thread(this);
        gameThread.start();
    }

    private Block createNewBlock(){
        Block block = new Block(5,1);
        gameBoard.add(block);

        return block;
    }

    private void repaint(){
        if (gameSurfaceView.isSurfaceReady()){
            gameSurfaceView.clearSurface();

            for (Block block: gameBoard){
                gameSurfaceView.drawBlock(block.getX(),block.getY(),block.getColor());
            }
        }
    }

    @Override
    public void run() {
        while(isGameRunning) {

            repaint();

            if(actualBlock.getY() == 20){
                actualBlock = createNewBlock();
            }else{
                actualBlock.setY(actualBlock.getY() + 1);
            }
            try {
                Thread.sleep(gameInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
