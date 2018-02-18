package pl.knowakowski.tetris.blocks;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;

import pl.knowakowski.tetris.ArraySet;

/**
 * Created by krzysiek on 13.02.2018.
 */

public class TBlock extends Figure {

    public TBlock(ArraySet<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255,204,0,153);//Purple

        blocksContainer.add(new Block(5,-2,color));
        blocksContainer.add(new Block(4,-1,color));
        blocksContainer.add(new Block(5,-1,color));
        blocksContainer.add(new Block(6,-1,color));

        rotationPoint = blocksContainer.get(2);

        Collections.sort(blocksContainer);
    }
}
