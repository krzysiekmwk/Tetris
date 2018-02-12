package pl.knowakowski.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.Random;

/**
 * Created by krzysiek on 05.02.2018.
 */

public class Block implements Comparable<Block> {
    private int x;
    private int y;
    private Paint color;

    Block(int x, int y, Paint color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Paint getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y;
    }

    @Override
    public int compareTo(@NonNull Block block) {
        if(y > block.y)
            return -1;
        if(y < block.y)
            return 1;
        return 0;
    }
}
