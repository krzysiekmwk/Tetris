package pl.knowakowski.tetris;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity{

    private GameSurfaceView gameSurfaceView;

    private Button buttonLeft;
    private Button buttonRight;
    private Button buttonDown;
    private Button buttonRotate;

    private GameController gameController;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameSurfaceView = findViewById(R.id.surfaceView_gamePanel);
        SurfaceHolder surfaceHolder = gameSurfaceView.getHolder();
        surfaceHolder.addCallback(gameSurfaceView);

        buttonLeft = findViewById(R.id.button_left);
        buttonRight = findViewById(R.id.button_right);
        buttonDown = findViewById(R.id.button_down);
        buttonRotate = findViewById(R.id.button_rotate);

        gameController = new GameController(gameSurfaceView);
        gameController.start();

        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.leftClick();
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.rightClick();
            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.downClick();
            }
        });

        buttonRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameController.rotateClick();
            }
        });


    }
}
