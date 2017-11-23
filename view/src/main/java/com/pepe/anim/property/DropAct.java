package com.pepe.anim.property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pepe.view.R;


/**
 * Created by Administrator on 2016/6/3.
 * 下拉展开动画
 */
public class DropAct extends Activity {

    private LinearLayout mHiddenView;
    private float mDensity;
    private int mHiddenViewMeasuredHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_prop_drop);
        mHiddenView = (LinearLayout) findViewById(R.id.hidden_view);
        // 获取像素密度
        mDensity = getResources().getDisplayMetrics().density;
        // 获取布局的高度,40是在XML文件中定义的布局高度
        mHiddenViewMeasuredHeight = (int) (mDensity * 40 + 0.5);
    }

    public void llClick(View view) {
        if (mHiddenView.getVisibility() == View.GONE) {
            // 打开动画
            animateOpen();
        } else {
            // 关闭动画
            animateClose();
        }
    }

    private void animateClose() {
        ValueAnimator animator = createDropAnimator(mHiddenView.getHeight(), 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mHiddenView.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private void animateOpen() {
        mHiddenView.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(0, mHiddenViewMeasuredHeight);
        animator.start();
    }

    private ValueAnimator createDropAnimator(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //通过ValueAnimator创建的数值发生器，并由此来改变View的布局属性
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams params = mHiddenView.getLayoutParams();
                params.height = value;
                mHiddenView.setLayoutParams(params);
            }
        });
        return animator;
    }
}
