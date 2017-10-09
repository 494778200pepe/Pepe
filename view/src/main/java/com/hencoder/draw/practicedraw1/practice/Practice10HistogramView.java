package com.hencoder.draw.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Jucongyuan/PracticeDraw1: 《HenCoder Android 开发进阶：UI 1-1 绘制基础》 的练习项目
 * https://github.com/Jucongyuan/PracticeDraw1
 */

public class Practice10HistogramView extends View {

    Paint rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int viewWidth, viewHeight;
    private float startX, startY;
    private int max;

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ArrayList<Data> datas = new ArrayList<>();

    {
        rectPaint.setColor(Color.GREEN);
        rectPaint.setStrokeWidth(2);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(25);
        textPaint.setTextAlign(Paint.Align.CENTER);


        datas.add(new Data(0, "Froyo"));
        datas.add(new Data(1, "GB"));
        datas.add(new Data(1, "ICS"));
        datas.add(new Data(10, "JB"));
        datas.add(new Data(20, "KitKat"));
        datas.add(new Data(25, "L"));
        datas.add(new Data(10, "M"));
        max = Integer.MIN_VALUE;
        for (Data data : datas) {
            max = Math.max(max, data.getValue());
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        viewWidth = getWidth();
        viewHeight = getHeight();
        startX = (int) (viewWidth * 0.1);
        startY = (int) (viewHeight * 0.7);

        //画坐标
        float width = viewWidth * 0.8f / datas.size() * 0.8f;
        float space = viewWidth * 0.8f / datas.size() * 0.2f;

        float[] points = {startX, startY, startX, viewHeight * 0.1f, startX, startY, viewWidth * 0.9f + space, startY};
        canvas.drawLines(points, 0, 8, rectPaint);
        float height = viewHeight * 0.6f / max;

        for (int i = 0; i < datas.size(); i++) {
            startX = startX + space;
            canvas.drawText(datas.get(i).getName(), startX + width / 2, startY + 25, textPaint);
            canvas.drawRect(new RectF(startX, startY - datas.get(i).getValue() * height, startX + width, startY), rectPaint);
            startX = startX + width;
        }
        textPaint.setTextSize(33);
        canvas.drawText("直方图", viewWidth / 2, viewHeight * 0.9f, textPaint);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        //测量文字的宽度方法一
//        rectPaint.measureText("1d2sfs");
        //测量文字的宽度方法二
//        Rect mBound = new Rect();
//        rectPaint.getTextBounds(text, 0, text.length(), mBound);
//        //字体的中间点和柱状图的中间点一直
//        canvas.drawText(text, offset + viewWidth * i - mBound.viewWidth() / 2, textY, rectPaint);
    }

    private class Data {
        int value;
        String name;

        Data(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
