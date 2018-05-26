package com.pepe.viewgroup.slidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Android 自定义控件打造史上最简单的侧滑菜单 - CSDN博客
 * http://blog.csdn.net/lmj623565791/article/details/39185641
 * Android 高仿 QQ5.0 侧滑菜单效果 自定义控件来袭 - CSDN博客
 * http://blog.csdn.net/lmj623565791/article/details/39257409
 * 抽屉式菜单（一）-慕课网
 * https://www.imooc.com/video/4385
 * @author wang
 * @date 2017/11/28.
 */

public class SlidingMenu extends HorizontalScrollView {
    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    //dp
    private int mMenuRightPadding = 50;
    private int mScreenWidth;
    private int mMenuWidth;

    private boolean isFirst;
    private boolean isOpen;

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, context.getResources().getDisplayMetrics());
        mMenuWidth = mScreenWidth - mMenuRightPadding;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isFirst) {
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mMenuWidth, 0);
            isFirst = true;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX >= mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void open() {
        if (!isOpen) {
            this.smoothScrollTo(0, 0);
            isOpen = true;
        }
    }

    private void close() {
        if (isOpen) {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }

    public void toggle() {
        if (isOpen) {
            close();
        } else {
            open();
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //1 - 0
        float scale = l * 1f / mMenuWidth;
        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale);
        ViewHelper.setAlpha(mMenu, 1 - 0.4f * scale);
        ViewHelper.setScaleX(mMenu, 1 - 0.4f * scale);
        ViewHelper.setScaleY(mMenu, 1 - 0.4f * scale);


        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, 0.8f + 0.2f * scale);
        ViewHelper.setScaleY(mContent, 0.8f + 0.2f * scale);
    }
}
