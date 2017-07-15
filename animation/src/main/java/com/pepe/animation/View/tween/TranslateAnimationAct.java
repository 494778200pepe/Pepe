package com.pepe.animation.View.tween;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/8/20 0020.
 */
public class TranslateAnimationAct extends AppCompatActivity {
    Context mContext=TranslateAnimationAct.this;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rotate_animation);
        image = (ImageView) findViewById(R.id.image);
    }

    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "constructor1");    //添加选项
        menu.add(Menu.NONE, 1, 0, "constructor2");    //添加选项
        menu.add(Menu.NONE, 2, 0, "AnimationUtils");    //添加选项
        menu.add(Menu.NONE, 3, 0, "");    //添加选项
        menu.add(Menu.NONE, 5, 0, "");    //添加选项
        menu.add(Menu.NONE, 6, 0, "");    //添加选项
        menu.add(Menu.NONE, 7, 0, "");    //添加选项
        menu.add(Menu.NONE, 8, 0, "");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                TranslateAnimation aa0 = new TranslateAnimation(0, 200,0,150);
                aa0.setDuration(3000);
                image.startAnimation(aa0);
                break;
            case 1://默认mPivotXType = ABSOLUTE;两个点四个type
                TranslateAnimation aa1 = new TranslateAnimation(0,0,1,0.5f,0,0,1,0.5f);
                aa1.setDuration(3000);
                image.startAnimation(aa1);
                break;
            case 2:
                Animation aa3 = AnimationUtils.loadAnimation(this, R.anim.translateanim);
                image.startAnimation(aa3);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;

            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
