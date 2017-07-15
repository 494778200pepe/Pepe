package com.pepe.animation.View.tween;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/9/12.
 */
public class CustomAnimationAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_animation);
    }

    public void btnAnim(View view) {
        CustomAnimation customAnim = new CustomAnimation();
        customAnim.setRotateY(30);
        view.startAnimation(customAnim);
    }

    public void imgClose(View view) {
        TVAnimation tv = new TVAnimation();
        view.startAnimation(tv);
    }
}
