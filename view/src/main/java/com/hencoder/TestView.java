package com.hencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepe.view.R;

/**
 * @author wang
 * @date 2017/11/10.
 */

public class TestView extends View {
    private Paint mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int color1 = getResources().getColor(R.color.color_blue);
    private int color2 = getResources().getColor(R.color.color_purple);


    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint1.setColor(color1);
        mPaint1.setTextSize(30);
        mPaint2.setColor(color2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        matrix.postScale(2, 2);
        canvas.save();
        canvas.setMatrix(matrix);
        canvas.concat(matrix);
        canvas.drawRect(100, 100, 200, 200, mPaint1);
        canvas.restore();
        canvas.drawRect(200, 200, 300, 300, mPaint2);
        canvas.drawText("先setMatrix 后concat", 50, 1000, mPaint1);

    }
}
