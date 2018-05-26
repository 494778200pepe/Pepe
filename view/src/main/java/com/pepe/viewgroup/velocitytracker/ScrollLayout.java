package com.pepe.viewgroup.velocitytracker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.pepe.Consts;

/**
 * @author wang
 * @date 2017/11/30.
 */

public class ScrollLayout extends ViewGroup {

    private Scroller mScroller;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private VelocityTracker mVelocityTracker;
    private int leftBorder;
    private int rightBorder;

    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(getContext());
        setFocusable(true);
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        setWillNotDraw(false);

        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            int left = 0;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                child.layout(left, 0, left + child.getMeasuredWidth(), child.getMeasuredHeight());
                left += child.getMeasuredWidth();
            }
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getLeft();
    }

    private float mXDown;
    private float mXMove;
    private float mLastMoveX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mLastMoveX = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = mXMove - mLastMoveX;
                mLastMoveX = mXMove;
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && event.getEdgeFlags() != 0) {
            return false;
        }
        obtainVelocityTracker(event);
        final int action = event.getAction();
        final float x = event.getX();
        final float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(Consts.TAG, "ACTION_DOWN#currentScrollX:" + getScrollX()
                        + ", mLastMotionX:" + x);
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mLastMoveX - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + scrolledX > rightBorder) {
                    scrollTo(rightBorder, 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mLastMoveX = mXMove;
                break;
            case MotionEvent.ACTION_UP:
//                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
//                int initialVelocity = (int) mVelocityTracker.getXVelocity();
//                if ((Math.abs(initialVelocity) > mMinimumVelocity)
//                        && getChildCount() > 0) {
//                    fling(-initialVelocity);
//                }
//                releaseVelocityTracker();

                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public void fling(int velocityX) {
        if (getChildCount() > 0) {
            mScroller.fling(getScrollX(), getScrollY(), velocityX, 0, leftBorder, rightBorder, 0,
                    0);
            awakenScrollBars(mScroller.getDuration());
            invalidate();
        }
    }

    private void obtainVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void releaseVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int oldX = scrollX;
            int oldY = scrollY;
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            scrollX = x;
            scrollY = y;
            scrollX = scrollX + 10;
            scrollTo(scrollX, scrollY);
            postInvalidate();
        }
    }
}
