package com.pepe.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepe.animation.View.TweenAnimationAct;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_btn0://视图动画
                startActivity(new Intent(this,TweenAnimationAct.class));
                break;
            case R.id.main_btn1://属性动画
                startActivity(new Intent(this,PropertyAnimationAct.class));
                break;
            case R.id.main_btn2://矢量动画
                startActivity(new Intent(this,VectorAnimationAct.class));
                break;

        }
    }


}
