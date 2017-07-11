package com.pepe.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zitech.animationdemo.Vector.VectorDemo1Act;
import com.zitech.animationdemo.Vector.VectorDemo2Act;
import com.zitech.animationdemo.Vector.VectorDemo3Act;

/**
 * Created by pepe on 2016/8/19 0019.
 */
public class VectorAnimationAct extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vector_animation);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_btn0://
                startActivity(new Intent(this,VectorDemo1Act.class));
                break;
            case R.id.main_btn1://
                startActivity(new Intent(this,VectorDemo2Act.class));
                break;
            case R.id.main_btn2://
                startActivity(new Intent(this,VectorDemo3Act.class));
                break;
            case R.id.main_btn3://
                break;
            case R.id.main_btn4://
                break;


        }
    }
}
