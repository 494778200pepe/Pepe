package com.pepe.animation.Property;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.zitech.animationdemo.R;

/**
 * Created by pepe on 2016/8/21 0021.
 */
public class AnimatorSetAct extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animator_set);
        image = (ImageView) findViewById(R.id.image);
    }
    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "AnimatorSet");    //添加选项
        menu.add(Menu.NONE, 1, 0, "playTogether");    //添加选项
        menu.add(Menu.NONE, 2, 0, "playWithAfter");    //添加选项
        menu.add(Menu.NONE, 3, 0, "xml");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                ObjectAnimator animator2= ObjectAnimator.ofFloat(image,"translationX",300f);
                ObjectAnimator animator3= ObjectAnimator.ofFloat(image,"scaleX",1f,0f,1f);
                ObjectAnimator animator4= ObjectAnimator.ofFloat(image,"scaleY",1f,0f,1f);
                AnimatorSet set=new AnimatorSet();
                set.setDuration(1000);
                set.playTogether(animator2,animator3,animator4);
                set.start();

                break;
            case 1:
                ObjectAnimator anim11 = ObjectAnimator.ofFloat(image, "scaleX",
                        1.0f, 2f);
                ObjectAnimator anim12 = ObjectAnimator.ofFloat(image, "scaleY",
                        1.0f, 2f);
                AnimatorSet animSet1 = new AnimatorSet();
                animSet1.setDuration(2000);
                animSet1.setInterpolator(new LinearInterpolator());
                //两个动画同时执行
                animSet1.playTogether(anim11, anim12);
                animSet1.start();

                break; 
            case 2:
                float cx = image.getX();

                ObjectAnimator anim21 = ObjectAnimator.ofFloat(image, "scaleX",
                        1.0f, 2f);
                ObjectAnimator anim22 = ObjectAnimator.ofFloat(image, "scaleY",
                        1.0f, 2f);
                ObjectAnimator anim23 = ObjectAnimator.ofFloat(image,
                        "x",  cx ,  0f);
                ObjectAnimator anim24 = ObjectAnimator.ofFloat(image,
                        "x", cx);

                /**
                 * anim1，anim2,anim3同时执行 
                 * anim4接着执行 
                 */
                AnimatorSet animSet2 = new AnimatorSet();
                animSet2.play(anim21).with(anim22);
                animSet2.play(anim22).with(anim23);
                animSet2.play(anim24).after(anim23);
                animSet2.setDuration(1000);
                animSet2.start();

                break;
            case 3:
                // 加载动画
                Animator anim3 = AnimatorInflater.loadAnimator(this, R.animator.scale_anim);
                image.setPivotX(0);
                image.setPivotY(0);
                //显示的调用invalidate
                image.invalidate();
                anim3.setTarget(image);
                anim3.start();
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
