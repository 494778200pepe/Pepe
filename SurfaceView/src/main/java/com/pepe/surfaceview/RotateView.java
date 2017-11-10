package com.pepe.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.support.test.espresso.core.deps.guava.util.concurrent.ThreadFactoryBuilder;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wang
 * @date 2017/10/28.
 */

public class RotateView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean mIsDrawing;
    private int mRadius;

    /**
     * 封闭圆环的半径
     */
    int roundRadius = 63;
    /**
     * 封闭圆环的宽度
     */
    int roundWidth = 2;
    /**
     * 内部小球的半径
     */
    int inBallRadius = 10;

    /**
     * 开口圆弧的半径
     */
    int circleRadius = 81;
    /**
     * 开口圆弧的宽度
     */
    int circleWidth = 4;
    /**
     * 外部小球的半径
     */
    int outBallRaius = 10;
    int degree = 0;
    int speed = 3;

    public final int SLEEP_TIME = 10;
    double pi = Math.PI;

    /**
     * Positive example 1：
     //org.apache.commons.lang3.concurrent.BasicThreadFactory
     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
     new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

     Positive example 2：
     ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
     .setNameFormat("demo-pool-%d").build();

     //Common Thread Pool
     ExecutorService pool = new ThreadPoolExecutor(5, 200,
     0L, TimeUnit.MILLISECONDS,
     new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

     pool.execute(()-> System.out.println(Thread.currentThread().getName()));
     pool.shutdown();//gracefully shutdown
     */
//    private ExecutorService pool = Executors.newFixedThreadPool(5);

    ThreadFactory mThreadFactory =new ThreadFactoryBuilder()
            .setNameFormat("pepe-pool-%d").build();

    ExecutorService mPool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), mThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public RotateView(Context context) {
        super(context);
        initView();
    }

    public RotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RotateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        //设置背景透明
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        setFocusable(true);
        //控制这个surfaceView是否被放在窗口顶层
        setZOrderOnTop(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsDrawing = true;
        //new Thread(this).start();
        mPool.execute(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            draw();
        }

        long end = System.currentTimeMillis();
        if (end - start < SLEEP_TIME) {
            try {
                Thread.sleep(SLEEP_TIME - (end - start));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Canvas getCanvas() {
        return mCanvas;
    }

    private void setCanvas(Canvas aCanvas) {
        this.mCanvas = aCanvas;
    }


    private void lockCanvas() {
        setCanvas(mHolder.lockCanvas());
    }

    private void unlockCanvas() {
        if (getCanvas() != null) {
            mHolder.unlockCanvasAndPost(getCanvas());
        }
    }

    private synchronized void draw() {
        try {
            lockCanvas();
            drawContent();
            unlockCanvas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawContent() {
        //mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// 设置画布的背景为透明
        mCanvas.drawColor(Color.parseColor("#101011"), PorterDuff.Mode.CLEAR);

        int canvasWidth = mCanvas.getWidth();
        mRadius = canvasWidth / 2;
        mCanvas.rotate(degree, mRadius, mRadius);

        drawCircle();
        drawInBall();
        drawOutBall();
        drawArc();

        degree += speed;
    }

    private void drawCircle() {
        //画封闭圆环
        mPaint.setColor(getResources().getColor(R.color.color_blue));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(roundWidth);
        mCanvas.drawCircle(mRadius, mRadius, roundRadius - roundWidth / 2, mPaint);
    }


    private void drawInBall() {
        //画内部小球
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(inBallRadius);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //计算x和Y坐标
        double inX = mRadius + roundRadius * (Math.cos(pi / (180 / (45)))) - 1;
        double inY = mRadius + roundRadius * (Math.sin(pi / (180 / (45)))) - 1;
        mCanvas.drawPoint((float) inX, (float) inY, mPaint);
    }

    private void drawOutBall() {
        //画外部小球
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(outBallRaius);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //计算x和Y坐标
        double outX = mRadius - circleRadius * (Math.sin(pi / (180 / (30))));
        double outY = mRadius - circleRadius * (Math.cos(pi / (180 / (30))));
        mCanvas.drawPoint((float) outX, (float) outY, mPaint);
    }

    private void drawArc() {
        //画开口圆弧
        mPaint.setStrokeWidth(circleWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(mRadius - circleRadius, mRadius - circleRadius, mRadius + circleRadius
                , mRadius + circleRadius);
        mCanvas.drawArc(oval, -45, 285, false, mPaint);

    }

}
