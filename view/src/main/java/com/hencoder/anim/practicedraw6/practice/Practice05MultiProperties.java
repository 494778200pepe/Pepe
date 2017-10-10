package com.hencoder.anim.practicedraw6.practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pepe.view.R;


public class Practice05MultiProperties extends ConstraintLayout {
    Button animateBt;
    ImageView imageView;
    int multiState = 0;

    public Practice05MultiProperties(Context context) {
        super(context);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05MultiProperties(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setScaleX(0);
        imageView.setScaleY(0);
        imageView.setAlpha(0f);
        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(multiState == 0){
                    imageView.animate().scaleX(1);
                    imageView.animate().scaleY(1);
                    imageView.animate().alpha(1);
                    imageView.animate().translationX(400);
                    imageView.animate().rotation(360);
                    multiState = 1;
                }else{
                    imageView.animate().scaleX(0);
                    imageView.animate().scaleY(0);
                    imageView.animate().alpha(0);
                    imageView.animate().translationX(0);
                    imageView.animate().rotation(0);
                    multiState = 0;
                }
                // TODO 在这里处理点击事件，同时对多个属性做动画
            }
        });
    }
}
