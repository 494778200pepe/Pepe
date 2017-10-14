package com.hencoder.task;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.pepe.view.R;

/**
 * Created by wang on 2017/10/13.
 */

public class FlipboardView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    float mBitmapWidth, mBitmapHeight;

    public FlipboardView(Context context) {
        super(context);
    }

    public FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.flipboard);
        mBitmapWidth = bitmap.getWidth();
        mBitmapHeight = bitmap.getHeight();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#000000"));

        canvas.save();
        Camera camera = new Camera();
        camera.setLocation(0, 0, -25);

        if (mDegree <= 45) {
            Matrix matrix = new Matrix();
            camera.save();
            canvas.translate(mBitmapWidth / 2, mBitmapHeight / 2);
            camera.rotateY(-mRightDegree);
            matrix.reset();
            camera.getMatrix(matrix);
            canvas.concat(matrix);
            camera.restore();

            canvas.translate(-mBitmapWidth / 2, -mBitmapHeight / 2);
            Rect srcRight = new Rect((int) mBitmapWidth / 2 , 0, (int) mBitmapWidth, (int) mBitmapHeight);// 图片的显示部分
            RectF dstRight = new RectF(mBitmapWidth / 2 -1, 0, mBitmapWidth, mBitmapHeight);// 屏幕位置及尺寸，供图片显示的区域
            canvas.drawBitmap(bitmap, srcRight, dstRight, mPaint);
            canvas.restore();


            Rect srcLeft = new Rect(0, 0, (int) mBitmapWidth / 2, (int) mBitmapHeight);// 图片的显示部分
            RectF dstLeft = new RectF(0, 0, (int) mBitmapWidth / 2, mBitmapHeight);// 屏幕位置及尺寸，供图片显示的区域
            canvas.drawBitmap(bitmap, srcLeft, dstLeft, mPaint);

        }


    }

    /**
     * 开始右边的折叠动画
     */
    public void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 45);
        animator.setEvaluator(new IntEvaluator());
        animator.setDuration(4000);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int degree = (int) animation.getAnimatedValue();
                setDegree(degree);
            }
        });
        animator.start();
    }

    int mDegree = 0;
    int mRightDegree = 0;
    int mRotateDegree = 0;

    public void setDegree(int degree) {
        mDegree = degree;
        if (mDegree <= 45) {
            mRightDegree = mDegree;
        } else {
            mRotateDegree = mDegree - 45;
            if (mDegree <= 90) {
                mRightDegree = 90 - mDegree;
            }
        }
        invalidate();
    }


}
