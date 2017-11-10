package com.hencoder.draw.practicedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepe.view.R;


public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);
    Camera camera = new Camera();
    Matrix matrix = new Matrix();

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //applyToCanvas写法，位移的顺序要倒过来哦
//        canvas.save();
//        camera.save();
//        camera.rotateX(30);
//        canvas.translate(200 + bitmap.getWidth() / 2, 200 + bitmap.getHeight() / 2);
//        camera.applyToCanvas(canvas);
//        canvas.translate(-200 - bitmap.getWidth() / 2, -200 - bitmap.getHeight() / 2);
//        camera.restore();
//        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
//        canvas.restore();
//
//
//        canvas.save();
//        camera.save();
//        camera.rotateY(30);
//        canvas.translate(600 + bitmap.getWidth() / 2, 200 + bitmap.getHeight() / 2);
//        camera.applyToCanvas(canvas);
//        canvas.translate(-600 - bitmap.getWidth() / 2, -200 - bitmap.getHeight() / 2);
//        camera.restore();
//        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
//        canvas.restore();


        //setMatrix 写法   这种写法的顺序是正常的
        canvas.save();
        camera.save();
        camera.rotateX(30);
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-200 - bitmap.getWidth() / 2, -200 - bitmap.getHeight() / 2);
        matrix.postTranslate(200 + bitmap.getWidth() / 2, 200 + bitmap.getHeight() / 2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        canvas.save();
        camera.save();
        camera.rotateY(30);
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-600 - bitmap.getWidth() / 2, -200 - bitmap.getHeight() / 2);
        matrix.postTranslate(600 + bitmap.getWidth() / 2, 200 + bitmap.getHeight() / 2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
