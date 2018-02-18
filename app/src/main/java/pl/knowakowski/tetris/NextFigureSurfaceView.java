package pl.knowakowski.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import pl.knowakowski.tetris.blocks.Block;
import pl.knowakowski.tetris.blocks.Figure;
import pl.knowakowski.tetris.blocks.ZBlock;

/**
 * Created by krzysiek on 18.02.2018.
 */

public class NextFigureSurfaceView extends SurfaceView implements SurfaceHolder.Callback  {
    private boolean isSurfaceReady = false;
    private float blockWidth = 0;
    private float blockHeight = 0;
    private float pixelWidth = 0;
    private float pixelHeight = 0;

    public NextFigureSurfaceView(Context context) {
        super(context);
    }

    public NextFigureSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NextFigureSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        isSurfaceReady = true;

        pixelWidth = (float)getWidth()/45;
        blockWidth = pixelWidth *10;

        pixelHeight = (float)getHeight()/45;
        blockHeight = pixelHeight *10;
    }

    public boolean isSurfaceReady() {
        return isSurfaceReady;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isSurfaceReady = false;
    }

    private void drawBlock(int x, int y, Paint paint, Canvas canvas){
        x -= 2;
        y += 5;
        float scaledX = x* pixelWidth + (x-1)*blockWidth;
        float scaledY = y* pixelHeight + (y-1)*blockHeight;

        canvas.drawRect(scaledX,scaledY,scaledX + blockWidth,scaledY + blockHeight,paint);
    }

    public void showNextFigure(Figure nextFigure, Canvas canvas){
        for (Block block: nextFigure.getBlocksToDraw()){
            drawBlock(block.getX(),block.getY(),block.getColor(), canvas);
        }
    }

}
