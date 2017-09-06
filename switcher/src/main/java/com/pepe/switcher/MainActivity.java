package com.pepe.switcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import static com.pepe.switcher.R.id.imageSwicher;

/**
 * 想要不停的切换，可以用定时器不停的setImageResource/setImageURI/setImageDrawable
 * TextSwitcher 类似
 * 参考博客：
 * Android_TextSwitcher和ImageSwitcher - teletian的专栏 - CSDN博客
 * http://blog.csdn.net/tianjf0514/article/details/7556487
 * TextSwitch 和 ImageSwitch源码分析 - weichao - CSDN博客
 * http://blog.csdn.net/o279642707/article/details/52291961
 */
public class MainActivity extends Activity implements ViewSwitcher.ViewFactory,
        View.OnTouchListener, View.OnClickListener {

    private ImageSwitcher imageSwitcher;

    // 图片数组
    private int[] arrayPictures = {R.mipmap.bg1, R.mipmap.bg2,
            R.mipmap.bg3, R.mipmap.bg4};
    // 要显示的图片在图片数组中的Index
    private int pictureIndex;
    // 左右滑动时手指按下的X坐标
    private float touchDownX;
    // 左右滑动时手指松开的X坐标
    private float touchUpX;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher) findViewById(imageSwicher);
        // 为ImageSwicher设置Factory，用来为ImageSwicher制造ImageView
        imageSwitcher.setFactory(this);
        // 设置ImageSwitcher左右滑动事件
        imageSwitcher.setOnTouchListener(this);
        imageSwitcher.setOnClickListener(this);
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(arrayPictures[pictureIndex]);
        return imageView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 取得左右滑动时手指按下的X坐标
            touchDownX = event.getX();
            return false;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 取得左右滑动时手指松开的X坐标
            touchUpX = event.getX();
            // 从左往右，看前一张
            if (touchUpX - touchDownX > 100) {
                // 取得当前要看的图片的index
                pictureIndex = pictureIndex == 0 ? arrayPictures.length - 1
                        : pictureIndex - 1;
                // 设置图片切换的动画
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.slide_in_left));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                        android.R.anim.slide_out_right));
                // 设置当前要看的图片
                imageSwitcher.setImageResource(arrayPictures[pictureIndex]);
                // 从右往左，看下一张
            } else if (touchDownX - touchUpX > 100) {
                // 取得当前要看的图片的index
                pictureIndex = pictureIndex == arrayPictures.length - 1 ? 0
                        : pictureIndex + 1;
                // 设置图片切换的动画
                // 由于Android没有提供slide_out_left和slide_in_right，所以仿照slide_in_left和slide_out_right编写了slide_out_left和slide_in_right
                imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.slide_out_left));
                imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                        R.anim.slide_in_right));
                // 设置当前要看的图片
                imageSwitcher.setImageResource(arrayPictures[pictureIndex]);
            }
            return false;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, GalleryActivity.class));
    }
}

