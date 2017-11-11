package com.pepe.animation.Property;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/9/8 0008.
 */
public class KeyframeAct extends AppCompatActivity {
    ImageView image;
    private float mScreenHeight;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_keyframe);
        image = (ImageView) findViewById(R.id.image_xml);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
    }

    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "Keyframe ");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Keyframe keyframe1 = Keyframe.ofFloat(0f, 0f);//第一帧动画 动画完成度0的时候的值是0
                Keyframe keyframe2 = Keyframe.ofFloat(.5f, 200.0f);//第二帧动画 动画完成度0.5也就是一半的时候值是200
                Keyframe keyframe3 = Keyframe.ofFloat(1f, 0f);//第三帧动画 动画完成度1也就是动画结束的时候值是0.

                PropertyValuesHolder property = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(image, property);
                objectAnimator.start();
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}