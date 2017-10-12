package com.github.InfiniteCards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by wang on 2017/10/12.
 */

public class InfiniteCardsView extends android.view.View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    ArrayList<Bitmap> mBitmapList = new ArrayList<>();

    public InfiniteCardsView(Context context) {
        super(context);
        initBitmaps();
    }

    public InfiniteCardsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmaps();
    }

    public InfiniteCardsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmaps();
    }
    public void initBitmaps(){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
