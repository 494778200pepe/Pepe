package com.hencoder.task.Ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author wang
 * @date 2017/10/23
 */
public class RulerView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static final int MINDEGREE = 20;
    public static final int MAXDEGREE = 80;
    int mUnitCount = 10;
    int mCenterDegree = 50;
    int mShowCount = 30;

    public RulerView(Context context) {
        super(context);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int left = getLeft();
        int top = getTop();
        int right = getRight();
        int bottom = getBottom();
        int centerX = (left + right) / 2;
        int width = getWidth();
        int height = getHeight();
        Log.d("pepe", "height = " + height);
        Log.d("pepe", "centerX = " + centerX);
        Log.d("pepe", "top = " + top);
        Log.d("pepe", "bottom = " + bottom);


        mPaint.setColor(Color.GRAY);
        canvas.drawRect(left, top, right, bottom, mPaint);

        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(centerX, top, centerX, top + height / 2, mPaint);




    }
}
