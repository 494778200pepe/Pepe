package com.pepe.view.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

//设置字符位置，字符以坐标（x,y）为源点。y为字符的下边缘
//center表示字符在水平方向上以x中心向左右两边延伸，在垂直方向以y为起点向下延伸；
//left表示字符在水平方向上以x为起点向右延伸，在垂直方向上以y为起点向下延伸；
//right表示字符在水平方向上以x为起点向左延伸，在垂直方向上以y为起点向下延伸。
public class View_Paint_Align extends View {

    private Paint mPaint;

    public View_Paint_Align(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true); // 消除锯齿

        float x = 300;
        float y = 0;
        int DY = 50;
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(30);
        mPaint.setColor(0x80FF0000);
        canvas.drawLine(x, y, x, y + DY * 3, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.translate(0, DY);
        //设置字符位置，字符以坐标（x,y）为源点。
        //left表示字符在水平方向上以x为起点向右延伸，在垂直方向上以y为起点向下延伸；
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("left", x, y, mPaint);

        canvas.translate(0, DY);
        //center表示字符在水平方向上以x中心向左右两边延伸，在垂直方向以y为起点向下延伸；
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("center", x, y, mPaint);

        canvas.translate(0, DY);
        //right表示字符在水平方向上以x为起点向左延伸，在垂直方向上以y为起点向下延伸。
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("right", x, y, mPaint);
    }
}
