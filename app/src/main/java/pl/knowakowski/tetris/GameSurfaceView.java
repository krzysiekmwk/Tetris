package pl.knowakowski.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;
import java.util.logging.Handler;

import static android.content.ContentValues.TAG;

/**
 * Created by krzysiek on 05.02.2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
    Bitmap mBitmap;
    Thread thread = null;
    Context ctx;

    public GameSurfaceView(Context context) {
        super(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        thread = new Thread(this);
        thread.start();
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap,0,0,null);//Przy obrocie ekranu sie crash robi
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d("dotyk", event.getX()+" "+event.getY());
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        while (getWidth() == 0); //Wait until SurfaceView will
        int yPos = 400;
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config. ARGB_8888);
        while (true) {
            //mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config. ARGB_8888);
            Canvas canvas = new Canvas(mBitmap);
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(200,yPos,100,paint);
            canvas.drawCircle(350,yPos,100,paint);
            canvas.drawRect(230,yPos,320,yPos-400,paint);
            canvas.drawCircle(275,yPos-400,45,paint);
            postInvalidate();
            if(yPos < 1000){
                yPos += 10;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mBitmap.eraseColor(Color.TRANSPARENT);

        }
    }
}
