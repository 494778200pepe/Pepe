package com.pepe.tool;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 *自定义的Toast类
 */
public class MyToast {
    public static void myToastShow(Context context, int imageResId, String content, int duration){
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setGravity(Gravity.BOTTOM, 0, 110);

        LinearLayout toastLayout = new LinearLayout(context);
        toastLayout.setOrientation(LinearLayout.HORIZONTAL);
        toastLayout.setGravity(Gravity.CENTER_VERTICAL);
//        //设置Toast的图标
//        ImageView imageView  = new ImageView(context);
//        imageView.setImageResource(imageResId);
//        toastLayout.addView(imageView);
        //设置Toast的Text
        TextView tv_content = new TextView(context);
        tv_content.setText(content);
        tv_content.setTextSize(30);
        tv_content.setTextColor(Color.WHITE);
        tv_content.setGravity(Gravity.CENTER_HORIZONTAL);
//        tv_content.setBackgroundColor(Color.TRANSPARENT);

        toastLayout.addView(tv_content);

        toast.setView(toastLayout);
        toast.show();

    }
}
