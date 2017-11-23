package com.pepe.anim.tween;

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

import com.pepe.view.R;


/**
 * Created by pepe on 2016/8/20 0020.
 */
public class AnimSetAct extends AppCompatActivity {

    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_set);
        image = (ImageView) findViewById(R.id.image_xml);
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
                as.setFillAfter(true);
                as.setFillBefore(false);
                //默认duration为0
                as.setDuration(3000);
                as.reset();
                image.startAnimation(as);
                break;
            case 1:
                Animation aa0 = AnimationUtils.loadAnimation(this, R.anim.anim_set);
                image.startAnimation(aa0);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
