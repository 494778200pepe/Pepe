package com.hencoder.task.Like;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
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
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.pepe.view.R;

/**
 * Created by wang on 2017/10/17.
 */

public class LikeView extends View implements View.OnClickListener {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mOldCount = 5239;
    int mNewCount = 0;
    int mOffsetCount = 1;
    boolean isLike = true;
    Bitmap bitmap1, bitmap2, bitmap3;
    LikeStatus mLikeStatus = LikeStatus.UNLIKE;

    enum LikeStatus {
        UNLIKE, LIKINGOLd, LIKINGNEW, LIKED
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
        paint.setStrokeWidth(1);
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
        } else if (mLikeStatus == LikeStatus.LIKINGOLd) {
            canvas.save();
            canvas.scale(mOldScale, mOldScale, centerX, centerY);
            paint.setAlpha(mOldAlpha);
            canvas.drawBitmap(bitmap1, x, y, paint);
            canvas.restore();

        } else if (mLikeStatus == LikeStatus.LIKINGNEW || mLikeStatus == LikeStatus.LIKED) {
            canvas.save();
            canvas.scale(mNewScale, mNewScale, centerX, centerY);
            paint.setAlpha(mNewAlpha);
            canvas.drawBitmap(bitmap2, x, y, paint);
            canvas.restore();

            canvas.save();
            canvas.scale(mCircleScale, mCircleScale, centerX, centerY);
            paint.setColor(Color.parseColor("#E4583E"));
            paint.setAlpha(mCircleAlpha);
            canvas.drawCircle(centerX, centerY, width2 * 0.9f / 2, paint);
            canvas.restore();

            canvas.save();
            Path path = new Path();
            path.addCircle(centerX, centerY - height3 / 2, mShiningScale * width3, Path.Direction.CCW);
            paint.setAlpha(0xFF);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap3, centerX - width3 / 2, centerY - height1 * 0.9f, paint);
            canvas.restore();
        }

        //绘制文字
        paint.setColor(Color.parseColor("#333333"));
        if (mLikeStatus == LikeStatus.UNLIKE) {
            Rect rect = new Rect();
            String count = String.valueOf(mOldCount);
            paint.getTextBounds(count, 0, count.length(), rect);
            int textWidth = rect.left + rect.right;
            int textHeight = rect.top + rect.bottom;
            canvas.drawText(count, centerX + 25, centerY - textHeight / 2, paint);
        } else if (mLikeStatus == LikeStatus.LIKINGOLd || mLikeStatus == LikeStatus.LIKINGNEW) {
            Log.d("pepe", "mOffsetCount = " + mOffsetCount);
            if (isLike) {
                Rect rect = new Rect();
                String oldCount = String.valueOf(mOldCount);
                String newCount = String.valueOf(mNewCount);
                paint.getTextBounds(oldCount, 0, oldCount.length() - mOffsetCount, rect);
                int textWidth = rect.left + rect.right;
                int textHeight = rect.top + rect.bottom;
                canvas.drawText(oldCount.substring(0, oldCount.length() - mOffsetCount), centerX + 25, centerY - textHeight / 2, paint);

                paint.setAlpha(255 - (int) (mOffset * 255));
                canvas.drawText(oldCount.substring(oldCount.length() - mOffsetCount, oldCount.length()), centerX + 25 + textWidth, centerY - textHeight / 2 + textHeight * 2 * mOffset, paint);
                paint.setAlpha((int) (mOffset * 255));
                canvas.drawText(newCount.substring(newCount.length() - mOffsetCount, newCount.length()), centerX + 25 + textWidth, centerY - textHeight / 2 - textHeight * 2 + textHeight * 2 * mOffset, paint);
            } else {
                Rect rect = new Rect();
                String oldCount = String.valueOf(mOldCount);
                String newCount = String.valueOf(mNewCount);
                paint.getTextBounds(oldCount, 0, oldCount.length() - mOffsetCount, rect);
                int textWidth = rect.left + rect.right;
                int textHeight = rect.top + rect.bottom;
                canvas.drawText(oldCount.substring(0, oldCount.length() - mOffsetCount), centerX + 25, centerY - textHeight / 2, paint);

                paint.setAlpha(255 - (int) (mOffset * 255));
                canvas.drawText(oldCount.substring(oldCount.length() - mOffsetCount, oldCount.length()), centerX + 25 + textWidth, centerY - textHeight / 2 - textHeight * 2 * mOffset, paint);
                paint.setAlpha((int) (mOffset * 255));
                canvas.drawText(newCount.substring(newCount.length() - mOffsetCount, newCount.length()), centerX + 25 + textWidth, centerY - textHeight / 2 + textHeight * 2 - textHeight * 2 * mOffset, paint);
            }
        } else if (mLikeStatus == LikeStatus.LIKED) {
            Rect rect = new Rect();
            String count = String.valueOf(mOldCount);
            paint.getTextBounds(count, 0, count.length(), rect);
            canvas.drawText(count, centerX + 25, centerY - (rect.top + rect.bottom) / 2, paint);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mOffsetCount = 1;
        if (mLikeStatus == LikeStatus.UNLIKE) {
            isLike = true;
            mNewCount = mOldCount + 1;
            int count = mOldCount;
            while (count % 10 == 9) {
                mOffsetCount++;
                count = (count - 9) / 10;
            }
            startUnlikeAnimation();
        } else if (mLikeStatus == LikeStatus.LIKED) {
            isLike = false;
            mNewCount = mOldCount - 1;
            int count = mOldCount;
            while (count % 10 == 0) {
                mOffsetCount++;
                count = count / 10;
            }
            startLikeAnimation2();
        }
    }

    private void startUnlikeAnimation() {
        mLikeStatus = LikeStatus.LIKINGOLd;
        //灰色图片缩小并减小透明度
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "oldScale", 1f, 0.8f);
        animator1.setDuration(100);
        animator1.setStartDelay(100);
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startLikeAnimation();
            }
        });
        ObjectAnimator animator2 = ObjectAnimator.ofInt(this, "oldAlpha", 255, 200);
        animator2.setDuration(100);

        //文字偏移
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "offset", 0, 1);
        animator3.setDuration(800);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2, animator3);
        animatorSet.start();
    }

    private void startLikeAnimation() {
        mLikeStatus = LikeStatus.LIKINGNEW;
        //黄色图片增加透明度并放大
        ObjectAnimator animator1 = ObjectAnimator.ofInt(this, "newAlpha", 200, 255);
        animator1.setDuration(100);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "newScale", 0.8f, 1.2f, 1f);
        animator2.setDuration(700);
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(animator1, animator2);
        animatorSet1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLikeStatus = LikeStatus.LIKED;
                mOldCount = mNewCount;
            }
        });

        //圆圈透明度增加并放大
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "circleScale", 0.8f, 1.6f);
        animator3.setDuration(700);
        ObjectAnimator animator4 = ObjectAnimator.ofInt(this, "circleAlpha", 70, 120, 40, 0);
        animator4.setDuration(700);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(animator3, animator4);

        //爆炸效果显示区域放大
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(this, "shiningScale", 0, 1f);
        animator5.setDuration(700);
        animator5.setInterpolator(new AccelerateInterpolator());

        animatorSet1.playTogether(animatorSet2);
        animatorSet1.playTogether(animator5);
        animatorSet1.start();
    }

    private void startLikeAnimation2() {
        mLikeStatus = LikeStatus.LIKINGNEW;
        //黄色图片增加透明度并放大
        ObjectAnimator animator1 = ObjectAnimator.ofInt(this, "newAlpha", 255, 200);
        animator1.setDuration(300);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "newScale", 1f, 0.8f);
        animator2.setDuration(300);
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startUnlikeAnimation2();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mShiningScale = 0;
            }
        });
        //文字偏移
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "offset", 0, 1);
        animator3.setDuration(700);

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(animator1, animator2, animator3);
        animatorSet1.start();
    }

    private void startUnlikeAnimation2() {
        mLikeStatus = LikeStatus.LIKINGOLd;
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "oldScale", 0.8f, 1f);
        animator1.setDuration(300);
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLikeStatus = LikeStatus.UNLIKE;
                mOldCount = mNewCount;
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofInt(this, "oldAlpha", 200, 255);
        animator2.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);
        animatorSet.start();
    }


    private float mOldScale = 1f;

    private void setOldScale(float oldScale) {
        mOldScale = oldScale;
        invalidate();
    }

    private int mOldAlpha = 255;

    private void setOldAlpha(int oldAlpha) {
        mOldAlpha = oldAlpha;
        invalidate();
    }

    private float mNewScale = 0.8f;

    private void setNewScale(float newScale) {
        mNewScale = newScale;
        invalidate();
    }

    private int mNewAlpha = 200;

    private void setNewAlpha(int newAlpha) {
        mNewAlpha = newAlpha;
        invalidate();
    }

    private float mCircleScale = 0.8f;

    private void setCircleScale(float circleScale) {
        mCircleScale = circleScale;
    }

    private int mCircleAlpha = 0;

    private void setCircleAlpha(int circleAlpha) {
        mCircleAlpha = circleAlpha;
        invalidate();
    }

    private float mShiningScale = 0;

    private void setShiningScale(float shiningScale) {
        mShiningScale = shiningScale;
        invalidate();
    }

    private float mOffset = 0;

    private void setOffset(float offset) {
        mOffset = offset;
    }
}
