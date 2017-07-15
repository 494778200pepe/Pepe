package com.pepe.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepe.animation.View.FrameAnimationAct;
import com.pepe.animation.View.TweenAnimationAct;


/**
 * Created by pepe on 2016/8/18 0018.
 */
public class ViewAnimationAct extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_animation);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_btn0://补间动画
                startActivity(new Intent(this,TweenAnimationAct.class));
                break;
            case R.id.main_btn1://帧动画
                startActivity(new Intent(this,FrameAnimationAct.class));
                break;

        }
    }
}
