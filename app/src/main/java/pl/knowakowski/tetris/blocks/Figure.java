package pl.knowakowski.tetris.blocks;

import java.util.ArrayList;

import pl.knowakowski.tetris.ArraySet;

/**
 * Created by krzysiek on 10.02.2018.
 */

public abstract class Figure {
    protected ArraySet<Block> gameBoard;
    protected ArrayList<Block> blocksContainer;
    protected boolean canMoveDown = true;
    protected Block rotationPoint;

    public void moveRight(){
        if(canMoveDown) {
            boolean canMoveRight = true;
            for (Block block : blocksContainer) {
                for (Block blockInGameBoard : gameBoard) {
                    if ((blockInGameBoard.getX()) == (block.getX() + 1) && (blockInGameBoard.getY()) == (block.getY())) {
                        canMoveRight = false;
                    }
                }
                if ((block.getX() + 1) == 11)
                    canMoveRight = false;
            }

            if (canMoveRight) {
                for (Block block : blocksContainer) {
                    block.setX(block.getX() + 1);
                }
            }
        }
    }

    public void moveLeft(){
        if(canMoveDown) {
            boolean canMoveLeft = true;
            for (Block block : blocksContainer) {
                for (Block blockInGameBoard : gameBoard) {
                    if ((blockInGameBoard.getX()) == (block.getX() - 1) && (blockInGameBoard.getY()) == (block.getY())) {
                        canMoveLeft = false;
                    }
                }

                if ((block.getX() - 1) == 0)
                    canMoveLeft = false;
            }

            if (canMoveLeft) {
                for (Block block : blocksContainer) {
                    block.setX(block.getX() - 1);
                }
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
            }

            if (block.getY() == 20) { //down of board
                canMoveDown = false;
                gameBoard.addAll(blocksContainer);
                return;
            }
        }

        if(canMoveDown){
            for (Block block: blocksContainer) {
                block.setY(block.getY() + 1);
            }
        }
    }

    /*
        Algorithm for rotate figure
        There i'm using matrix
        R [0 -1] - for Rotation. This is matrix for -90 rotation.
          [1  0]
        pivot - is a point what we will rotate a Figure
        a,b - is a simple calculation for length of vector from pivot point
        vx, vy - is counted rotated vector
        pivotX + vx - new X point

        It's only simpler version than this:
        x1 = (x-pivotX)*cos(-90) - (y-pivotY)*sin(-90) + pivotX;
        y1 = (x-pivotX)*sin(-90) + (y-pivotY)*cos(-90) + pivotY;
    */
    public void moveRotate(){
        int pivotX = rotationPoint.getX();
        int pivotY = rotationPoint.getY();

        ArrayList<Block> tmp = new ArrayList<>();

        //Make actual copy
        for (Block block: blocksContainer) {
            tmp.add(new Block(block.getX(),block.getY(),block.getColor()));
        }

        boolean canRotate = true;
        //Rotate tmp object and check if everything is correct
        for (Block block: blocksContainer) {
            if(block != rotationPoint){
                int a = block.getX() - pivotX;
                int b = block.getY() - pivotY;

                int vx = -1*b;
                int vy = a;

                block.setX(pivotX + vx);
                block.setY(pivotY + vy);

                for (Block blockInGameBoard : gameBoard) {
                    if (((blockInGameBoard.getX()) == (block.getX())) && ((blockInGameBoard.getY()) == (block.getY()))
                            || (block.getX() <= 0) || (block.getX() >= 11)) {
                        canRotate = false;
                    }
                }
            }
        }

        //Back to previous version
        if(!canRotate){
            int i = 0;
            for (Block block: blocksContainer) {
                block.setX(tmp.get(i).getX());
                block.setY(tmp.get(i).getY());
                i++;
            }
        }
    }

    public ArrayList<Block> getBlocksToDraw(){
            return blocksContainer;
    }

    public boolean canMoveDown(){
        return canMoveDown;
    }

    public Block getRotationPoint() {
        return rotationPoint;
    }
}
