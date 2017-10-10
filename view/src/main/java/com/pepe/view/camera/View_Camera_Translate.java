package com.pepe.view.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.pepe.view.R;

/**
 * Created by wang on 2017/10/10.
 */

public class View_Camera_Translate extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(0, 0);
    Point point2 = new Point(0, 200);
    Point point3 = new Point(0, 400);
    Point point4 = new Point(0, 600);

    public View_Camera_Translate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gezi);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(point1.x, point1.y, point1.x + getWidth(), point1.y, paint);
        canvas.drawLine(point2.x, point2.y, point2.x + getWidth(), point2.y, paint);
        canvas.drawLine(point3.x, point3.y, point3.x + getWidth(), point3.y, paint);
        canvas.drawLine(point4.x, point4.y, point4.x + getWidth(), point4.y, paint);

        Matrix matrix = new Matrix();

        // X方向平移，向右为正数
        //在 Camera 中，相机的默认位置是 (0, 0, -8)（英寸）。8 x 72 = 576，所以它的默认位置是 (0, 0, -576)（像素）。
        Camera camera = new Camera();
        Log.d("pepe"," camera.getLocationX()"+ camera.getLocationX());//0
        Log.d("pepe"," camera.getLocationY()"+ camera.getLocationY());//0
        Log.d("pepe"," camera.getLocationZ()"+ camera.getLocationZ());//-8
        camera.save();
        camera.translate(100, 0, 0);
        canvas.save();
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        // Y方向平移，向上为正数
        camera.save();
        camera.translate(0, -30, 0);
        camera.getMatrix(matrix);
        camera.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();



        // Z方向平移，正数为增加距离，图像缩小，负数为减小距离，图像放大
        camera.save();
        camera.translate(0, 0, 10);
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point3.x, point3.y, paint);
        canvas.restore();
    }
}

