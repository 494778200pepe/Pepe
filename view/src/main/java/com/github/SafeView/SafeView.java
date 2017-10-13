package com.github.SafeView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Android实现SafeView - 安卓源码 安卓巴士 - 安卓开发 - Android开发 - 安卓 - 移动互联网门户
 * http://www.apkbus.com/thread-465141-1-1.html
 * Created by wang on 2017/10/12.
 */

public class SafeView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public SafeView(Context context) {
        super(context);
    }

    public SafeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SafeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
    }
}
