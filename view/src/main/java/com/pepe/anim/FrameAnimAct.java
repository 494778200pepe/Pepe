package com.pepe.anim;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.pepe.view.R;


/**
 * Created by pepe on 2016/8/18 0018.
 */
public class FrameAnimAct extends AppCompatActivity {

    ImageView image_xml;
    ImageView image_java;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_frame);
        image_xml = (ImageView) findViewById(R.id.image_xml);
        image_java = (ImageView) findViewById(R.id.image_java);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //将动画资源文件设置为ImageView的背景
        image_xml.setBackgroundResource(R.drawable.anim_frame);
        //获取ImageView背景,此时已被编译成AnimationDrawable
        AnimationDrawable anim = (AnimationDrawable) image_xml.getBackground();
        //开始动画
        anim.start();

        /*// 通过逐帧动画的资源文件获得AnimationDrawable示例
        AnimationDrawable frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_frame);
        // 把AnimationDrawable设置为ImageView的背景
        image_xml.setBackgroundDrawable(frameAnim);
        frameAnim.start();*/

        runFrame(image_java);
    }

    public void runFrame(View view) {
        //完全编码实现的动画效果
        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 1; i <= 6; i++) {
            //根据资源名称和目录获取R.java中对应的资源ID
            int id = getResources().getIdentifier("anim_frame_flower" + i, "mipmap", getPackageName());
            //根据资源ID获取到Drawable对象
            Drawable drawable = getResources().getDrawable(id);
            //将此帧添加到AnimationDrawable中
            anim.addFrame(drawable, 500);
        }
        //设置为loop
        anim.setOneShot(false);
        //将动画设置为ImageView背景
        view.setBackgroundDrawable(anim);
        anim.start();   //开始动画
    }

}
