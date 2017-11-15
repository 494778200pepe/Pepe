package com.pepe.view.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepe.view.R;

/**
 * @author wang
 * @date 2017/11/13.
 */

public class InvertedView extends View {
    Bitmap originalImage;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint defaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Bitmap reflectionImage;
    Bitmap bitmapWithReflection;

    int width, height;
    Matrix matrix = new Matrix();

    final int reflectionGap = 4;

    public InvertedView(Context context) {
        super(context);
    }

    public InvertedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InvertedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        originalImage = BitmapFactory.decodeResource(getResources(), R.drawable.batman);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = originalImage.getWidth();
        height = originalImage.getHeight();
        matrix.preScale(1, -1);
        reflectionImage = Bitmap.createBitmap(originalImage, 0,
                height / 2, width, height / 2, matrix, false);
        bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Bitmap.Config.ARGB_8888);
//        canvas.setBitmap(bitmapWithReflection);
        canvas.drawBitmap(originalImage, 0, 0, paint);
        canvas.drawRect(0, height, width, height + reflectionGap, paint);
        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, paint);

        LinearGradient shader = new LinearGradient(0,
                originalImage.getHeight(), 0, height + height / 2
                + reflectionGap, 0x70ffffff, 0x00ffffff,
                Shader.TileMode.MIRROR);
        defaultPaint.setShader(shader);
        defaultPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, height + reflectionGap, width, height + height / 2
                + reflectionGap, defaultPaint);
    }
}
