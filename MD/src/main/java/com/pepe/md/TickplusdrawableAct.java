package com.pepe.md;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.pepe.md.Tickplusdrawable.TickPlusDrawable;


/**
 * Created by pepe on 2016/9/14.
 * flavienlaurent/tickplusdrawable: I'm not a Gif maker, I'm a developer
 * https://github.com/flavienlaurent/tickplusdrawable
 */
public class TickplusdrawableAct extends Activity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_md_tickplus_drawable);

        View view = findViewById(R.id.view);

        final TickPlusDrawable tickPlusDrawable = new TickPlusDrawable(getResources().getDimensionPixelSize(R.dimen.stroke_width), getResources().getColor(android.R.color.holo_blue_dark), Color.WHITE);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(tickPlusDrawable);
        } else {
            view.setBackground(tickPlusDrawable);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickPlusDrawable.toggle();
            }
        });
    }
}
