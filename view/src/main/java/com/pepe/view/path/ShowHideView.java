package com.pepe.view.path;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.pepe.view.R;

public class ShowHideView extends View {

    private Bitmap mBitmap = null;
    private int limitLength = 0;     //
    private int bitmapWidth;
    private int bitmapHeight;
    private int dstWidth;
    private int dstHeight;
    int screenWidth;
    int screenHeight;
    private int CLIP_HEIGHT = 150;
    /**
     * 显示还是隐藏的状态，最开始为HIDE
     */
    private boolean status = HIDE;
    /**
     * 显示图片
     */
    private static final boolean SHOW = true;
    /**
     * 隐藏图片
     */
    private static final boolean HIDE = false;


    public ShowHideView(Context context) {
        this(context, null);
    }

    public ShowHideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.earth);
        limitLength = bitmapWidth = mBitmap.getWidth();
        bitmapHeight = mBitmap.getHeight();
        screenWidth = getWindowMsg(getContext())[0];
        screenHeight = getWindowMsg(getContext())[1];
        if (bitmapWidth > screenWidth) {
            dstWidth = screenWidth;
            limitLength = screenWidth;
            dstHeight = (int)(((double)screenWidth / bitmapWidth)*bitmapHeight);
        }
    }

    public ShowHideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 300;
        if (specMode == MeasureSpec.AT_MOST) {
            //相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            //相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        if (result > screenWidth) {
            result = screenWidth;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    /**
     * 获取屏幕宽高
     */
    public int[] getWindowMsg(Context context) {
        int[] wh = new int[2];
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wh[0] = wm.getDefaultDisplay().getWidth();
        wh[1] = wm.getDefaultDisplay().getHeight();
        Log.d("pepe", "屏幕宽 =" + wh[0]);
        Log.d("pepe", "屏幕高 =" + wh[1]);
        return wh;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Region region = new Region();
        int i = 0;
        while (i * CLIP_HEIGHT <= bitmapHeight) {
            //计算clip的区域
            if (i % 2 == 0) {
                region.union(new Rect(0, i * CLIP_HEIGHT, limitLength, (i + 1) * CLIP_HEIGHT));
            } else {
                region.union(new Rect(dstWidth - limitLength, i * CLIP_HEIGHT, dstWidth, (i + 1)
                        * CLIP_HEIGHT));
            }
            i++;
        }
        canvas.clipRegion(region);
        canvas.drawBitmap(mBitmap, new Rect(0,0,bitmapWidth,bitmapHeight),new RectF(0,0,dstWidth,dstHeight), new Paint());
        if (status == HIDE) {
            //如果此时是隐藏
            limitLength -= 10;
            if (limitLength <= 0) {
                status = SHOW;
            }
        } else {
            //如果此时是显示
            limitLength += 5;
            if (limitLength >= dstWidth) {
                status = HIDE;
            }
        }
        invalidate();
    }
}
