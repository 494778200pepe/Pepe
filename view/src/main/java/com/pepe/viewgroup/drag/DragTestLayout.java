package com.pepe.viewgroup.drag;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author wang
 * @date 2017/11/23.
 */

public class DragTestLayout extends ViewGroup implements View.OnClickListener {
    ViewDragHelper mDragHelper;
    View mContentView;
    View mMenuView;
    View mShowButton;

    int mMenuTop = 0;
    boolean mIsShowMenu = false;

    public DragTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, 1, new ViewDragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mMenuView = getChildAt(1);
        mShowButton = getChildAt(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            mContentView.layout(0, 0, getMeasuredWidth(), mContentView.getMeasuredHeight());
            mMenuView.layout(0, -mMenuView.getMeasuredHeight() + mMenuTop, mMenuView.getMeasuredWidth(), 0 + mMenuTop);
            int centerX = getMeasuredWidth()/2;
            int centerY = (getMeasuredHeight() - mContentView.getMeasuredHeight())/2 +mContentView.getMeasuredHeight();
            mShowButton.layout(centerX - mShowButton.getMeasuredWidth()/2,centerY - mShowButton.getMeasuredHeight()/2,centerX + mShowButton.getMeasuredWidth()/2,centerY + mShowButton.getMeasuredHeight()/2);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mShowButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mIsShowMenu) {
            hideMenu();
        } else {
            showMenu();
        }
    }

    public boolean getIsShowMenu() {
        return mIsShowMenu;
    }

    public void showMenu() {
        if (!mIsShowMenu) {
            mDragHelper.smoothSlideViewTo(mMenuView, 0, 0);
            invalidate();
        }
    }

    public void hideMenu() {
        if (mIsShowMenu) {
            mDragHelper.smoothSlideViewTo(mMenuView, 0, -mMenuView.getHeight());
            invalidate();
        }
    }

    private class ViewDragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mMenuView;
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mDragHelper.captureChildView(mMenuView, pointerId);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == mMenuView) {
                if (mMenuView.getTop() < -mMenuView.getHeight() / 2) {
                    mDragHelper.settleCapturedViewAt(0, -mMenuView.getHeight());
                } else {
                    mDragHelper.settleCapturedViewAt(0, 0);
                }
                invalidate();
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mMenuView.setVisibility((mMenuView.getHeight() + top) == 0 ? View.GONE : View.VISIBLE);
            mMenuTop = top;
            requestLayout();
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            if (state == ViewDragHelper.STATE_IDLE) {
                mIsShowMenu = (mMenuView.getTop() == 0);
            }
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return 0;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return Math.min(Math.max(-mMenuView.getHeight(), top), 0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
