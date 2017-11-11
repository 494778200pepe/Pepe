package com.pepe.anim.tween;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.pepe.view.R;


/**
 * Created by pepe on 2016/8/20 0020.
 */
public class RotateAnimAct extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_rotate);
        image = (ImageView) findViewById(R.id.image_xml);
    }

    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "constructor1");    //添加选项
        menu.add(Menu.NONE, 1, 0, "constructor2");    //添加选项
        menu.add(Menu.NONE, 2, 0, "constructor3");    //添加选项
        menu.add(Menu.NONE, 3, 0, "AnimationUtils");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                RotateAnimation aa0 = new RotateAnimation(0, 360);
                aa0.setDuration(3000);
                image.startAnimation(aa0);
                break;
            case 1:
                //默认mPivotXType = ABSOLUTE; 相对于(0,0)的距离
                RotateAnimation aa1 = new RotateAnimation(0, -720, 100, 75);
                aa1.setDuration(3000);
                image.startAnimation(aa1);
                break;
            case 2:
                //旋转中心点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p.
                //当为数值时，mPivotXType = ABSOLUTE = 0,表示在当前View的左上角，即原点处加上50px，做为起始缩放点,整数；
                //如果是50%，mPivotXType = RELATIVE_TO_SELF = 1,表示在当前控件的左上角加上自己宽度的50%做为起始点,浮点数；
                //如果是50%p，mPivotXType = RELATIVE_TO_PARENT = 2,那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标,浮点数。
                RotateAnimation aa2 = new RotateAnimation(0, 360, 2, 0.5f, 2, 0.5f);
                aa2.setDuration(3000);
                image.startAnimation(aa2);
                break;
            case 3:
                Animation aa3 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
                image.startAnimation(aa3);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

