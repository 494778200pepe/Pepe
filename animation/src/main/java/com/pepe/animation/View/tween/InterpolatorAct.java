package com.pepe.animation.View.tween;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/8/20 0020.
 */
public class InterpolatorAct  extends AppCompatActivity {

    Context mContext=InterpolatorAct.this;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_interpolator);
        image = (ImageView) findViewById(R.id.image);
    }
    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "AccelerateDecelerateInterpolator");    //动画始末速率较慢，中间加速
        menu.add(Menu.NONE, 1, 0, "AccelerateInterpolator");    //	动画开始速率较慢，之后慢慢加速
        menu.add(Menu.NONE, 2, 0, "AnticipateInterpolator");    //开始的时候从后向前甩
        menu.add(Menu.NONE, 3, 0, "AnticipateOvershootInterpolator");    //添加选项
        menu.add(Menu.NONE, 4, 0, "BounceInterpolator");    //动画结束时弹起
        menu.add(Menu.NONE, 5, 0, "CycleInterpolator");    //循环播放速率改变为正弦曲线
        menu.add(Menu.NONE, 6, 0, "DecelerateInterpolator");    //动画开始快然后慢
        menu.add(Menu.NONE, 7, 0, "LinearInterpolator");    //动画匀速改变
        menu.add(Menu.NONE, 8, 0, "OvershootInterpolator");    //向前弹出一定值之后回到原来位置
        menu.add(Menu.NONE, 9, 0, "PathInterpolator");    //新增，定义路径坐标后按照路径坐标来跑
        menu.add(Menu.NONE, 10, 0, "");    //添加选项
        menu.add(Menu.NONE, 11, 0, "");    //添加选项
        menu.add(Menu.NONE, 12, 0, "");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        ScaleAnimation sa = new ScaleAnimation(0,2,0,2);
        switch (item.getItemId()){
            case 0://在动画开始与结束的地方速率改变比较慢，在中间的时候加速
                sa.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case 1://在动画开始的地方速率改变比较慢，然后开始加速
                sa.setInterpolator(new AccelerateInterpolator());
                break;
            case 2://开始的时候向后然后向前甩
                sa.setInterpolator(new AnticipateInterpolator());
                break;
            case 3://开始的时候向后然后向前甩一定值后返回最后的值
                sa.setInterpolator(new AnticipateOvershootInterpolator());
                break;
            case 4://动画结束的时候弹起
                sa.setInterpolator(new BounceInterpolator());
                break;
            case 5://动画循环播放特定的次数，速率改变沿着正弦曲线
                sa.setInterpolator(new CycleInterpolator(2));
                break;
            case 6://在动画开始的地方快然后慢
                sa.setInterpolator(new DecelerateInterpolator());
                break;
            case 7://以常量速率改变
                sa.setInterpolator(new LinearInterpolator());
                break;
            case 8://向前甩一定值后再回到原来位置
                sa.setInterpolator(new OvershootInterpolator());
                break;
            case 9://
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            default:
                break;
        }
        sa.setDuration(3000);
        image.startAnimation(sa);
        return super.onOptionsItemSelected(item);
    }
}
