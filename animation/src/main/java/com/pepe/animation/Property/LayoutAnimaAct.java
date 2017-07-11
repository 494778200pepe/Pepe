package com.pepe.animation.Property;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;

import com.zitech.animationdemo.R;

/**
 * Created by pepe on 2016/8/21 0021.
 */
public class LayoutAnimaAct extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener
{
    private ViewGroup viewGroup;
    private GridLayout mGridLayout;
    private int mVal;
    private LayoutTransition mTransition;

    private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_layout_anim);
        viewGroup = (ViewGroup) findViewById(R.id.id_container);

        mAppear = (CheckBox) findViewById(R.id.id_appear);
        mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
        mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
        mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);

        mAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);
        mDisAppear.setOnCheckedChangeListener(this);
        mChangeDisAppear.setOnCheckedChangeListener(this);

        // 创建一个GridLayout
        mGridLayout = new GridLayout(this);
        // 设置每列5个按钮
        mGridLayout.setColumnCount(5);
        // 添加到布局中
        viewGroup.addView(mGridLayout);
        // 默认动画全部开启
        mTransition = new LayoutTransition();
        mTransition.setAnimator(LayoutTransition.APPEARING, (mAppear
                .isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1)
                : null));
        mGridLayout.setLayoutTransition(mTransition);

    }
        //TODO  给ViewGroup添加LayoutTransition动画
//    我们可以通过如下方式使用系统提供的默认ViewGroup的LayoutTransition动画：
//    android:animateLayoutChanges=”true”
//    在ViewGroup添加如上xml属性默认是没有任何动画效果的，因为前面说了，该动画针对于ViewGroup内部东东发生改变时才有效，所以当我们设置如上属性然后调运ViewGroup的addView、removeView方法时就能看见系统默认的动画效果了。
//    还有一种就是通过如下方式设置：
//    android:layoutAnimation=”@anim/customer_anim”
//    通过这种方式就能实现很多吊炸天的动画。

        //TODO  过渡的类型一共有四种：
//    LayoutTransition.APPEARING 当一个View在ViewGroup中出现时，对此View设置的动画
//    LayoutTransition.CHANGE_APPEARING 当一个View在ViewGroup中出现时，对此View对其他View位置造成影响，对其他View设置的动画
//    LayoutTransition.DISAPPEARING  当一个View在ViewGroup中消失时，对此View设置的动画
//    LayoutTransition.CHANGE_DISAPPEARING 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响，对其他View设置的动画
//    LayoutTransition.CHANGE 不是由于View出现或消失造成对其他View位置造成影响，然后对其他View设置的动画。
//    注意动画到底设置在谁身上，此View还是其他View。
    /**
     * 添加按钮
     *
     * @param view
     */
    public void addBtn(View view)
    {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                mGridLayout.removeView(button);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        mTransition = new LayoutTransition();
        mTransition.setAnimator(LayoutTransition.APPEARING, (mAppear
                .isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1)
                : null));

        //TODO 这里可以自定义动画
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "scaleX", 0, 1);
        mTransition.setAnimator(LayoutTransition.APPEARING, anim);
        mTransition
                .setAnimator(
                        LayoutTransition.CHANGE_APPEARING,
                        (mChangeAppear.isChecked() ? mTransition
                                .getAnimator(LayoutTransition.CHANGE_APPEARING)
                                : null));
        mTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                (mDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
        mTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                (mChangeDisAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
                        : null));
        mGridLayout.setLayoutTransition(mTransition);
    }
}