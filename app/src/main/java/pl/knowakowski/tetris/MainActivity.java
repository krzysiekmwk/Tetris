package pl.knowakowski.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by krzysiek on 21.02.2018.
 */

public class MainActivity extends Activity {

    private Button buttonGoToGame;
    private Button buttonExitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToGame = findViewById(R.id.button_goToGame);
        buttonExitGame = findViewById(R.id.button_exitApplication);

        buttonGoToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });

        buttonExitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }
}
