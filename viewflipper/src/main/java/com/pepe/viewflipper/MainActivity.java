package com.pepe.viewflipper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
    private Context mContext=MainActivity.this;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    bindNotices();
                    break;
                case -1:
                    break;
            }
        }
    };

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        bindLinearLayout();
        Message msg = new Message();
        msg.what = 1;
        mHandler.sendMessageDelayed(msg, 3000);

    }
    /**
     * 初始化自定义的布局
     */
    public void bindLinearLayout() {
        viewFlipper = (ViewFlipper)findViewById(R.id.flipper_scrollTitle);
//        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
//        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        viewFlipper.startFlipping();
        View v = viewFlipper.getCurrentView();
    }
    /**
     *  网络请求后返回公告内容进行适配
     */
    protected void bindNotices() {
        // TODO Auto-generated method stub
        viewFlipper.removeAllViews();
        int i = 1;
        while(i<6){
            LinearLayout v=(LinearLayout) View.inflate(mContext, R.layout.listitem_announce, null);

            TextView tv_username=(TextView) v.findViewById(R.id.listitem_tv_username);
            TextView tv_investnumber=(TextView) v.findViewById(R.id.listitem_tv_investnumber);
            TextView tv_benefitnumber=(TextView) v.findViewById(R.id.listitem_tv_benefitnumber);
            String username = "张三李四"+i;
            tv_username.setText(username.substring(0, 1)
                    + "****"
                    + username.substring(username.length() - 1,
                    username.length()));
            tv_investnumber.setText(String.valueOf(i*100));
            tv_benefitnumber.setText(String.valueOf(i*0.1));
            v.setGravity(Gravity.CENTER_VERTICAL);
            viewFlipper.addView(v);
            i++;
        }
    }
}