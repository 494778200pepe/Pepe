package com.pepe.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.pepe.Consts;

/**
 * Created by wang on 2017/7/15.
 */

public class MyTouchImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener{

    public MyTouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setClickable(true);
        setOnTouchListener(this);
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
//        return super.onTouchEvent(event);
        return true;
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
        return false



















                ;
    }

    //1、onTouch 返回false
//    E/pepe    (15018): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (15018): onTouch ACTION_DOWN
//    E/pepe    (15018): onTouchEvent ACTION_DOWN

    //2、onTouch 返回true
//    E/pepe    (15261): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (15261): onTouch ACTION_DOWN
//    E/pepe    (15261): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (15261): onTouch ACTION_MOVE
//    E/pepe    (15261): dispatchTouchEvent ACTION_UP
//    E/pepe    (15261): onTouch ACTION_UP

    //3、设置setClickable(true);
//    E/pepe    (16752): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (16752): onTouch ACTION_DOWN
//    E/pepe    (16752): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (16752): onTouch ACTION_MOVE
//    E/pepe    (16752): dispatchTouchEvent ACTION_UP
//    E/pepe    (16752): onTouch ACTION_UP

    //4、设置setClickable(false)，onTouchEvent 返回true
//    E/pepe    (25724): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (25724): onTouch ACTION_DOWN
//    E/pepe    (25724): onTouchEvent ACTION_DOWN
//    E/pepe    (25724): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (25724): onTouch ACTION_MOVE
//    E/pepe    (25724): onTouchEvent ACTION_MOVE
//    E/pepe    (25724): dispatchTouchEvent ACTION_UP
//    E/pepe    (25724): onTouch ACTION_UP
//    E/pepe    (25724): onTouchEvent ACTION_UP

    //说明：
    //1、如果onTouch 返回false，表示没有消费事件，后续的event就不会传递过来，只会执行 onTouchEvent
    //2、如果onTouch 返回true，表示消费事件，后续的onTouchEvent就不会执行了
    //3、设置setClickable(true)，那么都会消费事件
    //4、onTouch 在 dispatchTouchEvent 中执行
    //5、onTouch 返回false，那么 dispatchTouchEvent 也会返回false，后续的 action 就会传过来了
    //6、再看看这个事件传递：
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED &&
//                mOnTouchListener.onTouch(this, event)) {
//            return true;
//        }
//        return onTouchEvent(event);
//    }
        //如果onTouch 返回 false，那么交给onTouchEvent,由于ImageView的Clickable 是 false，那么 onTouchEvent(event)会返回false。
        //后续的事件也就不会过来了
    //7、如果onTouch 返回false，但是onTouchEvent 返回true的话，同样是消费了事件。onTouch 和 onTouchEvent 的三个action都会执行了。
    //8、onTouchEvent 的默认处理是 判断 clickable。






}
