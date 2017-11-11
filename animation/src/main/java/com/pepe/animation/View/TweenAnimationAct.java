package com.pepe.animation.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepe.animation.R;
import com.pepe.animation.View.tween.LayoutAnimationControllerAct;


/**
 * Created by pepe on 2016/8/18 0018.
 */
public class TweenAnimationAct extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tween_animation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn5://
                startActivity(new Intent(this, LayoutAnimationControllerAct.class));
                break;
        }
    }
}
