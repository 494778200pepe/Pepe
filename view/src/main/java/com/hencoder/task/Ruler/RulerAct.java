package com.hencoder.task.Ruler;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pepe.view.R;

/**
 * @author wang
 * @date 2017/10/17
 */

public class RulerAct extends Activity implements RulerView.DegreeChangeListener {
    DegreeView degreeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //720*1280
        int screenWidth = UIUtil.getScreenWidth(this);
        int screenHeight = UIUtil.getScreenHeight(this);
        Log.d("pepe", "screenWidth = " + screenWidth);
        Log.d("pepe", "screenHeight = " + screenHeight);
        setContentView(R.layout.act_hencoder_task_ruler);
        RulerView rulerView = (RulerView) findViewById(R.id.rulerView);
        degreeView = (DegreeView) findViewById(R.id.degreeView);
        rulerView.setDegreeChangeListener(this);

    }

    @Override
    public void degreeChange(String degree) {
        degreeView.setDegree(degree);
    }
}
