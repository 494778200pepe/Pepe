package com.pepe.widget.CircleDrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepe.widget.R;

/**
 * Created by wang on 2017/5/15.
 */

public class CircleDrawable extends View {

    private float mRadius;
    private Bitmap mBitmap;

    public CircleDrawable(Context context) {
        super(context);
    }

    public CircleDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleDrawable);
        int typeCount = typedArray.getIndexCount();
        for (int i = 0; i < typeCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CircleDrawable_radius:
                    mRadius = typedArray.getFloat(attr, 0.5f);
                    break;
                case R.styleable.CircleDrawable_src:
                    mBitmap = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, R.mipmap.ic_launcher));
                    break;
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        Bitmap target = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(target);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas1.drawRoundRect(new RectF(0, 0, width, height), mRadius * width, mRadius * height, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas1.drawBitmap(mBitmap, 0, 0, paint);

        canvas.drawBitmap(target, 0, 0, null);

    }
}
