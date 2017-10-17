package com.hencoder.task.Like;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;

import com.pepe.view.R;

/**
 * Created by wang on 2017/10/17.
 */

public class LikeView extends View implements View.OnClickListener {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mCount = 2304;
    Bitmap bitmap1, bitmap2, bitmap3;
    LikeStatus mLikeStatus = LikeStatus.UNLIKE;

    enum LikeStatus {
        UNLIKE, LIKING, LIKED
    }

    public LikeView(Context context) {
        super(context);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(18);
        paint.setStrokeWidth(0.5f);
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width1 = bitmap1.getWidth();
        int height1 = bitmap1.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        int width3 = bitmap3.getWidth();
        int height3 = bitmap3.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - width1 / 2;
        int y = centerY - height1 / 2;

        if (mLikeStatus == LikeStatus.UNLIKE) {
            canvas.drawBitmap(bitmap1, x, y, paint);
        } else if (mLikeStatus == LikeStatus.LIKING) {
            canvas.save();
            canvas.scale(mScale, mScale, centerX, centerY);
            canvas.drawBitmap(bitmap2, x, y, paint);
            canvas.restore();
            paint.setColor(Color.parseColor("#E4583E"));
            canvas.drawCircle(centerX, centerY, mScale * width2 *1.1f/ 2, paint);
            if (mScale > 1) {
                paint.setAlpha(0xFF);
                canvas.save();
                Path path = new Path();
                path.addCircle(centerX - width3 / 2 + width3 / 2, centerY - height1 + height3 / 2, (mScale - 1f) * width3 * 5 / 2, Path.Direction.CCW);
                canvas.clipPath(path);
                canvas.drawBitmap(bitmap3, centerX - width3 / 2, centerY - height1, paint);
                canvas.restore();
            }

        } else if (mLikeStatus == LikeStatus.LIKED) {
            canvas.drawBitmap(bitmap2, x, y, paint);
            canvas.drawBitmap(bitmap3, centerX - width3 / 2, centerY - height1, paint);
        }
        paint.setColor(Color.GRAY);
        paint.setAlpha(0xFF);
        Rect rect = new Rect();
        String count = String.valueOf(mCount);
        paint.getTextBounds(count, 0, count.length(), rect);
        canvas.drawText(count, centerX + 18, centerY - (rect.top + rect.bottom) / 2, paint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startAnimation();
    }

    private void setText(int count) {
        mCount = count;
    }

    private void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "scale", 0.8f, 1.2f);
        animator.setDuration(700);
        animator.setStartDelay(100);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLikeStatus = LikeStatus.LIKED;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mLikeStatus = LikeStatus.LIKING;
            }
        });
        animator.start();

    }

    private float mScale;

    private void setScale(float scale) {
        mScale = scale;
        invalidate();
    }
}
