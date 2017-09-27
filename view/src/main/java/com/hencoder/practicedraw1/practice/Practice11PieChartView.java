package com.hencoder.practicedraw1.practice;

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

public class Practice11PieChartView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int viewWidthCenter, viewHeightCenter;
    int radius = 200;
    float startAngle = 180;
    float spaceAngle = 2.5f;
    int maxAngle = Integer.MIN_VALUE;
    float totalValue = 0;

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ArrayList<Data> datas = new ArrayList<>();

    {

        paint.setTextSize(20);

        datas.add(new Data(120, "Lollipop", Color.RED));
        datas.add(new Data(60, "Marshmallow", Color.YELLOW));
        datas.add(new Data(1, "Froyo", Color.DKGRAY));
        datas.add(new Data(6, "Gingerbread", Color.MAGENTA));
        datas.add(new Data(5, "Ice Cream Sandwich", Color.GRAY));
        datas.add(new Data(48, "Jelly Bean", Color.GREEN));
        datas.add(new Data(110, "Kitkat,Color.RED", Color.BLUE));
        for (Data data : datas) {
            maxAngle = Math.max(maxAngle, data.getValue());
            totalValue += data.getValue();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        viewWidthCenter = getWidth() / 2;
        viewHeightCenter = getHeight() / 2;
        float sweetAngle;
        Data data;
        for (int i = 0; i < datas.size(); i++) {
            data = datas.get(i);
            paint.setColor(data.getColor());
            sweetAngle = (data.getValue() / totalValue) * (360 - spaceAngle * datas.size());
            float lineAngle = startAngle + sweetAngle / 2;
            float startLineX = radius * (float) Math.cos(lineAngle / 180 * Math.PI);
            float startLineY = radius * (float) Math.sin(lineAngle / 180 * Math.PI);
            if (data.getValue() == maxAngle) {
                canvas.save();
                float translateX, translateY;
                translateX = startLineX * 0.1f;
                translateY = startLineY * 0.1f;
                canvas.translate(translateX, translateY);
                canvas.drawArc(new RectF(viewWidthCenter - radius, viewHeightCenter - radius, viewWidthCenter + radius, viewHeightCenter + radius), startAngle, sweetAngle, true, paint);
            } else {
                canvas.drawArc(new RectF(viewWidthCenter - radius, viewHeightCenter - radius, viewWidthCenter + radius, viewHeightCenter + radius), startAngle, sweetAngle, true, paint);
            }
            paint.setColor(Color.WHITE);
            canvas.drawLine(viewWidthCenter + startLineX, viewHeightCenter + startLineY, viewWidthCenter + startLineX * 1.1f, viewHeightCenter + startLineY * 1.1f, paint);

            float startX = viewWidthCenter + startLineX * 1.1f;
            float startY = viewHeightCenter + startLineY * 1.1f;
            float stopX, stopY = startY;
            lineAngle = lineAngle % 360;
            if (lineAngle <= 270 && lineAngle >= 90) {
                stopX = startX - 50;
                paint.setTextAlign(Paint.Align.RIGHT);
            } else {
                paint.setTextAlign(Paint.Align.LEFT);
                stopX = startX + 50;
            }
            canvas.drawText(data.getName(), stopX, stopY + 5, paint);
            canvas.drawLine(startX, startY, stopX, stopY, paint);

            if (data.getValue() == maxAngle) {
                canvas.restore();
            }
            startAngle += sweetAngle + spaceAngle;
        }

    }

    private class Data {
        int value;
        String name;
        int color;

        Data(int value, String name, int color) {
            this.value = value;
            this.name = name;
            this.color = color;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public int getColor() {
            return color;
        }
    }
}
