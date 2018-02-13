package pl.knowakowski.tetris.blocks;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;

import pl.knowakowski.tetris.ArraySet;

/**
 * Created by krzysiek on 13.02.2018.
 */

public class ZBlock extends Figure{

    public ZBlock(ArraySet<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255,255,0,0);//Red

        blocksContainer.add(new Block(4,4,color));
        blocksContainer.add(new Block(5,4,color));
        blocksContainer.add(new Block(5,5,color));
        blocksContainer.add(new Block(6,5,color));

        rotationPoint = blocksContainer.get(2);

        Collections.sort(blocksContainer);
    }
}
