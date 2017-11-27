package com.pepe.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author wang
 * @date 2017/11/25.
 */

public class PathMeasureView extends View {
    Paint coordinatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint srcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    {
        coordinatePaint.setColor(Color.GREEN);
        coordinatePaint.setStrokeWidth(2);

        srcPaint.setStyle(Paint.Style.FILL);
        srcPaint.setColor(Color.GRAY);
        srcPaint.setStrokeWidth(3);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.translate( width/2,height/2);

        //画坐标
        canvas.drawLine(-width/2,0,width/2,0,coordinatePaint);
        canvas.drawLine(0,-height/2,0,height/2,coordinatePaint);

        //画srcPath
        Path srcPath = new Path();
        srcPath.lineTo(0,200);
        srcPath.lineTo(200,200);
        srcPath.lineTo(200,0);
        canvas.drawPath(srcPath,srcPaint);

        //测试构造方法中的第二个参数，false表示不强制close，true表示强制close，但不会影响srcPath
        PathMeasure measure1 = new PathMeasure(srcPath,false);
        PathMeasure measure2 = new PathMeasure(srcPath,true);
        Log.e("pepe", "forceClosed=false---->"+measure1.getLength());
        Log.e("pepe", "forceClosed=true----->"+measure2.getLength());

        //测试getSegment(),第三个参数很有意思了：
        //false，表示不强制移动到截取path的起点，直接从(-100，-100)lineTo (0,200);
        //这里的false就有疑问了？按理解应该不强制移动到(0,0)，那么应该先lineTo(0,0)，然后lineTo (0,200);
        //如果是从(0,200)然后lineTo(0,0)，也就是说path的方向是逆时针，所以从(-100，-100)lineTo (0,200)，这样似乎可以解释从(-100，-100)lineTo (0,200)的操作
        //但如果这样，那么最后的从(0,200)然后lineTo(0,0)也应该画出来啊，目前还没有合理的解释。
        //true，表示先moveTo(0,0)，然后lineTo (0,200);

        //如果 startWithMoveTo 为 true, 则被截取出来到Path片段保持原状，
        // 如果 startWithMoveTo 为 false，则会将截取出来的 Path 片段的起始点移动到 dst 的最后一个点，以保证 dst 的连续性。
        mPaint.setColor(Color.RED);
        Path segmentPath1 = new Path();
        segmentPath1.lineTo(-100,-100);
        measure1.getSegment(0,200,segmentPath1,false);
        canvas.drawPath(segmentPath1,mPaint);


        //true，表示moveTo(200,200)，然后lineTo(200,0);
        //false，表示以当前的点作为起点先lineTo(200,200)，然后lineTo(200,0);
        //true	保证截取得到的 Path 片段不会发生形变
        //false	保证存储截取片段的 Path(dst) 的连续性
        mPaint.setColor(Color.BLUE);
        Path segmentPath2 = new Path();
        segmentPath2.lineTo(100,200);
        measure1.getSegment(400,600,segmentPath2,false);
        canvas.drawPath(segmentPath2,mPaint);

        //测试圆和矩形的起始点，中心均为(0,0)
        //圆的起始点（radius，0）
        Path circlePath = new Path();
        circlePath.addCircle(0,0,250, Path.Direction.CW);
        PathMeasure measure3 = new PathMeasure(circlePath,false);
        Path circleTestPath = new Path();
        measure3.getSegment(0,300,circleTestPath,true);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(circleTestPath,mPaint);

        //矩形的起始点（-radius，-radius）
        Path rectPah = new Path();
        rectPah.addRect(-300,-300,300,300, Path.Direction.CW);
        PathMeasure measure4 = new PathMeasure(rectPah,false);
        Path rectTestPath = new Path();
        measure4.getSegment(0,400,rectTestPath,true);
        canvas.drawPath(rectTestPath,mPaint);
    }
}
