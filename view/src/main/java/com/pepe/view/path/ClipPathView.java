package com.pepe.view.path;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

public class ClipPathView extends View {

	public ClipPathView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setFocusable(true);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(6);
		mPaint.setTextSize(16);
		mPaint.setTextAlign(Paint.Align.RIGHT);

		mPath = new Path();
	}

	private Paint mPaint;
	private Path mPath;

	private void drawScene(Canvas canvas) {
		canvas.clipRect(0, 0, 300, 300);

		canvas.drawColor(Color.WHITE);

		mPaint.setColor(Color.RED);
		canvas.drawLine(0, 0, 300, 300, mPaint);

		mPaint.setColor(Color.GREEN);
		canvas.drawCircle(90, 210, 90, mPaint);

		mPaint.setColor(Color.BLUE);
		canvas.drawText("Clipping", 300, 90, mPaint);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.GRAY);
		canvas.save();
		canvas.translate(10, 10);
		drawScene(canvas);
		canvas.restore();

		// 用pointList记录不同的path的各处的连接点
		List<Point> pointList = new ArrayList<Point>();

		// 一
		canvas.save();
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(3);
		canvas.translate(10, 410);
		pointList.add(new Point(10, 410));
		mPath.lineTo(150, 300);
		pointList.add(new Point(10 + 150, 410 + 300));
		mPath.lineTo(300, 0);
		pointList.add(new Point(10 + 300, 410));
		canvas.clipPath(mPath); // makes the clip empty
		mPath.addCircle(150, 150, 120, Path.Direction.CCW);
		canvas.clipPath(mPath, Region.Op.REPLACE);
		drawScene(canvas);
		canvas.restore();

		// 猜测1 自动和circle的起点(300)补全？
		// 猜测2 自动和上次addCircle的终点(270,150)补全？
		// 将addCircle的半径改为120测试，发现猜测2正确
		// 二
		canvas.save();
		canvas.translate(410, 410);
		pointList.add(new Point(410, 410));
		// Path没有重置
		pointList.add(new Point(410 + 300, 410));
		pointList.add(new Point(410 + 270, 410 + 150));
		// addCircle(150, 150, 120, Path.Direction.CCW);之后
		// 起始点为(270,150)接着和后两个点组成了第二个三角形
		mPath.lineTo(150, 300);
		pointList.add(new Point(410 + 150, 410 + 300));
		mPath.lineTo(300, 300);
		pointList.add(new Point(410 + 300, 410 + 300));
		canvas.clipPath(mPath); // makes the clip empty
		mPath.addCircle(150, 150, 150, Path.Direction.CCW);
		canvas.clipPath(mPath, Region.Op.REPLACE);
		drawScene(canvas);
		canvas.restore();

		// 三
		canvas.save();
		canvas.translate(10, 810);
		pointList.add(new Point(10, 810));
		mPath = new Path();
		// new Path()的起点是(0,0)
		mPath.lineTo(150, 300);
		pointList.add(new Point(10 + 150, 810 + 300));
		mPath.lineTo(300, 0);
		pointList.add(new Point(10 + 300, 810));
		canvas.clipPath(mPath); // makes the clip empty
		mPath.addCircle(150, 150, 150, Path.Direction.CCW);
		canvas.clipPath(mPath, Region.Op.REPLACE);
		drawScene(canvas);
		canvas.restore();

		// 四
		canvas.save();
		canvas.translate(410, 810);
		pointList.add(new Point(410, 810));
		mPath = new Path();
		// new Path()和reset()的起点是(0,0)
		mPath.lineTo(150, 300);
		pointList.add(new Point(410 + 150, 810 + 300));
		mPath.lineTo(300, 300);
		pointList.add(new Point(410 + 300, 810 + 300));
		canvas.clipPath(mPath); // makes the clip empty
		mPath.addCircle(150, 150, 150, Path.Direction.CCW);
		canvas.clipPath(mPath, Region.Op.REPLACE);
		drawScene(canvas);
		canvas.restore();

		// 五
		canvas.save();
		canvas.translate(10, 1210);
		pointList.add(new Point(10, 1210));
		mPath = new Path();
		mPath.lineTo(300, 300);
		pointList.add(new Point(10 + 300, 1210 + 300));
		canvas.clipPath(mPath); // makes the clip empty
		mPath.addCircle(150, 150, 150, Path.Direction.CCW);
		canvas.clipPath(mPath, Region.Op.REPLACE);
		drawScene(canvas);
		canvas.restore();

		// 六
		canvas.save();
		canvas.translate(410, 1210);
		pointList.add(new Point(410, 1210));
		mPath = new Path();
		mPath.lineTo(0, 300);
		pointList.add(new Point(410, 1210 + 300));
		canvas.clipPath(mPath); // makes the clip empty
		mPath.addCircle(150, 150, 150, Path.Direction.CCW);
		canvas.clipPath(mPath, Region.Op.REPLACE);
		drawScene(canvas);
		canvas.restore();
		// 结论：
		// 一条线没用
		// 两条线自动补全

		// 最后绘制Path的连接点，方便我们大家对比观察
		mPaint.setStrokeWidth(25);// 将点的strokeWidth要设置的比画path时要大
		mPaint.setStrokeCap(Paint.Cap.ROUND);// 将点设置为圆点状
		mPaint.setColor(Color.RED);// 设置圆点为蓝色
		for (Point p : pointList) {
			// 遍历pointList，绘制连接点
			canvas.drawPoint(p.x, p.y, mPaint);
		}
	}

}
