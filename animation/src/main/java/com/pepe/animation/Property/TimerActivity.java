package com.pepe.animation.Property;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zitech.animationdemo.R;

/**
 * Created by Administrator on 2016/6/3.
 * 计时器动画
 */
public class TimerActivity extends Activity {

    private TextView mTimerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_timer);
        mTimerTextView = (TextView) findViewById(R.id.tv_timer);
        mTimerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ValueAnimator animator = ValueAnimator.ofInt(0, 100);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        mTimerTextView.setText("$" + valueAnimator.getAnimatedValue());
                    }
                });
                animator.setTarget(view);
                animator.setDuration(3000);
                animator.start();
            }
        });
    }
}

