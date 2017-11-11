package com.pepe.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepe.animation.Property.AnimatorSetAct;
import com.pepe.animation.Property.DropActivity;
import com.pepe.animation.Property.KeyframeAct;
import com.pepe.animation.Property.LayoutAnimaAct;
import com.pepe.animation.Property.MojiDemoAct;
import com.pepe.animation.Property.ObjectAnimatorAct;
import com.pepe.animation.Property.PropertyActivity;
import com.pepe.animation.Property.PropertyValuesHolderAct;
import com.pepe.animation.Property.TimerActivity;
import com.pepe.animation.Property.ValueAnimatorAct;
import com.pepe.animation.Property.ViewAnimateAct;


/**
 * Created by pepe on 2016/8/18 0018.
 */
public class PropertyAnimationAct extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_property);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_btn0://ObjectAnimator
                startActivity(new Intent(this,ObjectAnimatorAct.class));
                break;
            case R.id.main_btn1://ValueAnimator
                startActivity(new Intent(this,ValueAnimatorAct.class));
                break;
            case R.id.main_btn2://AnimatorSet
                startActivity(new Intent(this,AnimatorSetAct.class));
                break;
            case R.id.main_btn3://AnimatorSet
                startActivity(new Intent(this,PropertyValuesHolderAct.class));
                break;
            case R.id.main_btn4://Layout Animations
                startActivity(new Intent(this,LayoutAnimaAct.class));
                break;
            case R.id.main_btn5://KeyframeAct
                startActivity(new Intent(this,KeyframeAct.class));
                break;
            case R.id.main_btn6://ViewAnimateAct
                startActivity(new Intent(this,ViewAnimateAct.class));
                break;
            case R.id.main_btn7://Drop
                startActivity(new Intent(this, DropActivity.class));
                break;
            case R.id.main_btn8://Menu
                startActivity(new Intent(this, PropertyActivity.class));
                break;
            case R.id.main_btn9://Timer
                startActivity(new Intent(this, TimerActivity.class));
                break;
            case R.id.main_btn10://MojiDemo
                startActivity(new Intent(this, MojiDemoAct.class));
                break;

        }
    }
}
