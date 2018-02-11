package pl.knowakowski.tetris;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by krzysiek on 10.02.2018.
 */

public abstract class Figure {
    protected ArrayList<Block> gameBoard;
    protected ArrayList<Block> blocksContainer;
    protected boolean canMoveDown = true;

    abstract public void moveRight();
    abstract public void moveLeft();

    abstract public void moveDown();

    abstract public void moveRotate();

    public ArrayList<Block> getBlocksToDraw(){
            return blocksContainer;
    }
    abstract public boolean canMoveDown();
}
