package com.pepe.view.touch;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.pepe.view.Consts;

/**
 * Created by wang on 2017/7/15.
 */

public class MyClickImageView extends AppCompatImageView implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = MyClickImageView.class.getSimpleName();

    public MyClickImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(Consts.TAG, "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(Consts.TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(Consts.TAG, "onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(Consts.TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(Consts.TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(Consts.TAG, "dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(Consts.TAG, "onTouch ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(Consts.TAG, "onTouch ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(Consts.TAG, "onTouch ACTION_UP");
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Log.e(Consts.TAG, "onClick");
    }

    @Override
    public boolean onLongClick(View v) {
        Log.e(Consts.TAG, "onLongClick");
        return true;
    }
    //1、onTouch 返回false，onLongClick 返回false
//    E/pepe    (18959): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (18959): onTouch ACTION_DOWN
//    E/pepe    (18959): onTouchEvent ACTION_DOWN
//    E/pepe    (18959): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (18959): onTouch ACTION_MOVE
//    E/pepe    (18959): onTouchEvent ACTION_MOVE
//    E/pepe    (18959): onLongClick
//    E/pepe    (18959): dispatchTouchEvent ACTION_UP
//    E/pepe    (18959): onTouch ACTION_UP
//    E/pepe    (18959): onTouchEvent ACTION_UP
//    E/pepe    (18959): onClick

    //2、onTouch 返回true,onLongClick 返回发false
//    E/pepe    (19255): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (19255): onTouch ACTION_DOWN
//    E/pepe    (19255): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (19255): onTouch ACTION_MOVE
//    E/pepe    (19255): dispatchTouchEvent ACTION_UP
//    E/pepe    (19255): onTouch ACTION_UP

    //3、onTouch 返回false，onLongClick 返回true
//    E/pepe    (19504): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (19504): onTouch ACTION_DOWN
//    E/pepe    (19504): onTouchEvent ACTION_DOWN
//    E/pepe    (19504): onLongClick
//    E/pepe    (19504): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (19504): onTouch ACTION_MOVE
//    E/pepe    (19504): onTouchEvent ACTION_MOVE
//    E/pepe    (19504): dispatchTouchEvent ACTION_UP
//    E/pepe    (19504): onTouch ACTION_UP
//    E/pepe    (19504): onTouchEvent ACTION_UP



    //说明：
    //1、onLongClick 返回true，会拦截onClick；返回false，不拦截onClick
    //2、onTouch 返回true时，就会执行onTouchEvent
    //3、onClick 和 onLongClick 都在 onTouchEvent中执行
    //4、onLongClick 在 onTouchEvent 的 down 事件中执行
    //5、onClick 在 onTouchEvent 的 up 事件中执行
    //6、只要是 clickable，均会消费事件

}

