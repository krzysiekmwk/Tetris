package pl.knowakowski.tetris;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by krzysiek on 05.02.2018.
 */

public class Block {
    private int x;
    private int y;
    private Paint color;
    private Random random;

    Block(int x, int y){
        this.x = x;
        this.y = y;
        random = new Random();
        color = new Paint();
        color.setARGB(255, random.nextInt(255),random.nextInt(255),random.nextInt(255));

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
}
