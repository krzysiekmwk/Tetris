package pl.knowakowski.tetris;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by krzysiek on 12.02.2018.
 */

public class SBlock extends Figure {

    SBlock(ArraySet<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255,0,255,0);//Green

        blocksContainer.add(new Block(6,4,color));
        blocksContainer.add(new Block(5,4,color));
        blocksContainer.add(new Block(5,5,color));
        blocksContainer.add(new Block(4,5,color));

        rotationPoint = blocksContainer.get(2);

        Collections.sort(blocksContainer);
    }

}
