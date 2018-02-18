package pl.knowakowski.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import pl.knowakowski.tetris.blocks.Block;
import pl.knowakowski.tetris.blocks.Figure;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private boolean isSurfaceReady = false;
    private float blockWidth = 0;
    private float blockHeight = 0;
    private float pixelWidth = 0;
    private float pixelHeight = 0;


    public GameSurfaceView(Context context) {
        super(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        isSurfaceReady = true;

        pixelWidth = (float)getWidth()/111;
        blockWidth = pixelWidth *10;

        pixelHeight = (float)getHeight()/221;
        blockHeight = pixelHeight *10;
    }

    public boolean isSurfaceReady() {
        return isSurfaceReady;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isSurfaceReady = false;
    }

    private void drawBlock(int x, int y,Paint paint, Canvas canvas){
        float scaledX = x* pixelWidth + (x-1)*blockWidth;
        float scaledY = y* pixelHeight + (y-1)*blockHeight;

        canvas.drawRect(scaledX,scaledY,scaledX + blockWidth,scaledY + blockHeight,paint);
    }

    public void drawAllBlocks(ArraySet<Block> gameBoard, Canvas canvas){
        for (Block block: gameBoard){
            drawBlock(block.getX(),block.getY(),block.getColor(), canvas);
        }
    }

    public void drawFigure(Figure actualBlock, Canvas canvas) {
        for (Block block: actualBlock.getBlocksToDraw()){
            drawBlock(block.getX(),block.getY(),block.getColor(), canvas);
        }
    }
}
