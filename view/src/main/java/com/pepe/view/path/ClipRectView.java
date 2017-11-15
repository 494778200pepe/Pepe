package com.pepe.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

public class ClipRectView extends View {

	public ClipRectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setFocusable(true);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(6);
		mPaint.setTextSize(16);
		mPaint.setTextAlign(Paint.Align.RIGHT);

		// 实例化区域对象
		mRegion = new Region(200, 200, 400, 400);

	}

	private Paint mPaint;
	private Region mRegion;

	private void drawScene(Canvas canvas) {
		canvas.clipRect(0, 0, 100, 100);

		canvas.drawColor(Color.WHITE);

		mPaint.setColor(Color.RED);
		canvas.drawLine(0, 0, 100, 100, mPaint);

		mPaint.setColor(Color.GREEN);
		canvas.drawCircle(30, 70, 30, mPaint);

		mPaint.setColor(Color.BLUE);
		canvas.drawText("Clipping", 100, 30, mPaint);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.GRAY);
		canvas.save();
		canvas.translate(10, 10);
		drawScene(canvas);
		canvas.restore();




		canvas.save();
		// 裁剪区域
		canvas.clipRegion(mRegion);
//		canvas.clipRegion(new Region(300,300,500,500),Region.Op.INTERSECT);
		canvas.drawColor(Color.RED);
		canvas.restore();

		// clipRect(Rect rect)
		// clipRect(RectF rect)
		// clipRect(int left, int top, int right, int bottom)
		// clipRect(Rect rect, Region.Op op)
		// clipRect(RectF rect, Region.Op op)
		// clipRect(float left, float top, float right, float bottom)
		// clipRect(float left, float top, float right, float bottom, Region.Op
		// op)

		// clipRegion(Region region)
		// clipRegion(Region region, Region.Op op)
	}

}
