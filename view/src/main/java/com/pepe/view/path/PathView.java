package com.pepe.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wang
 * @date 2017/11/14.
 */
public class PathView extends View {

    private Paint mPaint;
    private Path mPath;
    private float density = getResources().getDisplayMetrics().density;

    private int drawMode = 0;
    final String DRAW_STR = "天行健，君子以自强不息";
    // 声明路径对象数组
    Path[] paths = new Path[8];

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2 * density);
        setDrawMode(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (this.drawMode == 7) {
            paths[0] = new Path();
            paths[0].moveTo(0, 0);
            for (int i = 0; i <= 7; i++) {
                // 生成7个点，随机生成Y坐标，并连成一条
                paths[0].lineTo(i * 30, (float) Math.random() * 30);
            }
            paths[1] = new Path();
            RectF rectF = new RectF(0, 0, 120, 120);
            paths[1].addOval(rectF, Path.Direction.CCW);
            paths[2] = new Path();
            paths[2].addOval(rectF, Path.Direction.CW);
            paths[3] = new Path();
            paths[3].addOval(rectF, Path.Direction.CCW);
            paths[4] = new Path();
            paths[4].addOval(rectF, Path.Direction.CW);
            paths[5] = new Path();
            paths[5].addArc(rectF, 0, 270);
            paths[6] = new Path();
            paths[6].addArc(rectF, 0, 270);

            Matrix matrix = new Matrix();
            matrix.setTranslate(120, 0);
            paths[7] = new Path();
            paths[7].addPath(paths[2], matrix);

            // 初始化画笔
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.CYAN);
            mPaint.setStrokeWidth(1);

            // 将背景填充为白色
            canvas.drawColor(Color.WHITE);
            canvas.translate(40, 40);
            // 设置从右边开始绘制（右对齐）
            mPaint.setTextAlign(Paint.Align.LEFT);
            mPaint.setTextSize(20);

            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[0], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR, paths[0], 0, 60, mPaint);

            // 画布下移120
            canvas.translate(0, 150);

            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[1], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR, paths[1], 0, 0, mPaint);

            // 画布下移120
            canvas.translate(0, 150);

            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[2], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR, paths[2], 0, 0, mPaint);
            // 结论：
            // paths[1].addOval(rectF, Path.Direction.CCW)为逆时针，同时文字在椭圆内侧
            // paths[2].addOval(rectF, Path.Direction.CW)为顺时针，同时文字在椭圆外侧

            // 画布下移120
            canvas.translate(0, 150);

            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[3], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            // canvas.drawTextOnPath(DRAW_STR, paths[3], -60, 0, paint);
            canvas.drawTextOnPath(DRAW_STR, paths[3], 60, -10, mPaint);

            // 画布下移120
            canvas.translate(0, 150);
            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[4], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            // canvas.drawTextOnPath(DRAW_STR, paths[4], 0, -60, paint);
            canvas.drawTextOnPath(DRAW_STR, paths[4], 0, -60, mPaint);
            // 结论：
            // canvas.drawTextOnPath(DRAW_STR, paths[3], -60, 0, paint)
            // x：-60，表示沿path，字符串缩进60，也就是从60之后正常显示，之前的挤在一块
            // x：+60，表示沿path，字符串正常显示之前，空60
            // canvas.drawTextOnPath(DRAW_STR, paths[4], 0, -60, paint)
            // y：-60，表示朝着圆弧上对应的点-60
            // 表示距离圆心增加60的距离，外扩
            // y：+60，表示距离圆心减少60的距离，内缩
            canvas.translate(0, 150);

            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[5], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR, paths[5], -10, 20, mPaint);

            // 画布下移120
            canvas.translate(0, 150);
            mPaint.setTextAlign(Paint.Align.RIGHT);
            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[6], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR, paths[6], -10, 20, mPaint);
            // 结论：
            // 默认顺时针，靠内侧
            // paint.setTextAlign(Paint.Align.LEFT)则靠近起始点
            // paint.setTextAlign(Paint.Align.RIGHT)则靠近结尾点

            // 画布下移120
            canvas.translate(120, 0);

            // 绘制路径
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawPath(paths[7], mPaint);
            // 沿着路径绘制一段文本
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_STR, paths[7], 0, 0, mPaint);
        } else {
            canvas.drawPath(mPath, mPaint);
        }
    }
    public static String[] CONTENTS = {"addArc", "addCircle", "addPath", "addRect", "lineTo", "moveTo", "arcTo", "drawTextOnPath"};

    public int getDrawMode(){
        return drawMode;
    }

    public void setDrawMode(int drawMode) {
        this.drawMode = drawMode;
        switch (drawMode) {
            case 0:
                addArc();
                break;
            case 1:
                addCircle();
                break;
            case 2:
                addPath();
                break;
            case 3:
                addRect();
                break;
            case 4:
                lineTo();
                break;
            case 5:
                moveTo();
                break;
            case 6:
                arcTo();
                break;
            case 7:
                //do nothing,just postInvalidate
                break;
            default:
                addArc();
                break;
        }
        postInvalidate();
    }

    private void addArc() {
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.addArc(new RectF(10, 10, 210, 110), -90, 180);
    }

    private void addCircle() {
        mPath = new Path();
        mPaint.setStyle(Paint.Style.FILL);
        mPath.addCircle(200, 200, 100, Path.Direction.CW);
    }

    private void addPath() {
        mPath = new Path();
        Path path = new Path();
        path.addArc(new RectF(10, 10, 210, 110), -90, 180);
        // mPath.addPath(path);
        mPath.addPath(path, 100, 0);// 起始点移动到(currentX + x , currentY +
        // y)，然后复用src
        // mPath.addPath(path,new Matrix());
    }

    private void addRect() {
        mPath = new Path();
        // mPath.addRect(new RectF(10,10,210,110), Path.Direction.CW);
        // mPath.addRect(10,10,210,110, Path.Direction.CW);
        // mPath.addRoundRect(new RectF(10,10,210,110), new
        // float[]{10,10,10,10,10,10,10,10},Path.Direction.CW);
        mPath.addRoundRect(new RectF(10, 10, 210, 110), 20, 20,
                Path.Direction.CW);
    }

    private void lineTo() {
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.lineTo(100, 100);
        mPath.rLineTo(200, -100);// 是从当前点移动划线
    }

    private void moveTo() {
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.lineTo(100, 100);// 划线(0,0)到(100,100)
        mPath.moveTo(100, 0);// 移动到(100,0)
        mPath.rLineTo(100, 100);// 以(100,0)为基准点划线
        mPath.rMoveTo(0, -100);// 现在的左边是(200,100),以此为基准点相对移动，移动后坐标(200,0)
        mPath.rLineTo(100, 100);// 以(200,0)为基准点划线
    }

    private void arcTo() {
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.lineTo(100, 100);
        // mPath.arcTo(new RectF(10,10,210,110), -90, 180);
        mPath.arcTo(new RectF(10, 10, 210, 110), -90, 180, true);// forceMoveTo强制移动到，当然是不划线啦
    }


}
