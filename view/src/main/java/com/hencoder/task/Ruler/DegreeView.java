package com.hencoder.task.Ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 49477
 * @date 2017/10/25.
 */

public class DegreeView extends View {
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint unitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String mDegree = "50";
    String mUnit = "kg";
    int textColor = Color.parseColor("#4ABB73");
    int textSize = 40;
    int unitSize = 20;

    public DegreeView(Context context) {
        super(context);
    }

    public DegreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DegreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setTextAlign(Paint.Align.CENTER);
        unitPaint.setTextSize(unitSize);
        unitPaint.setColor(textColor);
        unitPaint.setTextAlign(Paint.Align.LEFT);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Rect rect = new Rect();
        textPaint.getTextBounds(mDegree, 0, mDegree.length(), rect);
        canvas.drawText(mDegree, width / 2, height / 2 - (rect.top + rect.bottom) / 2, textPaint);
        canvas.drawText(mUnit, width / 2 + (rect.left + rect.right) / 2, height / 2 + (rect.top + rect.bottom) / 2, unitPaint);
    }

    public void setDegree(String degree) {
        mDegree = degree;
        invalidate();
    }
}