package com.pepe.anim.tween;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.pepe.view.R;


/**
 * Created by pepe on 2016/8/20 0020.
 */
public class ScaleAnimAct extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_scale);
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
        switch (item.getItemId()){
            case 0:
                ScaleAnimation aa0 = new ScaleAnimation(0, 1,0,1);
                aa0.setDuration(3000);
                image.startAnimation(aa0);
                break;
            case 1://默认mPivotXType = ABSOLUTE;
                ScaleAnimation aa1 = new ScaleAnimation(0, 1,0,1);
                aa1.setDuration(3000);
                image.startAnimation(aa1);
                break;
            case 2:
                //缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p.
                //当为数值时，mPivotXType = ABSOLUTE = 0,表示在当前View的左上角，即原点处加上50px，做为起始缩放点,整数；
                //如果是50%，mPivotXType = RELATIVE_TO_SELF = 1,表示在当前控件的左上角加上自己宽度的50%做为起始点,浮点数；
                //如果是50%p，mPivotXType = RELATIVE_TO_PARENT = 2,那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标,浮点数。
                ScaleAnimation aa2 = new ScaleAnimation(0, 1,0,1,1,1,1,1);
                aa2.setDuration(3000);
                image.startAnimation(aa2);
                break;
            case 3:
                Animation aa3 = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
                image.startAnimation(aa3);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}