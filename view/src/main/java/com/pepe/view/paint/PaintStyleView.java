package com.pepe.view.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

//设置空心
public class PaintStyleView extends View {

	private Paint mPaint;

	public PaintStyleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);
		mPaint.setAntiAlias(true); // 消除锯齿
		mPaint.setStrokeWidth(20); // 设置圆环的宽度
//		mPaint.setStyle(Paint.Style.STROKE);
//		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

		RectF oval = new RectF(getWidth() / 4, getWidth() / 4,
				getWidth() * 3 / 4, getWidth() * 3 / 4); // 用于定义的圆弧的形状和大小的界限

		canvas.drawArc(oval, -90, 180, false, mPaint); // 根据进度画圆弧，这个是第一层圆弧
	}
}
