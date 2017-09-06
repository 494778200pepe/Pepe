package com.pepe.kenburns;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.animation.AnimatorProxy;

import java.util.Random;

/**
 * 参考博客：
 * 附加Ken Burns特效的幻灯片 - 黄飞的专栏 - CSDN博客
 * http://blog.csdn.net/tw19911005/article/details/49669203
 * 一个实现Ken Burns特效的控件：
 * flavioarfaria/KenBurnsView: Android ImageViews animated by Ken Burns Effect
 * https://github.com/flavioarfaria/KenBurnsView
 */
public class MainActivity extends Activity implements Animator.AnimatorListener {
    private static final int ANIM_COUNT = 4;
    private static final int[] PHOTOS = new int[] {R.mipmap.bg1, R.mipmap.bg2,
            R.mipmap.bg3, R.mipmap.bg4};

    private FrameLayout mContainer;
    private ImageView mImageView;
    private Random mRandom = new Random();
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建布局容器
        mContainer = new FrameLayout(this);
        mContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mImageView = createNewView();
        mContainer.addView(mImageView);

        setContentView(mContainer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextAnimation();
    }

    /**
     * 创建新的ImageView
     */
    private ImageView createNewView() {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //设置要显示的图片并设置下一个要显示的图片的索引
        imageView.setImageResource(PHOTOS[mIndex]);
        mIndex = ++mIndex % PHOTOS.length;
        return imageView;
    }

    /**
     * 该方法负责设置动画并启动动画
     */
    private void nextAnimation() {
        AnimatorSet anim = new AnimatorSet();
        //产生0-4的随机整数
        int index = mRandom.nextInt(ANIM_COUNT);//随机选择动画

        switch (index) {
            case 0://缩放动画
                anim.playTogether(ObjectAnimator.ofFloat(mImageView, "scaleX", 1.5f, 1f),
                        ObjectAnimator.ofFloat(mImageView, "scaleY", 1.5f, 1f));
                break;
            case 1:
                anim.playTogether(ObjectAnimator.ofFloat(mImageView, "scaleX", 1f, 1.5f),
                        ObjectAnimator.ofFloat(mImageView, "scaleY", 1f, 1.5f));
                break;
            case 2://位移动画
                /**
                 * AnimatorProxy是定义在Nine Old Androids库中的类，用于修改View的属性。这个新的动画框架的
                 * 基础是：视图的属性随着时间的推移可以改变。之所以使用AnimatorProxy，是因为在Android3.0以
                 * 下版本，有些属性没有getters/setters方法。
                 */
                AnimatorProxy.wrap(mImageView).setScaleX(1.5f);
                AnimatorProxy.wrap(mImageView).setScaleY(1.5f);
                anim.playTogether(ObjectAnimator.ofFloat(mImageView, "translationY", 80f, 0f));
                break;
            case 3:
            default:
                AnimatorProxy.wrap(mImageView).setScaleX(1.5f);
                AnimatorProxy.wrap(mImageView).setScaleY(1.5f);
                anim.playTogether(ObjectAnimator.ofFloat(mImageView, "translationX", 0f, 40f));
                break;
        }

        //设置动画持续时间，设置动画监听器，启动动画
        anim.setDuration(3000);
        anim.addListener(this);
        anim.start();
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        //动画结束时，从布局中移除之前的View，并添加新的View，而后开始显示下一个动画
        mContainer.removeView(mImageView);
        mImageView = createNewView();
        mContainer.addView(mImageView);
        nextAnimation();
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}