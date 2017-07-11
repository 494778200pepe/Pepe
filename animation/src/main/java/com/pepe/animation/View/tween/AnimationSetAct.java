package com.pepe.animation.View.tween;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.zitech.animationdemo.R;

/**
 * Created by pepe on 2016/8/20 0020.
 */
public class AnimationSetAct  extends AppCompatActivity {

    Context mContext=AnimationSetAct.this;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_set_animation);
        image = (ImageView) findViewById(R.id.image);
    }
    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "constructor");    //添加选项
        menu.add(Menu.NONE, 1, 0, "set1");    //添加选项
        menu.add(Menu.NONE, 2, 0, "");    //添加选项
        menu.add(Menu.NONE, 3, 0, "");    //添加选项

        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                AnimationSet as=new AnimationSet(false);//设置shareInterpolator=false
                AlphaAnimation aa=new AlphaAnimation(0,1);
                ScaleAnimation sa = new ScaleAnimation(0, 1,0,1);
                as.addAnimation(aa);
                as.addAnimation(sa);
                as.setDuration(3000);//默认duration为0
                image.startAnimation(as);
                break;
            case 1:
                Animation aa0 = AnimationUtils.loadAnimation(this, R.anim.setanim);
                image.startAnimation(aa0);
                break;
            case 2:
                break;
            case 3:
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
