package pl.knowakowski.tetris;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by krzysiek on 12.02.2018.
 */

public class SBlock extends Figure {

    private int position = 0;

    SBlock(ArrayList<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255,0,255,0);//Green

        blocksContainer.add(new Block(4,4,color));
        blocksContainer.add(new Block(5,4,color));
        blocksContainer.add(new Block(5,5,color));
        blocksContainer.add(new Block(6,5,color));

        Collections.sort(blocksContainer);
    }

    @Override
    public void moveRotate() {
        if(position == 0) {
            int x = blocksContainer.get(2).getX();
            int y = blocksContainer.get(2).getY();


        }

    }
}
