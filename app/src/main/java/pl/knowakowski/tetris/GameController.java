package pl.knowakowski.tetris;

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
    private Block actualBlock;

    GameController(GameSurfaceView gameSurfaceView){
        this.gameSurfaceView = gameSurfaceView;
        gameBoard = new ArrayList<>();
    }

    public void moveLeft(){
        boolean canMoveLeft = true;
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

        repaint();
    }

    public void moveRight(){
        boolean canMoveRight = true;
        for (Block block: gameBoard){
            if((block.getX()) == (actualBlock.getX() + 1) && (block.getY()) == (actualBlock.getY())) {
                canMoveRight = false;
                break;
            }
        }

        if(canMoveRight){
            if((actualBlock.getX() + 1) != 11)
                actualBlock.setX(actualBlock.getX() + 1);
        }

        repaint();
    }

    public void moveDown(){
        boolean canMoveDown = true;
        for (Block block: gameBoard){
            if((block.getY()) == (actualBlock.getY() + 1) && (block.getX()) == (actualBlock.getX())) {
                actualBlock = createNewBlock();
                canMoveDown = false;
                break;
            }
        }

        if(canMoveDown){
            if(actualBlock.getY() != 20) //down of board
                actualBlock.setY(actualBlock.getY() + 1);
            else
                actualBlock = createNewBlock();
        }
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
            gameSurfaceView.drawAllBlocks(gameBoard);
        }
    }

    @Override
    public void run() {
        while(isGameRunning) {
            if(actualBlock.getY() == 20){
                actualBlock = createNewBlock();
            }else{
                moveDown();
            }
            try {
                Thread.sleep(gameInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
