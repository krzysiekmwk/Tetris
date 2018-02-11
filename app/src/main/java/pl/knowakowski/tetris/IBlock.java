package pl.knowakowski.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by krzysiek on 10.02.2018.
 */

public class IBlock extends Figure{

    IBlock(ArrayList<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255, 255,255,0);//Yellow

        blocksContainer.add(new Block(5,4,color));
        blocksContainer.add(new Block(5,3,color));
        blocksContainer.add(new Block(5,2,color));
        blocksContainer.add(new Block(5,5,color));

        Collections.sort(blocksContainer);
    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveDown() {
        for (Block block: blocksContainer){
            canMoveDown = true;
            for (Block blockInGameBoard: gameBoard){
                if((blockInGameBoard.getY()) == (block.getY() + 1) && (blockInGameBoard.getX()) == (block.getX())) {
                    canMoveDown = false;
                    gameBoard.addAll(blocksContainer);
                    return;
                }
            }

            if(canMoveDown){
                if(block.getY() != 20) //down of board
                    block.setY(block.getY() + 1);
                else{
                    canMoveDown = false;
                    gameBoard.addAll(blocksContainer);
                    return;
                }
            }
        }
    }

    @Override
    public boolean canMoveDown() {
        return canMoveDown;
    }

    @Override
    public void moveRotate() {

    }
}
