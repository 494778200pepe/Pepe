package com.pepe.animation.Property;


import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/8/21 0021.
 * 简单的使用mBlueBall.animate().alpha(0).y(mScreenHeight / 2).setDuration(1000).start()就能实现动画~~
 * 不过需要SDK11，此后在SDK12，SDK16又分别添加了withStartAction和withEndAction用于在动画前，和动画后执行一些操作。
 * 当然也可以.setListener(listener)等操作。
 */
public class ViewAnimateAct extends Activity {
    protected static final String TAG = "ViewAnimateAct";

    private ImageView mBlueBall;
    private float mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_anim);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
        mBlueBall = (ImageView) findViewById(R.id.id_ball);

    }

    public void viewAnim(View view) {
        // need API12
        mBlueBall.animate()//
                .alpha(0)//
                .y(mScreenHeight / 2).setDuration(1000)
                // need API 12
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "START");
                    }
                    // need API 16
                }).withEndAction(new Runnable() {

            @Override
            public void run() {
                Log.e(TAG, "END");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBlueBall.setY(0);
                        mBlueBall.setAlpha(1.0f);
                    }
                });
            }
        }).start();
    }
}
