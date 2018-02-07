package pl.knowakowski.tetris;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;

public class GameActivity extends Activity{

    private GameSurfaceView gameSurfaceView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameSurfaceView = findViewById(R.id.surfaceView_gamePanel);
        SurfaceHolder surfaceHolder = gameSurfaceView.getHolder();
        surfaceHolder.addCallback(gameSurfaceView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int yPos = 5;
                while(true) {
                    if (gameSurfaceView.isSurfaceReady()){
                        gameSurfaceView.clearSurface();
                        Paint paint = new Paint();
                        paint.setColor(Color.YELLOW);
                        gameSurfaceView.drawBlock(5,yPos,paint);
                        paint.setColor(Color.GREEN);
                        gameSurfaceView.drawBlock(7,yPos,paint);

                    }
                    if(yPos < 20){
                        yPos++;
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
