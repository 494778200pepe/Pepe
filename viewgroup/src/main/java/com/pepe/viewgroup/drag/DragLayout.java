package com.pepe.viewgroup.drag;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wang on 2017/7/12.
 */

public class DragLayout extends LinearLayout {
    private ViewDragHelper mDragHelper;
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;
    private Point mAutoBackOriginPos = new Point();

    public DragLayout(Context context) {
        super(context);
    }

    public DragLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //第一个参数为this，表示该类生成的对象，他是ViewDragHelper的拖动处理对象，必须为ViewGroup。
        //1.0f是敏感度参数参数越大越敏感
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public DragLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
        mDragView.setClickable(true);
    }

    /**
     * 由父视图调用用来请求子视图根据偏移值 mScrollX,mScrollY重新绘制
     * 自定义ViewGroup必须实现方法体
     */
    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        // 设置哪些view可以捕捉drag事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragView || child == mAutoBackView;
        }

        // 确定x轴的可拖拽范围
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - mDragView.getWidth();
            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        // 确定y轴的可拖拽范围
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottonBound = getHeight() - mDragView.getHeight();
            final int newTop = Math.min(Math.max(top, topBound), bottonBound);
            return newTop;
        }

        //手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // mAutoBackView手指释放时可以自动回去
            if (releasedChild == mAutoBackView) {
                mDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                //因为其内部使用的是mScroller.startScroll，所以别忘了需要invalidate()以及结合computeScroll方法一起。
                invalidate();
            }
        }

        // 在边界拖动时回调
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId)
        {
            // 在onEdgeDragStarted回调方法中，主动通过captureChildView对其进行捕获，该方法可以绕过tryCaptureView，
            // 所以我们的tryCaptureView虽然并为返回true，但却不影响。
            // 注意如果需要使用边界检测需要添加上mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);。
            mDragHelper.captureChildView(mEdgeTrackerView, pointerId);
        }

        @Override
        public int getViewHorizontalDragRange(View child)
        {
            return getMeasuredWidth()-child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child)
        {
            return getMeasuredHeight()-child.getMeasuredHeight();
        }

        public DragHelperCallback() {
            super();
        }

        //当ViewDragHelper状态发生变化时回调（IDLE,DRAGGING,SETTING[自动滚动时]）
        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        //当captureview的位置发生改变时回调
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        //当captureview被捕获时回调
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        //当触摸到边界时回调。
        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        //true的时候会锁住当前的边界，false则unLock。
        @Override
        public boolean onEdgeLock(int edgeFlags) {
            return super.onEdgeLock(edgeFlags);
        }

        //改变同一个坐标（x,y）去寻找captureView位置的方法。（具体在：findTopChildUnder方法中）
        @Override
        public int getOrderedChildIndex(int index) {
            return super.getOrderedChildIndex(index);
        }
        // 方法的大致的回调顺序：
//        shouldInterceptTouchEvent：
//
//        DOWN:
//        getOrderedChildIndex(findTopChildUnder)
//          ->onEdgeTouched
//
//        MOVE:
//        getOrderedChildIndex(findTopChildUnder)
//          ->getViewHorizontalDragRange &
//        getViewVerticalDragRange(checkTouchSlop)(MOVE中可能不止一次)
//          ->clampViewPositionHorizontal&
//        clampViewPositionVertical
//          ->onEdgeDragStarted
//          ->tryCaptureView
//          ->onViewCaptured
//          ->onViewDragStateChanged
//
//        processTouchEvent:
//
//        DOWN:
//        getOrderedChildIndex(findTopChildUnder)
//          ->tryCaptureView
//          ->onViewCaptured
//          ->onViewDragStateChanged
//          ->onEdgeTouched
//        MOVE:
//                ->STATE==DRAGGING:dragTo
//          ->STATE!=DRAGGING:
//        onEdgeDragStarted
//        ->getOrderedChildIndex(findTopChildUnder)
//        ->getViewHorizontalDragRange&
//        getViewVerticalDragRange(checkTouchSlop)
//        ->tryCaptureView
//        ->onViewCaptured
//        ->onViewDragStateChanged
    }
}
