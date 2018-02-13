package pl.knowakowski.tetris;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by krzysiek on 05.02.2018.
 */

public class GameController implements Runnable{

    private Thread gameThread = null;
    private boolean isGameRunning = false;
    private int gameInterval = 300;
    private GameSurfaceView gameSurfaceView;

    ArraySet<Block> gameBoard;
    private Figure actualBlock;

    GameController(GameSurfaceView gameSurfaceView){
        this.gameSurfaceView = gameSurfaceView;
        gameBoard = new ArraySet<>();
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

    private void createNewFigure(){
        //actualBlock = new IBlock(gameBoard);
        //actualBlock = new JBlock(gameBoard);
        //actualBlock = new LBlock(gameBoard);
        //actualBlock = new OBlock(gameBoard);
        //actualBlock = new SBlock(gameBoard);
        actualBlock = new TBlock(gameBoard);
        //actualBlock = new ZBlock(gameBoard);
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

    private int removeFullRows(){
        ArrayList<Integer> numberOfRemovedRows = new ArrayList();
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

    @Override
    public void run() {
        while(isGameRunning) {
            if(actualBlock.canMoveDown()){
                moveDown();
            }else {

                removeFullRows();
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
