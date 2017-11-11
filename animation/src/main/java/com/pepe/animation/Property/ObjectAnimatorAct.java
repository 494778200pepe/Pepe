package com.pepe.animation.Property;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/8/21 0021.
 */
public class ObjectAnimatorAct extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_object_animator);
        image = (ImageView) findViewById(R.id.image_xml);
    }

    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "translationX");    //添加选项
        menu.add(Menu.NONE, 1, 0, "translationY");    //添加选项
        menu.add(Menu.NONE, 2, 0, "rotationX");    //添加选项
        menu.add(Menu.NONE, 3, 0, "rotationY");    //添加选项
        menu.add(Menu.NONE, 4, 0, "rotation");    //添加选项
        menu.add(Menu.NONE, 5, 0, "scaleX");    //添加选项
        menu.add(Menu.NONE, 6, 0, "scaleY");    //添加选项
        menu.add(Menu.NONE, 7, 0, "pivotX");    //添加选项
        menu.add(Menu.NONE, 8, 0, "pivotY");    //添加选项
        menu.add(Menu.NONE, 9, 0, "X");    //添加选项
        menu.add(Menu.NONE, 10, 0, "Y");    //添加选项
        menu.add(Menu.NONE, 11, 0, "alpha");    //添加选项
        menu.add(Menu.NONE, 12, 0, "set");    //添加选项
        menu.add(Menu.NONE, 13, 0, "AnimatorListener");    //添加选项
        menu.add(Menu.NONE, 14, 0, "AnimatorListenerAdapter");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            ofFloat()方法的第一个参数表示动画操作的对象（可以是任意对象），
//            第二个参数表示操作对象的属性名字（只要是对象有的属性都可以），
//            第三个参数之后就是动画过渡值。当然过度值可以有一个到N个，
//            如果是一个值的话默认这个值是动画过渡值的结束值。如果有N个值，动画就在这N个值之间过渡。
            case 0:
                ObjectAnimator.ofFloat(image, "translationX", 0, 350, 0).setDuration(2500).start();
                break;
            case 1:
                ObjectAnimator.ofFloat(image, "translationY", 100, 350, 0).setDuration(2500).start();
                break;
            case 2:
                ObjectAnimator.ofFloat(image,"rotationX",0, 90,0).setDuration(2500).start();
                break;
            case 3:
                ObjectAnimator.ofFloat(image,"rotationY",0, 90,0).setDuration(2500).start();
                break;
            case 4:
                ObjectAnimator.ofFloat(image,"rotation",0.0f, 90.0f,0.0F).setDuration(4000).start();
                break;
            case 5:
                ObjectAnimator.ofFloat(image,"scaleX",1.0f, 2.0f,1.0f).setDuration(2500).start();
                break;
            case 6:
                ObjectAnimator.ofFloat(image,"scaleY",1.0f, 2.0f,1.0f).setDuration(2500).start();
                break;
            case 7:
                ObjectAnimator.ofFloat(image,"pivotX",0,300,0).setDuration(4000).start();
                ObjectAnimator.ofFloat(image,"rotation",0.0f, 90.0f,0.0F).setDuration(4000).start();
                break;
            case 8:
                ObjectAnimator.ofFloat(image,"pivotY",300).setDuration(4000).start();
                ObjectAnimator.ofFloat(image,"rotation",0.0f, 90.0f,0.0F).setDuration(4000).start();
                break;
            case 9:
                ObjectAnimator.ofFloat(image,"X",0,300,0).setDuration(3000).start();
                break;
            case 10:
                ObjectAnimator.ofFloat(image,"Y",0,300,0).setDuration(3000).start();
                break;
            case 11:
                ObjectAnimator.ofFloat(image,"alpha",1.0f, 0.3f, 1.0f).setDuration(2500).start();
                break;
            case 12://一个改三个
                ObjectAnimator anim12 = ObjectAnimator//
                        .ofFloat(image, "zhy", 1.0F,  0.0F)//
                        .setDuration(500);//
                anim12.start();
                anim12.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        float cVal = (Float) animation.getAnimatedValue();
                        image.setAlpha(cVal);
                        image.setScaleX(cVal);
                        image.setScaleY(cVal);
                    }
                });
                break;
            case 13://动画过程监听
                ObjectAnimator anim13 = ObjectAnimator.ofFloat(image, "alpha", 0.5f);

                anim13.addListener(new Animator.AnimatorListener()
                {

                    @Override
                    public void onAnimationStart(Animator animation)
                    {
                        Log.e("pepe", "onAnimationStart");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation)
                    {
                        // TODO Auto-generated method stub
                        Log.e("pepe", "onAnimationRepeat");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        Log.e("pepe", "onAnimationEnd");
                        ViewGroup parent = (ViewGroup) image.getParent();
                        if (parent != null)
                            parent.removeView(image);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation)
                    {
                        // TODO Auto-generated method stub
                        Log.e("pepe", "onAnimationCancel");
                    }
                });
                anim13.start();
                break;
            case 14://简单版监听
                ObjectAnimator anim14 = ObjectAnimator.ofFloat(image, "alpha", 0.5f);
                anim14.addListener(new AnimatorListenerAdapter()
                {
                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        Log.e("pepe", "onAnimationEnd");
                        ViewGroup parent = (ViewGroup) image.getParent();
                        if (parent != null)
                            parent.removeView(image);
                    }
                });
                anim14.start();
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}