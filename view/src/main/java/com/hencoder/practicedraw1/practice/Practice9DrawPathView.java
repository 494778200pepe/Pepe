package com.hencoder.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        int startX = 300, startY = 200;
        int radius = 50;
        Path path = new Path();
        path.moveTo(startX, startY);
        path.addCircle(startX, startY, radius, Path.Direction.CCW);
        path.addCircle(startX+radius*2, startY, radius, Path.Direction.CCW);
        int r = (int) (radius / Math.sqrt(2));
        int t = (int) ((radius * 2 + r * 2) / Math.sqrt(2));
        path.moveTo(startX - r, startY + r);
        path.lineTo(startX + radius, startY + t);
        path.lineTo(startX + radius * 2 + r, startY + r);
        path.lineTo(startX + radius, startY);
        path.close();
        canvas.drawPath(path, paint);

//        练习内容：使用 canvas.drawPath() 方法画心形
    }
}
