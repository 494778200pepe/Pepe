package com.pepe.animation.Property;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.zitech.animationdemo.R;

/**
 * Created by "pepe" on 2016/8/21 0021.
 */
public class ValueAnimatorAct extends AppCompatActivity {

    ImageView image;
    private float mScreenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_value_animator);
        image = (ImageView) findViewById(R.id.image);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
    }

    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "ValueAnimator");    //添加选项
        menu.add(Menu.NONE, 1, 0, "自由落体");    //添加选项
        menu.add(Menu.NONE, 2, 0, "抛物线");    //添加选项
        menu.add(Menu.NONE, 3, 0, "淡出且删除");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                ValueAnimator animator0= ValueAnimator.ofFloat(0,100);
                animator0.setTarget(image);
                animator0.setDuration(1000).start();
                animator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Float value=(Float) animation.getAnimatedValue();
                        Log.d("pepe","value:"+value);
                    }
                });

                break;

            case 1://自由落体
                ValueAnimator animator1 = ValueAnimator.ofFloat(0, mScreenHeight
                        - image.getHeight());
                animator1.setTarget(image);
                animator1.setDuration(1000).start();
//      animator.setInterpolator(value)
                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        image.setTranslationY((Float) animation.getAnimatedValue());
                    }
                });

                break;
            case 2://抛物线
                ValueAnimator valueAnimator2 = new ValueAnimator();
                valueAnimator2.setDuration(3000);
                valueAnimator2.setObjectValues(new PointF(0, 0));
                valueAnimator2.setInterpolator(new LinearInterpolator());
                //setEvaluator和在ofObject中传入TypeEvaluator是一样的
                valueAnimator2.setEvaluator(new TypeEvaluator<PointF>()
                {
                    // fraction = t / duration
                    @Override
                    public PointF evaluate(float fraction, PointF startValue,
                                           PointF endValue)
                    {
                        Log.e("pepe", fraction * 3 + "");
                        // x方向200px/s ，则y方向0.5 * 10 * t
                        PointF point = new PointF();
                        point.x = 200 * fraction * 3;
                        point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                        return point;
                    }
                });

                valueAnimator2.start();
                valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        PointF point = (PointF) animation.getAnimatedValue();
                        image.setX(point.x);
                        image.setY(point.y);

                    }
                });
                break; 
            case 3://淡出且删除
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(image, "alpha", 0.5f);

                anim3.addListener(new Animator.AnimatorListener()
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
                anim3.start();
                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
