package com.github.Card;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepe.view.R;

/**
 * SpikeKing/wcl-flip-anim-demo: 卡片的翻页动画
 * https://github.com/SpikeKing/wcl-flip-anim-demo
 * 实现翻转卡片的动画效果 - 简书
 * http://www.jianshu.com/p/7db8425e84fc
 * Created by wang on 2017/10/12.
 * @author wang
 */
public class CardView extends View implements View.OnClickListener {
    /**
     * 画笔
     */
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 相机
     */
    Camera camera = new Camera();
    /**
     * 测量文字宽高的矩形
     */
    Rect rect = new Rect();
    /**
     * 卡片前面颜色
     */
    int mFrontColor = Color.RED;
    /**
     * 卡片背面颜色
     */
    int mBackColor = Color.DKGRAY;
    /**
     * 是否正在执行动画
     */
    boolean mIsAnimating = false;
    /**
     * 当前旋转的角度
     */
    int mRotateDegree = -1;
    /**
     * 卡片背面显示的文字
     */
    String mBackText = "反面";
    /**
     * 卡片前面显示的文字
     */
    String mFrontText = "正面";
    /**
     * 文字颜色
     */
    int mTextColor = Color.BLACK;
    /**
     * 文字大小
     */
    float mTextSize = 40;
    /**
     * 当前显示的文字
     */
    String mText;

    public void setRotateDegree(int rotateDegree) {
        mRotateDegree = rotateDegree;
        invalidate();
    }

    public int getmRotateDegree() {
        return mRotateDegree;
    }

    enum Orientation {
        //当前朝向是正面还是反面
        Front, Back
    }

    Orientation mOrientation = Orientation.Front;
    float mWidth, mHeight;


    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CardView);
//      TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
//              R.styleable.card_view,0,0);
        int attrCount = typedArray.getIndexCount();
        for (int i = 0; i < attrCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CardView_frontColor:
                    mFrontColor = typedArray.getColor(R.styleable.CardView_frontColor, Color.YELLOW);
                    break;
                case R.styleable.CardView_backColor:
                    mBackColor = typedArray.getColor(R.styleable.CardView_backColor, Color.DKGRAY);
                    break;
                case R.styleable.CardView_frontText:
                    mFrontText = typedArray.getString(R.styleable.CardView_frontText);
                    break;
                case R.styleable.CardView_backText:
                    mBackText = typedArray.getString(R.styleable.CardView_backText);
                    break;
                case R.styleable.CardView_textColor:
                    mTextColor = typedArray.getColor(R.styleable.CardView_textColor, Color.BLACK);
                    break;
                case R.styleable.CardView_textSize:
                    mTextSize = typedArray.getDimension(R.styleable.CardView_textSize, 30);
                    break;
                default:
                    break;
            }
        }

        typedArray.recycle();
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


        camera.save();
        canvas.save();
        camera.setLocation(0, 0, -25);
        canvas.translate(mWidth / 2, mHeight / 2);
        if (mRotateDegree < 90) {
            camera.rotateY(mRotateDegree);
        } else {
            camera.rotateY(mRotateDegree - 180);
        }
        camera.applyToCanvas(canvas);
        canvas.translate(-mWidth / 2, -mHeight / 2);
        camera.restore();

        if (mOrientation == Orientation.Front) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLACK);
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);

            mPaint.setStyle(Paint.Style.FILL);

            if (mRotateDegree <= 90 && mRotateDegree >= 0) {
                mText = mBackText;
                mPaint.setColor(mBackColor);
            } else {
                mText = mFrontText;
                mPaint.setColor(mFrontColor);
            }
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLACK);
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);

            mPaint.setStyle(Paint.Style.FILL);
            if (mRotateDegree <= 90) {
                mText = mFrontText;
                mPaint.setColor(mFrontColor);
            } else {
                mText = mBackText;
                mPaint.setColor(mBackColor);
            }
            canvas.drawRoundRect(new RectF(0.1f * mWidth, 0.1f * mHeight, 0.9f * mWidth, 0.9f * mHeight), 0.05f * mWidth, 0.05f * mWidth, mPaint);
        }
        mPaint.setColor(mTextColor);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        canvas.drawText(mText, mWidth / 2, mHeight / 2 + (rect.top + rect.bottom) / 2, mPaint);
        canvas.restore();

    }

    @Override
    public void onClick(View v) {
        if (mIsAnimating) {
            return;
        }
        if (mOrientation == Orientation.Front) {
            mOrientation = Orientation.Back;
        } else {
            mOrientation = Orientation.Front;
        }
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "rotateDegree", 0, 180);
        animator.setDuration(1000);
        animator.setEvaluator(new IntEvaluator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int degree = (int) animation.getAnimatedValue();
                setRotateDegree(degree);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimating = false;
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
