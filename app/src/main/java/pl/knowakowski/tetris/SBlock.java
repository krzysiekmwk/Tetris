package pl.knowakowski.tetris;

import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by krzysiek on 12.02.2018.
 */

public class SBlock extends Figure {

    SBlock(ArrayList<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255,0,255,0);//Green

        blocksContainer.add(new Block(4,4,color));
        blocksContainer.add(new Block(5,4,color));
        blocksContainer.add(new Block(5,5,color));
        blocksContainer.add(new Block(6,5,color));

        rotationPoint = blocksContainer.get(2);

        Collections.sort(blocksContainer);
    }

}
