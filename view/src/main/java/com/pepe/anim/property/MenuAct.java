package com.pepe.anim.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.pepe.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 * 灵动菜单
 */
public class MenuAct extends Activity implements View.OnClickListener {

    private int[] mRes = {R.id.imageView_a, R.id.imageView_b, R.id.imageView_c,
            R.id.imageView_d, R.id.imageView_e};
    private List<ImageView> mImageViews = new ArrayList<ImageView>();
    private boolean mFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_prop_menu);
        for (int i = 0; i < mRes.length; i++) {
            ImageView imageView = (ImageView) findViewById(mRes[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_a:
                if (mFlag) {
                    startAnim();
                } else {
                    closeAnim();
                }
                break;
            default:
                Toast.makeText(this, "" + view.getId(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha", 0.5F, 1F);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageViews.get(1), "translationY", 200F, 0);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageViews.get(2), "translationX", 200F, 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageViews.get(3), "translationY", -200F, 0);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageViews.get(4), "translationX", -200F, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        mFlag = true;
    }

    private void startAnim() {
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(mImageViews.get(0), "alpha", 1, 0.5f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageViews.get(1), "translationY", 200);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageViews.get(2), "translationX", 200);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageViews.get(3), "translationY", -200);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageViews.get(4), "translationX", -200);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0, animator1, animator2, animator3, animator4);
        set.start();
        mFlag = false;
    }
}
