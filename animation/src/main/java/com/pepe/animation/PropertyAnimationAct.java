package com.pepe.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepe.animation.Property.LayoutAnimaAct;
import com.pepe.animation.Property.MojiDemoAct;


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
            case R.id.main_btn4://Layout Animations
                startActivity(new Intent(this,LayoutAnimaAct.class));
                break;
            case R.id.main_btn10://MojiDemo
                startActivity(new Intent(this, MojiDemoAct.class));
                break;

        }
    }
}
