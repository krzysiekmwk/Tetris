package pl.knowakowski.tetris;

import android.graphics.Paint;
import android.support.annotation.NonNull;

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

    @Override
    public int hashCode() {
        int hash = 28;
        hash = hash*31 + x;
        hash = hash*31 + y;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Block))
            return false;
        Block block = (Block)o;

        return (block.x == x) && (block.y == y);
    }
}
