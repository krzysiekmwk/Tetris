package pl.knowakowski.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by krzysiek on 10.02.2018.
 */

public class IBlock extends Figure{

    private int position = 1;

    IBlock(ArrayList<Block> gameBoard){
        this.gameBoard = gameBoard;
        blocksContainer = new ArrayList<>();

        Paint color = new Paint();
        color.setARGB(255, 255,255,0);//Yellow

        blocksContainer.add(new Block(5,4,color));
        blocksContainer.add(new Block(5,5,color));
        blocksContainer.add(new Block(5,6,color));
        blocksContainer.add(new Block(5,7,color));

        Collections.sort(blocksContainer);
    }

    @Override
    public void moveRotate() {
        boolean canRotate = true;
        for (Block blockInGameBoard : gameBoard) {

            if ((blockInGameBoard.getX()) == (blocksContainer.get(0).getX() - 2*position)
                    && (blockInGameBoard.getY()) == (blocksContainer.get(0).getY() - 2*position) ||
                    (blockInGameBoard.getX()) == (blocksContainer.get(1).getX() - 1*position)
                            && (blockInGameBoard.getY()) == (blocksContainer.get(1).getY() - 1*position) ||
                    (blockInGameBoard.getX()) == (blocksContainer.get(0).getX() + 1*position)
                            && (blockInGameBoard.getY()) == (blocksContainer.get(0).getY() + 1*position)) {

                canRotate = false;
            }
        }

        if(canRotate){
            blocksContainer.get(0).setX(blocksContainer.get(0).getX() - 2*position);
            blocksContainer.get(0).setY(blocksContainer.get(0).getY() - 2*position);

            blocksContainer.get(1).setX(blocksContainer.get(1).getX() - 1*position);
            blocksContainer.get(1).setY(blocksContainer.get(1).getY() - 1*position);

            blocksContainer.get(3).setX(blocksContainer.get(3).getX() + 1*position);
            blocksContainer.get(3).setY(blocksContainer.get(3).getY() + 1*position);

            position *= -1;
        }
    }
}
