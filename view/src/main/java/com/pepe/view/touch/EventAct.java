package com.pepe.view.touch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pepe.view.R;

/**
 * Created by wang on 2017/7/12.
 */

public class EventAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_event);

    }


    //ImageView_false
//    D/pepe    (22065): dispatchTouchEvent----ACTION_DOWN
//    D/pepe    (22065): onTouchListener----img----ACTION_DOWN
//    D/pepe    (22065): onTouchEvent----ACTION_DOWN
//    D/pepe    (22065): dispatchTouchEvent----ACTION_UP
//    D/pepe    (22065): onTouchEvent----ACTION_UP

//    D/pepe    (22065): dispatchTouchEvent----ACTION_DOWN
//    D/pepe    (22065): onTouchListener----img----ACTION_DOWN
//    D/pepe    (22065): onTouchEvent----ACTION_DOWN

//    D/pepe    (22065): dispatchTouchEvent----ACTION_MOVE
//    D/pepe    (22065): onTouchEvent----ACTION_MOVE

//    D/pepe    (22065): dispatchTouchEvent----ACTION_UP
//    D/pepe    (22065): onTouchEvent----ACTION_UP

    //问题1：为啥down有 onTouchListener，move和up就没有呢？
    //问题2：是否跟onTouchListener的返回值有关系？
    //ImageView_true
//    D/pepe    (27262): dispatchTouchEvent----ACTION_DOWN
//    D/pepe    (27262): onTouchListener----img----ACTION_DOWN

//    D/pepe    (27262): dispatchTouchEvent----ACTION_MOVE
//    D/pepe    (27262): onTouchListener----img----ACTION_MOVE

//    D/pepe    (27262): dispatchTouchEvent----ACTION_UP
//    D/pepe    (27262): onTouchListener----img----ACTION_UP

}
