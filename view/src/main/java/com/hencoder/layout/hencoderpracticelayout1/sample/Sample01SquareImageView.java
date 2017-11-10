package com.hencoder.layout.hencoderpracticelayout1.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;


@SuppressLint("AppCompatCustomView")
public class Sample01SquareImageView extends ImageView {
    public Sample01SquareImageView(Context context) {
        super(context);
    }

    public Sample01SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Sample01SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth > measuredHeight) {
            measuredWidth = measuredHeight;
        } else {
            measuredHeight = measuredWidth;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
