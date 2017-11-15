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
import android.view.View;

import com.pepe.view.R;

/**
 * Created by wang on 2017/10/10.
 */

public class CameraRotateView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(0, 0);
    Point point2 = new Point(0, 200);
    Point point3 = new Point(0, 400);
    Point point4 = new Point(0, 600);
    Point point5 = new Point(0, 800);
    Point point6 = new Point(0, 1000);
    Point point7 = new Point(0, 1200);

    public CameraRotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(point1.x, point1.y, point1.x + getWidth(), point1.y, paint);
        canvas.drawLine(point2.x, point2.y, point2.x + getWidth(), point2.y, paint);
        canvas.drawLine(point3.x, point3.y, point3.x + getWidth(), point3.y, paint);
        canvas.drawLine(point4.x, point4.y, point4.x + getWidth(), point4.y, paint);
        canvas.drawLine(point5.x, point5.y, point5.x + getWidth(), point5.y, paint);
        canvas.drawLine(point6.x, point6.y, point6.x + getWidth(), point6.y, paint);

        Matrix matrix = new Matrix();

        //如果不对camera的位置做调整，那么原点为屏幕左上角（0,0,0）

        // 绕X轴旋转,正数朝屏幕外转，负数朝屏幕里面转
        Camera camera = new Camera();
        camera.save();
        camera.rotateX(45);
        canvas.save();
//        canvas.translate(bitmap.getWidth()/2,bitmap.getHeight()/2);
        camera.applyToCanvas(canvas);
//        canvas.translate(-bitmap.getWidth()/2,-bitmap.getHeight()/2);
        camera.restore();
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();



        camera.save();
        camera.rotateX(45);
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        canvas.save();
//        canvas.translate(bitmap.getWidth()/2+100,bitmap.getHeight()/2);
        canvas.concat(matrix);
//        canvas.translate(-bitmap.getWidth()/2+100,-bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, point1.x+250, point1.y, paint);
        canvas.restore();

        // 绕Y轴旋转,正数朝屏幕里面转，负数朝屏幕外转
        camera.save();
        camera.rotateY(45);
        camera.getMatrix(matrix);
        camera.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();

        // 绕Z轴旋转,正数逆时针转，负数顺时针转
        camera.save();
        camera.rotateZ(15);
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point3.x, point3.y, paint);
        canvas.restore();


        camera.save();
        camera.rotateX(30);
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point4.x, point4.y, paint);
        canvas.restore();

    }
}
