package pl.knowakowski.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    private Bitmap mBitmap;
    private boolean isSurfaceReady = false;
    private float blockWidth = 0;
    private float blockHeight = 0;
    private float borderVerticalWidth = 0;
    private float borderHorizontalWidth = 0;


    public GameSurfaceView(Context context) {
        super(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap,0,0,null);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        isSurfaceReady = true;
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config. ARGB_8888);

        borderVerticalWidth = (float)getWidth()/111;
        blockWidth = borderVerticalWidth*10;

        borderHorizontalWidth = (float)getHeight()/221;
        blockHeight = borderHorizontalWidth*10;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isSurfaceReady = false;
    }

    public boolean isSurfaceReady() {
        return isSurfaceReady;
    }

    public void drawBlock(int x, int y,Paint paint){
        float xw = x*borderVerticalWidth + (x-1)*blockWidth;
        float yw = y*borderHorizontalWidth + (y-1)*blockHeight;

        Canvas canvas = new Canvas(mBitmap);
        canvas.drawRect(xw,yw,xw + blockWidth,yw + blockHeight,paint);

        postInvalidate();
    }

    public void clearSurface(){
        mBitmap.eraseColor(Color.TRANSPARENT);
    }


}
