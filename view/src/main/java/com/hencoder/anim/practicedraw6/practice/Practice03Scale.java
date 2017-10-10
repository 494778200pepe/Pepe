package com.hencoder.anim.practicedraw6.practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pepe.view.R;


public class Practice03Scale extends RelativeLayout {
    Button animateBt;
    ImageView imageView;
    int scaleState = 0;

    public Practice03Scale(Context context) {
        super(context);
    }

    public Practice03Scale(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice03Scale(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animateBt = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (scaleState == 0) {
                    imageView.animate().scaleX(1.5f);
                } else if (scaleState == 1) {
                    imageView.animate().scaleX(1f);
                } else if (scaleState == 2) {
                    imageView.animate().scaleY(0.6f);
                } else if (scaleState == 3) {
                    imageView.animate().scaleY(1f);
                }
                scaleState++;
                if (scaleState == 4) scaleState = 0;
                // TODO 在这里处理点击事件，通过 View.animate().scaleX/Y() 来让 View 放缩
            }
        });
    }
}
