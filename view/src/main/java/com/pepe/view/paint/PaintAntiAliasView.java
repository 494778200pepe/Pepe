package com.pepe.view.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

//消除锯齿
public class PaintAntiAliasView extends View {

	private Paint mPaint;

	public PaintAntiAliasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		 super.onDraw(canvas);
		mPaint.setAntiAlias(true); // 消除锯齿
		// mPaint.setAntiAlias(false); // 不消除锯齿
		RectF oval1 = new RectF(100, 100,
				400, 400); // 用于定义的圆弧的形状和大小的界限
		canvas.drawArc(oval1, -90, 270, false, mPaint); // 根据进度画圆弧，这个是第一层圆弧

		canvas.translate(0,getHeight()/2);

		mPaint.setAntiAlias(false); // 不消除锯齿
		RectF oval2 = new RectF(100, 100,
				400, 400); // 用于定义的圆弧的形状和大小的界限
		canvas.drawArc(oval2, -90, 270, false, mPaint); // 根据进度画圆弧，这个是第一层圆弧
	}
}
