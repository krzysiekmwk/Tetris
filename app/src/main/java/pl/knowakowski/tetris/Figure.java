package pl.knowakowski.tetris;

import java.util.ArrayList;

/**
 * Created by krzysiek on 10.02.2018.
 */

public abstract class Figure {
    protected ArrayList<Block> gameBoard;
    protected ArrayList<Block> blocksContainer;
    protected boolean canMoveDown = true;

    public void moveRight(){
        boolean canMoveRight = true;
        for (Block block: blocksContainer) {
            for (Block blockInGameBoard : gameBoard) {
                if ((blockInGameBoard.getX()) == (block.getX() + 1) && (blockInGameBoard.getY()) == (block.getY())) {
                    canMoveRight = false;
                }
            }
            if((block.getX() + 1) == 11)
                canMoveRight = false;
        }

        if(canMoveRight){
            for (Block block: blocksContainer) {
                block.setX(block.getX() + 1);
            }
        }
    }

    public void moveLeft(){
        boolean canMoveLeft = true;
        for (Block block: blocksContainer){
            for (Block blockInGameBoard: gameBoard){
                if((blockInGameBoard.getX()) == (block.getX() - 1) && (blockInGameBoard.getY()) == (block.getY())) {
                    canMoveLeft = false;
                }
            }

            if((block.getX() - 1) == 0)
                canMoveLeft = false;
        }

        if(canMoveLeft){
            for (Block block: blocksContainer) {
                block.setX(block.getX() - 1);
            }
        }
    }

    public void moveDown(){
        canMoveDown = true;
        for (Block block: blocksContainer) {
            for (Block blockInGameBoard : gameBoard) {
                if ((blockInGameBoard.getY()) == (block.getY() + 1) && (blockInGameBoard.getX()) == (block.getX())) {
                    canMoveDown = false;
                    gameBoard.addAll(blocksContainer);
                    return;
                }

                if (block.getY() == 20) { //down of board
                    canMoveDown = false;
                    gameBoard.addAll(blocksContainer);
                    return;
                }
            }
        }

        if(canMoveDown){
            for (Block block: blocksContainer) {
                block.setY(block.getY() + 1);
            }
        }
    }

    public void moveRotate(){
        //RESORT NEEDED
    }

    public ArrayList<Block> getBlocksToDraw(){
            return blocksContainer;
    }

    public boolean canMoveDown(){
        return canMoveDown;
    }
}
