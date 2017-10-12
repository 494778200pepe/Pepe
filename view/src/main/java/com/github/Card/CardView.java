package com.github.Card;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * SpikeKing/wcl-flip-anim-demo: 卡片的翻页动画
 * https://github.com/SpikeKing/wcl-flip-anim-demo
 * 实现翻转卡片的动画效果 - 简书
 * http://www.jianshu.com/p/7db8425e84fc
 * Created by wang on 2017/10/12.
 */
public class CardView extends View implements View.OnClickListener {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mFrontColor = Color.RED;
    int mBackColor = Color.DKGRAY;
    boolean mIsAniming = false;
    int mDegree = -1;
    String mText;

    public void setDegree(int degree) {
        mDegree = degree;
        invalidate();
    }

    public int getmDegree() {
        return mDegree;
    }

    enum Orientation {
        Front, Back
    }

    Orientation mOrientation = Orientation.Front;
    float mWidth, mHeight;


    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(8);
        mWidth = getWidth();
        mHeight = getHeight();

        Camera camera = new Camera();
        camera.save();
        canvas.save();
        camera.setLocation(0, 0, -25);
        canvas.translate(mWidth / 2, mHeight / 2);
        if (mDegree < 90) {
            camera.rotateY(mDegree);
        } else {
            camera.rotateY(mDegree - 180);
        }
        camera.applyToCanvas(canvas);
        canvas.translate(-mWidth / 2, -mHeight / 2);
        camera.restore();

        if (mOrientation == Orientation.Front) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLACK);
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);

            mPaint.setStyle(Paint.Style.FILL);

            if (mDegree <= 90 && mDegree >= 0) {
                mText = "反面";
                mPaint.setColor(mBackColor);
            } else {
                mText = "正面";
                mPaint.setColor(mFrontColor);
            }
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLACK);
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);

            mPaint.setStyle(Paint.Style.FILL);
            if (mDegree <= 90) {
                mText = "正面";
                mPaint.setColor(mFrontColor);
            } else {
                mText = "反面";
                mPaint.setColor(mBackColor);
            }
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);
        }
        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(40);
        Rect rect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        canvas.drawText(mText, mWidth / 2, mHeight / 2 + (rect.top + rect.bottom) / 2, mPaint);
        canvas.restore();

    }

    @Override
    public void onClick(View v) {
        if (mIsAniming) {
            return;
        }
        if (mOrientation == Orientation.Front) {
            mOrientation = Orientation.Back;
        } else {
            mOrientation = Orientation.Front;
        }
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 180);
        animator.setDuration(1000);
        animator.setEvaluator(new IntEvaluator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int degree = (int) animation.getAnimatedValue();
                setDegree(degree);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAniming = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAniming = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
