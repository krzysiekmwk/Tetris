package pl.knowakowski.tetris;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by krzysiek on 04.02.2018.
 */

public class GameActivity extends Activity{

    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private GameSurfaceView gameSurfaceView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameSurfaceView = (GameSurfaceView) findViewById(R.id.surfaceView_gamePanel);
        SurfaceHolder surfaceHolder = gameSurfaceView.getHolder();
        surfaceHolder.addCallback(gameSurfaceView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int yPos = 100;
                while(true) {
                    if (gameSurfaceView.isReady())
                        gameSurfaceView.drawDick(yPos);
                    if(yPos < 1000){
                        yPos += 10;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
