package pl.knowakowski.tetris.blocks;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;

import pl.knowakowski.tetris.ArraySet;

/**
 * Created by krzysiek on 10.02.2018.
 */

public class IBlock extends Figure{

    public IBlock(ArraySet<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255, 0,255,255);//Light Blue

        blocksContainer.add(new Block(5,4,color));
        blocksContainer.add(new Block(5,5,color));
        blocksContainer.add(new Block(5,6,color));
        blocksContainer.add(new Block(5,7,color));

        rotationPoint = blocksContainer.get(1);

        Collections.sort(blocksContainer);
    }
}
