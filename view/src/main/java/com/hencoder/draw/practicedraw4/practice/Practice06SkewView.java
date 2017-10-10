package com.hencoder.draw.practicedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pepe.view.R;


public class Practice06SkewView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(450, 250);

    public Practice06SkewView(Context context) {
        super(context);
    }

    public Practice06SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice06SkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gezi);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.GREEN);
        canvas.drawRect(point1.x-20, point1.y,point1.x+bitmap.getWidth()+20, point1.y+bitmap.getHeight(),paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(point2.x, point2.y-20,point2.x+bitmap.getWidth(), point2.y+bitmap.getHeight()+20,paint);

        canvas.save();
        //y为负数，朝上，以图片左边为基线
        canvas.skew(0, -0.5f);
        //向上偏移的距离不完全是0.5f
//        canvas.translate(0,bitmap.getHeight()*0.5f);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        //y为正数，朝下，以图片左边为基线
        canvas.skew(0, 0.5f);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        //x为正数，朝右，以图片上边为基线
        canvas.skew(0.8f, 0);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();

        canvas.save();
        //x为负数，朝左，以图片上边为基线
        canvas.skew(-0.8f, 0);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
