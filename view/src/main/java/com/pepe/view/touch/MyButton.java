package com.pepe.view.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.pepe.view.Consts;


/**
 * Created by wang on 2017/7/15.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {

    public MyButton(Context context, AttributeSet attrs) {
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
//    E/pepe    (21523): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (21523): onTouch ACTION_DOWN
//    E/pepe    (21523): onTouchEvent ACTION_DOWN
//    E/pepe    (21523): onLongClick
//    E/pepe    (21523): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (21523): onTouch ACTION_MOVE
//    E/pepe    (21523): onTouchEvent ACTION_MOVE
//    E/pepe    (21523): dispatchTouchEvent ACTION_UP
//    E/pepe    (21523): onTouch ACTION_UP
//    E/pepe    (21523): onTouchEvent ACTION_UP
//    E/pepe    (21523): onClick


    //2、onTouch 返回true，onLongClick 返回false
//    E/pepe    (21778): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (21778): onTouch ACTION_DOWN
//    E/pepe    (21778): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (21778): onTouch ACTION_MOVE
//    E/pepe    (21778): dispatchTouchEvent ACTION_UP
//    E/pepe    (21778): onTouch ACTION_UP

    //2、onTouch false，onLongClick 返回true
//    E/pepe    (22094): dispatchTouchEvent ACTION_DOWN
//    E/pepe    (22094): onTouch ACTION_DOWN
//    E/pepe    (22094): onTouchEvent ACTION_DOWN
//    E/pepe    (22094): onLongClick
//    E/pepe    (22094): dispatchTouchEvent ACTION_MOVE
//    E/pepe    (22094): onTouch ACTION_MOVE
//    E/pepe    (22094): onTouchEvent ACTION_MOVE
//    E/pepe    (22094): dispatchTouchEvent ACTION_UP
//    E/pepe    (22094): onTouch ACTION_UP
//    E/pepe    (22094): onTouchEvent ACTION_UP

    //说明：跟MyClickImageView 一样的

}
