package com.hencoder.task.Flipboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.pepe.view.R;

/**
 * sunnyxibei/HenCoderPractice: HenCoder「仿写酷界面」活动 - Flipboard 红板报的翻页效果
 * https://github.com/sunnyxibei/HenCoderPractice
 * keyboard3/HencoderKeyboard3: 即刻点赞、薄荷健康尺、小米运动、Fliboard 翻页效果
 * https://github.com/keyboard3/HencoderKeyboard3
 * Created by wang on 2017/10/13.
 */

public class FlipboardView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera;
    int mBitmapWidth, mBitmapHeight;

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

        camera = new Camera();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        camera.setLocation(0, 0, newZ);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#359FF2"));
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - mBitmapWidth / 2;
        int y = centerY - mBitmapHeight / 2;

        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-mRotateDegree);
        camera.rotateY(mRightDegree);
        canvas.clipRect(0, -centerY, centerX, centerY);
        camera.applyToCanvas(canvas);
        canvas.rotate(mRotateDegree);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, mPaint);
        canvas.restore();

        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-mRotateDegree);
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        camera.rotateY(mTopDegree);
        camera.applyToCanvas(canvas);
        canvas.rotate(mRotateDegree);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, mPaint);
        canvas.restore();

    }


    /**
     * 开始右边的折叠动画
     */
    public void startAnimation() {
        ObjectAnimator animator1 = ObjectAnimator.ofInt(this, "rightDegree", 0, -45);
        animator1.setDuration(1000);
        animator1.setStartDelay(500);
        ObjectAnimator animator2 = ObjectAnimator.ofInt(this, "rotateDegree", 0, 270);
        animator2.setDuration(800);
        animator2.setStartDelay(500);
        ObjectAnimator animator3 = ObjectAnimator.ofInt(this, "topDegree", 0, 30);
        animator3.setDuration(800);
        animator3.setStartDelay(500);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2, animator3);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                reset();
                animatorSet.start();
            }
        });
        animatorSet.start();
    }

    int mRightDegree = 0;
    int mRotateDegree = 0;
    int mTopDegree = 0;

    public void setRightDegree(int rightDegree) {
        mRightDegree = rightDegree;
        invalidate();
    }

    public void setRotateDegree(int rotateDegree) {
        mRotateDegree = rotateDegree;
        invalidate();
    }

    public void setTopDegree(int topDegree) {
        mTopDegree = topDegree;
        invalidate();
    }

    private void reset() {
        mRightDegree = 0;
        mRotateDegree = 0;
        mTopDegree = 0;
    }

    /**
     * tan30 = 0.5773502691896257 = (1/1.732)
     * tan60 = 1.7320508075688767
     * tan90 = 1.633123935319537E16
     * tan120 = -1.7320508075688783
     * tan150 = -0.5773502691896257
     * tan180 = -1.2246467991473532E-16
     * Sin30 = 0.49999999999999994 = 0.5
     * Sin60 = 0.8660254037844386 = 1.732/2
     * Sin90 = 1.0 =
     * Sin120 = 0.8660254037844387
     * Sin150 = 0.49999999999999994
     * Sin180 = 1.2246467991473532E-16
     */
    public void test() {
        Log.d("pepe", "tan30 = " + Tan(30));
        Log.d("pepe", "tan60 = " + Tan(60));
        Log.d("pepe", "tan90 = " + Tan(90));
        Log.d("pepe", "tan120 = " + Tan(120));
        Log.d("pepe", "tan150 = " + Tan(150));
        Log.d("pepe", "tan180 = " + Tan(180));

        Log.d("pepe", "Sin30 = " + Sin(30));
        Log.d("pepe", "Sin60 = " + Sin(60));
        Log.d("pepe", "Sin90 = " + Sin(90));
        Log.d("pepe", "Sin120 = " + Sin(120));
        Log.d("pepe", "Sin150 = " + Sin(150));
        Log.d("pepe", "Sin180 = " + Sin(180));
    }

    public double Sin(int i) {
        double result = 0;
        //在这里我是写sin函数，其实可以用cos，tan等函数的，不信大家尽管试试
        //result = Math.cos(i * Math.PI / 180);
        result = Math.sin(i * Math.PI / 180);
        //result = Math.tan(i * Math.PI / 180);
        return result;
    }

    public float Tan(int i) {
        float result = 0;
        //在这里我是写sin函数，其实可以用cos，tan等函数的，不信大家尽管试试
        //result = Math.cos(i * Math.PI / 180);
        result = (float) Math.tan(i * Math.PI / 180);
        //result = Math.tan(i * Math.PI / 180);
        return result;
    }


}
