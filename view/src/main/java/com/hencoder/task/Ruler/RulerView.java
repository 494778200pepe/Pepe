package com.hencoder.task.Ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wang
 * @date 2017/10/23
 */
public class RulerView extends View{
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

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
        paint.setColor(Color.GREEN);
        canvas.drawRect(left,top,right,bottom,paint);
    }
}
