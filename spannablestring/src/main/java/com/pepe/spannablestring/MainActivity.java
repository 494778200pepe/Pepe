package com.pepe.spannablestring;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 也可以用 SpannableStringBuilder（有append方法）
 * 参考博客：
 * 用SpannableString打造绚丽多彩的文本显示效果 - 简书
 * http://www.jianshu.com/p/84067ad289d2
 * TextView使用SpannableString设置复合文本 - Hecker385 - 博客园
 * http://www.cnblogs.com/jisheng/archive/2013/01/10/2854088.html
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

//        Spanned.SPAN_INCLUSIVE_EXCLUSIVE 从起始下标到终了下标，包括起始下标
//        Spanned.SPAN_INCLUSIVE_INCLUSIVE 从起始下标到终了下标，同时包括起始下标和终了下标
//        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 从起始下标到终了下标，但都不包括起始下标和终了下标
//        Spanned.SPAN_EXCLUSIVE_INCLUSIVE 从起始下标到终了下标，包括终了下标

        // 不定项String的方法
//        String text2 = String.format(getResources().getString(R.string.main_detect_result), "dfds", 2);

        ll.addView(foregroundColorSpan());
        ll.addView(backgroundColorSpan());
        ll.addView(relativeSizeSpan());
        ll.addView(strikethroughSpan());
        ll.addView(underlineSpan());
        ll.addView(superscriptSpan());
        ll.addView(subscriptSpan());
        ll.addView(styleSpan());
        ll.addView(imageSpan());
        ll.addView(clickableSpan());
        ll.addView(URLSpan());
    }

    /**
     * 为文本设置前景色，效果和TextView的setTextColor()类似
     *
     * @return  TextView
     */
    public View foregroundColorSpan() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder("设置文字的前景色为淡蓝色");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(colorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        TextView textView = new TextView(this);
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 为文本设置背景色，效果和TextView的setBackground()类似
     *
     * @return TextView
     */
    public View backgroundColorSpan() {
        SpannableString spannableString = new SpannableString("设置文字的背景色为淡绿色");
        BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.parseColor("#AC00FF30"));
        spannableString.setSpan(colorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        TextView textView = new TextView(this);
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 设置文字相对大小，在TextView原有的文字大小的基础上，相对设置文字大小
     *
     * @return TextView
     */
    public View relativeSizeSpan() {
        SpannableString spannableString = new SpannableString("万丈高楼平地起");

        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.2f);
        RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan04 = new RelativeSizeSpan(1.8f);
        RelativeSizeSpan sizeSpan05 = new RelativeSizeSpan(1.6f);
        RelativeSizeSpan sizeSpan06 = new RelativeSizeSpan(1.4f);
        RelativeSizeSpan sizeSpan07 = new RelativeSizeSpan(1.2f);

        spannableString.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan02, 1, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan03, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan04, 3, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan05, 4, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan06, 5, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan07, 6, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 为文本设置中划线，也就是常说的删除线
     *
     * @return TextView
     */
    public View strikethroughSpan() {
        SpannableString spannableString = new SpannableString("为文字设置删除线");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 为文本设置下划线
     *
     * @return TextView
     */
    public View underlineSpan() {
        SpannableString spannableString = new SpannableString("为文字设置下划线");
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableString.setSpan(underlineSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 设置上标
     *
     * @return TextView
     */
    public View superscriptSpan() {
        SpannableString spannableString = new SpannableString("为文字设置上标");
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        spannableString.setSpan(superscriptSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 设置下标
     *
     * @return TextView
     */
    public View subscriptSpan() {
        SpannableString spannableString = new SpannableString("为文字设置下标");
        SubscriptSpan subscriptSpan = new SubscriptSpan();
        spannableString.setSpan(subscriptSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 为文字设置风格（粗体、斜体），和TextView属性textStyle类似
     *
     * @return TextView
     */
    public View styleSpan() {
        SpannableString spannableString = new SpannableString("为文字设置粗体、斜体风格");
        StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpan_I = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(styleSpan_B, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan_I, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
//        textView.setHighlightColor(Color.parseColor("#36969696"));//setHighlightColor方法则是控制点击是的背景色
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 设置文本图片
     *
     * @return TextView
     */
    public View imageSpan() {
        SpannableString spannableString = new SpannableString("在文本中添加表情（表情）");
        Drawable drawable = getResources().getDrawable(R.mipmap.a9c);
        drawable.setBounds(0, 0, 42, 42);
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
//        textView.setHighlightColor(Color.parseColor("#36969696"));//setHighlightColor方法则是控制点击是的背景色
        textView.setText(spannableString);
        return textView;
    }

    /**
     * 设置可点击的文本，设置这个属性的文本可以相应用户点击事件，至于点击事件用户可以自定义
     * 使用ClickableSpan的文本如果想真正实现点击作用，必须为TextView设置setMovementMethod方法，否则没有点击相应
     *
     * @return TextView
     */
    public View clickableSpan() {
        SpannableString spannableString = new SpannableString("为文字设置点击事件");
        MyClickableSpan clickableSpan = new MyClickableSpan("http://www.jianshu.com/users/dbae9ac95c78");
        spannableString.setSpan(clickableSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        //使用ClickableSpan的文本如果想真正实现点击作用，必须为TextView设置setMovementMethod方法，否则没有点击相应
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.parseColor("#36969696"));//setHighlightColor方法则是控制点击是的背景色
        textView.setText(spannableString);
        return textView;
    }

    class MyClickableSpan extends ClickableSpan {

        private String content;

        public MyClickableSpan(String content) {
            this.content = content;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            //setUnderlineText()控制是否让可点击文本显示下划线
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(MainActivity.this,"SpannableString",Toast.LENGTH_LONG).show();
        }
    }


    /**
     * 设置超链接文本
     *
     * @return TextView
     */
    public View URLSpan() {
        SpannableString spannableString = new SpannableString("为文字设置超链接");
        URLSpan urlSpan = new URLSpan("http://www.jianshu.com/users/dbae9ac95c78");
        spannableString.setSpan(urlSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        TextView textView = new TextView(this);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        //setHighlightColor方法则是控制点击是的背景色
        textView.setHighlightColor(Color.parseColor("#36969696"));
        textView.setText(spannableString);
        return textView;
    }


}
