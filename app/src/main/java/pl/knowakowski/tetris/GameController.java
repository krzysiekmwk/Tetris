package pl.knowakowski.tetris;

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

    public void moveLeft(){
        actualBlock.setX(actualBlock.getX() - 1);
        repaint();
    }

    public void moveRight(){
        actualBlock.setX(actualBlock.getX() + 1);
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

        if(canMoveDown == true){
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
            gameSurfaceView.clearSurface();

            for (Block block: gameBoard){
                gameSurfaceView.drawBlock(block.getX(),block.getY(),block.getColor());
            }
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
