package com.hencoder.task.Ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

import java.text.DecimalFormat;

/**
 * @author wang
 * @date 2017/10/23
 */
public class RulerView extends View implements View.OnTouchListener {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static final int MINDEGREE = 20;
    public static final int MAXDEGREE = 80;
    int unitCount = 10;
    int currentDegree = 50;
    int showCount = 3;
    float density;
    Rect rect;

    int backgroundColor = Color.parseColor("#F6F9F6");
    int pointerColor = Color.parseColor("#4ABB73");
    int highUnitColor = Color.parseColor("#DEE2DE");
    int lowUnitColor = Color.parseColor("#E3E6E3");
    int pointerWidth = 12;

    public RulerView(Context context) {
        super(context);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(40);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        density = displayMetrics.density;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取到的都是绝对值，而画的都是相对值
        //屏幕宽高720*1280,这两个也是px，不是dp
        //获取到的边界值是px，top = 340,bottom = 940，centerY = 640
        int left = getLeft();
        int top = getTop();
        int right = getRight();
        int bottom = getBottom();
        Log.d("pepe", "top = " + top);
        Log.d("pepe", "bottom = " + bottom);


        //这里的draw都是相对距离，以控件左上角为原点的，而不是以屏幕左上角，切记
        //获取到的宽度和高度，是px值,宽度,高度300*2 = 600
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        Log.d("pepe", "width = " + width);
        Log.d("pepe", "height = " + height);
        Log.d("pepe", "centerX = " + centerX);
        Log.d("pepe", "centerY = " + centerY);

        Log.d("pepe", "density = " + density);


        int unitTotalCount = unitCount * showCount;
        int unitWidth = width / unitTotalCount;
        //背景
        paint.setColor(backgroundColor);
        canvas.drawRect(centerX - unitCount * unitWidth * (currentDegree - MINDEGREE), 0, centerX + unitCount * unitWidth * (MAXDEGREE - currentDegree), height, paint);

        //刻度
        paint.setStrokeWidth(pointerWidth / 2);
        for (int i = -unitCount * (currentDegree - MINDEGREE); i <= unitCount * (MAXDEGREE - currentDegree); i++) {
            if (i % unitCount == 0) {
                paint.setColor(highUnitColor);
                canvas.drawLine(centerX + unitWidth * i, 0, centerX + unitWidth * i, (float) (centerY * 0.8), paint);
                paint.setColor(Color.BLACK);
                String degree = String.valueOf(i / unitCount + currentDegree);
                rect = new Rect();
                paint.getTextBounds(degree, 0, degree.length(), rect);
                canvas.drawText(degree, 0, degree.length(), centerX + unitWidth * i, centerY + 35 - (rect.top + rect.bottom) / 2, paint);

            } else {
                paint.setColor(lowUnitColor);
                canvas.drawLine(centerX + unitWidth * i, 0, centerX + unitWidth * i, (float) (centerY * 0.5), paint);
            }
        }


        //指针
        paint.setColor(pointerColor);
        paint.setStrokeWidth(pointerWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(centerX - offset - tempOffset, 0, centerX - offset - tempOffset, centerY, paint);

        double newDegree = currentDegree - (double) (offset + tempOffset) / unitWidth / 10;
        mDegreeChangeListener.degreeChange(new DecimalFormat("0.0").format(newDegree));
    }

    int startX, endX;
    int firstX, offset, tempOffset;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getRawX();
                firstX = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = (int) event.getX();
                int scrollDistance = endX - startX;
                tempOffset = endX - firstX;
                scrollBy(-scrollDistance, 0);
                startX = endX;
                break;
            case MotionEvent.ACTION_UP:
                endX = (int) event.getX();
                offset += endX - firstX;
                tempOffset = 0;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }


    interface DegreeChangeListener {
        void degreeChange(String degree);
    }

    private DegreeChangeListener mDegreeChangeListener;

    public void setDegreeChangeListener(DegreeChangeListener degreeChangeListener) {
        mDegreeChangeListener = degreeChangeListener;
    }


}
