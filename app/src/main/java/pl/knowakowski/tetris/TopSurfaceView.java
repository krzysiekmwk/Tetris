package pl.knowakowski.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

import pl.knowakowski.tetris.blocks.Block;
import pl.knowakowski.tetris.blocks.Figure;

public class TopSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private boolean isSurfaceReady = false;

    public TopSurfaceView(Context context) {
        super(context);
    }

    public TopSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        isSurfaceReady = true;
    }

    public boolean isSurfaceReady() {
        return isSurfaceReady;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isSurfaceReady = false;
    }

    public void drawCenterMessage(Canvas canvas, String message){
        float x = getWidth()/2;
        float y = getHeight()/2;

        Paint paint = new Paint();
        paint.setARGB(255,255,0,0);
        paint.setTextSize(150);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(message,x,y,paint);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("X: " + event.getX() + ", Y: " + event.getY());
        return true;
    }*/
}
