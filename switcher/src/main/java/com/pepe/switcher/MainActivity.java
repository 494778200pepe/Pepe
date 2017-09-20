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
        Intent intent = new Intent(this, GalleryActivity.class);
        // 如果设置这个标志，新的Activity就不会在历史栈中保存。用户一旦离开，这个Activity就会finish掉。也可以使用noHistory属性设置。
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        // 设置这个标志之后，如果被启动的Activity已经在栈顶，那它就不会被再次启动。
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // 设置这个标志可以为待启动的Activity创建一个新的任务。一个任务（从启动它的Activity到任务中的下一个Activity）就是用户可以跳转到的Activity的原子群。
        // 任务可以在前台与后台之间切换；在某一特定任务之中的所有Activity一直会保持同样的顺序。
        // 这个标志通常被用来呈现一种"laucher"类型的行为：为用户提供一个可单独解决的事情列表，完全独立于启动他们的Activity之外运行。
        // 使用这个标志时，如果有一个任务已经运行了你要启动的Activity，那就不会在创建新的Activity，而是将现有的任务保持之前的状态直接唤到前台。
        // 参见FLAG_ACTIVITY_MULTIPLE_TASK这个标志，可以禁用掉这个行为。
        // 这个标志不能在调用者向待启动Activity请求返回结果时使用。
        // 注意：假设A启动B，如果要让B在新的task中创建，要求这两个Activity的taskAffinity不同。
        // 也就是说，设置了这个标志后，新启动的activity并非就一定在新的task中创建，如果A和B在属于同一个package，而且都是使用默认的taskAffinity，那B还是会在A的task中被创建。
        // 所以，只有A和B的taskAffinity不同时，设置了这个标志才会使B被创建到新的task。
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 除非你实现了自己的顶级应用启动器，否则不要使用这个标志。与 FLAG_ACTIVITY_NEW_TASK 一起使用可以不再把已存在的任务唤起到前台。
        // 当被设置时，系统总会为Intent的Activity启动一个新的task，而不管是否已经有已存在的任务在做同样的事情。
        // 因为默认系统不包含图形化的任务管理功能，所以除非你给用户提供了返回到已启动任务的方法，否则就不要用这个标志。
        // 如果FLAG_ACTIVITY_NEW_TASK没有设置，则这个标志也被忽略。
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        // 如果设置了这个标志，而且被启动的Activity如果已经在运行，那这个Activity会被调到栈顶。
        // 比如，一个任务中有4个Activity：A，B，C，D。
        // 如果D调用了startActivity() 来启动B时使用了这个标志，那B就会被调到历史栈的栈顶，结果顺序：A，C，D，B，否则顺序会是：A，B，C，D，B。
        // 如果使用了标志 FLAG_ACTIVITY_CLEAR_TOP，那这个FLAG_ACTIVITY_REORDER_TO_FRONT标志会被忽略。
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // 如果Activity A 在启动 Activity B时设置了这个标志，那A的答复目标目标会传递给B，这样一来B就可以通过调用setResult(int) 将返回结果返回给A的答复目标。
        // 简单如下：
        // O ----startActivityForResult()----> A ----FLAG_ACTIVITY_FORWARD_RESULT----> B
        // A的答复目标是O，如果A在启动B时使用了这个标志，A就会把答复目标O的信息传递给B，以便B将O作为它的答复目标。
        // 此时B调用setResult()时的结果信息都会传递给O，而不会给A。并且此时在A中调用setResult()的内容不会生效。我还没发现使A中setResult()生效的方法。
        // 注意：这个标志不能与startActivityForResult()一起使用。
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        // 如果启动Activity时设置了这个标志，那当前这个 Activity 不会被当作顶部的 Activity 来判断是否之后新Intent应该被传给栈顶Activity而不是启动一个新的Activity。
        // 之前一个的Activity会被当作栈顶，假定当前的Acitvity会立即自己finish掉。
        // 即 A---> B --->C，若B启动C时用了这个标志位，那在启动时B并不会被当作栈顶的Activity，而是用A做栈顶来启动C。此过程中B充当一个跳转页面。
        // 典型的场景是在应用选择页面，如果在文本中点击一个网址要跳转到浏览器，而系统中又装了不止一个浏览器应用，此时会弹出应用选择页面。
        // 在应用选择页面选择某一款浏览器启动时，就会用到这个Flag。然后应用选择页面将自己finish，以保证从浏览器返回时不会在回到选择页面。
        // 经常与FLAG_ACTIVITY_FORWARD_RESULT 一起使用。
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        // 如果设置这个标志，这个Activity就不会在近期任务中显示。
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        // 通常在应用代码中不需要设置这个FLAG，当launchMode为singleTask时系统会默认设置这个标志。
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        //
        intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        // 这个标志通常情况下不会通过应用的代码来设置，而是在通过最近任务启动activity时由系统设置的。
        intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        // 设置这个标志意味着在activity栈中做一个标记，在Task重置的时候栈就把从标记往上的activity都清除。
        // 也就是说，下次这个Task被通过FLAG_ACTIVITY_RESET_TASK_IF_NEEDED调到前台时（通常是由于用户从桌面重新启动），这个activity和它之上的activity都会被finish掉，
        // 这样用户就不会再回到他们，而是直接回到在它们之前的activity。
        // 这在应用切换时非常有用。比如，Email应用会需要查看附件，就要调用查看图片的Activity来显示，那这个查看图片的Activity就会成为Email应用任务里的一部分。
        // 但是，如果用户离开了Email的任务，过了一会儿由通过Home来选择Email应用，我们会希望它回到查看邮件会话的页面，而不是浏览图片附件的页面，不然就感觉太诡异了。
        // 如果在启动查看图片Activity时设置了这个标志，那这个Activity及由它启动的Activity在下一次用户返回邮件时都会被清除。
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        //
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        // 如果设置了这个标志，可以在避免用户离开当前Activity时回调到 onUserLeaveHint().
        // 通常，Activity可以通过这个回调表明有明确的用户行为将当前activity切出前台。
        // 这个回调标记了activity生命周期中的一个恰当的点，可以用来“在用户看过通知之后”将它们清除，如闪烁LED灯。
        // 如果Activity是由非用户驱动的事件（如电话呼入或闹钟响铃）启动的，那这个标志就应该被传入Context.startActivity，以确保被打断的activity不会认为用户已经看过了通知。
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        // 任务中的Activity顺序重排
        // 如果设置了这个标志，而且被启动的Activity如果已经在运行，那这个Activity会被调到栈顶。
        // 比如，一个任务中有4个Activity：A，B，C，D。如果D调用了startActivity() 来启动B时使用了这个标志，那B就会被调到历史栈的栈顶，结
        // 果顺序：A，C，D，B，否则顺序会是：A，B，C，D，B。 如果使用了标志 FLAG_ACTIVITY_CLEAR_TOP，那这个FLAG_ACTIVITY_REORDER_TO_FRONT标志会被忽略。
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        // 禁用掉系统默认的Activity切换动画
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        // 清空任务标志
        // 如果Intent中设置了这个标志，会导致含有待启动Activity的Task在Activity被启动前清空。
        // 也就是说，这个Activity会成为一个新的root，并且所有旧的activity都被finish掉。
        // 这个标志只能与FLAG_ACTIVITY_NEW_TASK 一起使用。
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // 直接返回桌面
        // 这个标志可以将一个新启动的任务置于当前的home任务(home activity task)之上（如果有的话）。也就是说，在任务中按back键总是会回到home界面，而不是回到他们之前看到的activity。这个标志只能与FLAG_ACTIVITY_NEW_TASK标志一起用。
        // 比如，A->B->C->D，如果在C启动D的时候设置了这个标志，那在D中按Back键则是直接回到桌面，而不是C。
        // 注意：
        // 只有D是在新的task中被创建时（也就是D的launchMode是singleInstance时，
        // 或者是给D指定了与C不同的taskAffinity并且加了FLAG_ACTIVITY_NEW_TASK标志时），使用 FLAG_ACTIVITY_TASK_ON_HOME标志才会生效。
        // 感觉实际使用效果和用 FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK 的效果一样。
        intent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        //
        intent.addFlags(Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS);
        //
        intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);

        startActivity(new Intent(this, GalleryActivity.class));
    }
}

