package com.pepe.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by wang on 2017/7/12.
 */

public class EventAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_event);
        ImageView img = (ImageView) findViewById(R.id.img);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("pepe", "onTouch----img----" + "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("pepe", "onTouch----img----" + "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("pepe", "onTouch----img----" + "ACTION_UP");
                        break;
                }
                return false;
            }
        });
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("pepe", "onTouch----btn----" + "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("pepe", "onTouch----btn----" + "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("pepe", "onTouch----btn----" + "ACTION_UP");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("pepe", "dispatchTouchEvent----" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("pepe", "dispatchTouchEvent----" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("pepe", "dispatchTouchEvent----" + "ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("pepe", "onTouchEvent----" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("pepe", "onTouchEvent----" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("pepe", "onTouchEvent----" + "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
