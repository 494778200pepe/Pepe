package com.hencoder.draw.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wang on 2017/9/27.
 */

public class Practice12RoundView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice12RoundView(Context context) {
        super(context);
    }

    public Practice12RoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float viewWidthCenter = getWidth() / 2;
        float viewHeightCenter = getHeight() / 2;
        float radius = 130;
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(30);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(viewWidthCenter, viewHeightCenter, radius, paint);
        RectF rectF = new RectF(viewWidthCenter - radius, viewHeightCenter - radius, viewWidthCenter + radius, viewHeightCenter + radius);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 0, 135, false, paint);

        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF, -45, 45, false, paint);

        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, -80, 30, false, paint);

        paint.setColor(Color.RED);
        canvas.drawArc(rectF, -90, 10, false, paint);

    }
}