package pl.knowakowski.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity implements Callback {

    private GameSurfaceView gameSurfaceView;
    private NextFigureSurfaceView nextFigureSurfaceView;
    private SurfaceView topSurfaceView;

    private Button buttonLeft;
    private Button buttonRight;
    private Button buttonDown;
    private Button buttonRotate;
    private Button buttonPause;
    private Button buttonPlay;

    private GameController gameController;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameSurfaceView = findViewById(R.id.surfaceView_gamePanel);
        SurfaceHolder surfaceHolderGameSurfaceView = gameSurfaceView.getHolder();
        surfaceHolderGameSurfaceView.addCallback(gameSurfaceView);

        nextFigureSurfaceView = findViewById(R.id.surfaceView_nextItem);
        SurfaceHolder surfaceHolderNextFigureSurfaceView = nextFigureSurfaceView.getHolder();
        surfaceHolderNextFigureSurfaceView.addCallback(nextFigureSurfaceView);

        topSurfaceView = findViewById(R.id.surfaceView_aboveGameSurfaceView);
        topSurfaceView.setZOrderOnTop(true);

        buttonLeft = findViewById(R.id.button_left);
        buttonRight = findViewById(R.id.button_right);
        buttonDown = findViewById(R.id.button_down);
        buttonRotate = findViewById(R.id.button_rotate);
        buttonPause = findViewById(R.id.button_pause);
        buttonPlay = findViewById(R.id.button_play);

        gameController = new GameController(gameSurfaceView, nextFigureSurfaceView, this);

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.moveLeft();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.moveRight();
            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.moveDown();
            }
        });

        buttonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.rotateClick();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.pause();
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.play();
            }
        });

    }

    @Override
    public void updateTextView(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView scorePointsTextView = findViewById(R.id.textView_scorePoints);
                scorePointsTextView.setText(str);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameController.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameController.start();
    }
}
