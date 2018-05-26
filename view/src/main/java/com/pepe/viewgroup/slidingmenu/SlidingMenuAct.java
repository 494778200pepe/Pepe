package com.pepe.viewgroup.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pepe.view.R;

/**
 * @author wang
 * @date 2017/11/28.
 */

public class SlidingMenuAct extends Activity {
    private SlidingMenu sliding_menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pepe_viewgroup_sliding_menu);
        sliding_menu = (SlidingMenu) findViewById(R.id.sliding_menu);
    }

    public void toggle(View view) {
        sliding_menu.toggle();
    }

}
