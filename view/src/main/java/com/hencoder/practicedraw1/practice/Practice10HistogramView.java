package com.hencoder.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String[] names = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
        int[] values = {0, 1, 1, 10, 20, 25, 10};
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        paint.setTextSize(25);
        int x = 80, y = 400;
        float[] points = {x, y, x, 80, x, y, x + 600, y};
        canvas.drawLines(points, 0, 8, paint);
        int height = 10;
        int width = 60;
        int space = 25;
        int offset = 10;
        int newX;
        for (int i = 0; i < names.length; i++) {
            newX = x + space * (i + 1) + width * i;
            canvas.drawText(names[i], newX + offset*(6-names[i].length())/2, y + 25, paint);
        }

        paint.setColor(Color.GREEN);
        for (int j = 0; j < names.length; j++) {
            newX = x + space * (j + 1) + width * j;
            canvas.drawRect(new Rect(newX, y - values[j] * height, newX + width, y), paint);
        }
        paint.setTextSize(33);
        canvas.drawText("直方图", 310,480,paint);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
    }


}
