package com.pepe.anim.tween;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.pepe.view.R;


/**
 * Created by pepe on 2016/8/20 0020.
 */
public class TranslateAnimAct extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_rotate);
        image = (ImageView) findViewById(R.id.image_xml);
        image.setClickable(true);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TranslateAnimAct.this,"hehe",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "constructor1");    //添加选项
        menu.add(Menu.NONE, 1, 0, "constructor2");    //添加选项
        menu.add(Menu.NONE, 2, 0, "AnimationUtils");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                //默认mPivotXType = ABSOLUTE
                TranslateAnimation aa0 = new TranslateAnimation(0, 200,0,150);
                aa0.setDuration(3000);
                aa0.setFillBefore(false);
                aa0.setFillAfter(true);
                image.startAnimation(aa0);
                break;
            case 1:
                //两个点(x,y)四个type
                TranslateAnimation aa1 = new TranslateAnimation(0,0,1,0.5f,0,0,1,0.5f);
                aa1.setDuration(3000);
                image.startAnimation(aa1);
                break;
            case 2:
                Animation aa3 = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
                image.startAnimation(aa3);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
