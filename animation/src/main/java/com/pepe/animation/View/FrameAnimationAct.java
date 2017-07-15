package com.pepe.animation.View;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/8/18 0018.
 */
public class FrameAnimationAct extends AppCompatActivity {

    ImageView image;
    ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_frame_animation);
        image = (ImageView) findViewById(R.id.image);
        image2 = (ImageView) findViewById(R.id.image2);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        image.setBackgroundResource(R.drawable.frameanim);  //将动画资源文件设置为ImageView的背景
        AnimationDrawable anim = (AnimationDrawable) image.getBackground(); //获取ImageView背景,此时已被编译成AnimationDrawable
        anim.start();   //开始动画

//        // 通过逐帧动画的资源文件获得AnimationDrawable示例
//        AnimationDrawable frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.frameanim);
//        // 把AnimationDrawable设置为ImageView的背景
//        image.setBackgroundDrawable(frameAnim);
//        frameAnim.start();

        runFrame(image2);
    }

    public void runFrame(View view) {
        //完全编码实现的动画效果
        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 1; i <= 6; i++) {
            //根据资源名称和目录获取R.java中对应的资源ID
            int id = getResources().getIdentifier("flower" + i, "mipmap", getPackageName());
            //根据资源ID获取到Drawable对象
            Drawable drawable = getResources().getDrawable(id);
            //将此帧添加到AnimationDrawable中
            anim.addFrame(drawable, 500);
        }
        anim.setOneShot(false); //设置为loop
        view.setBackgroundDrawable(anim);  //将动画设置为ImageView背景
        anim.start();   //开始动画
    }

}
