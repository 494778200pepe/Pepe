package com.pepe.animation.Vector;

import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.zitech.animationdemo.R;

/**
 * Created by pepe on 2016/9/13.
 */
public class VectorDemo3Act extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vector_demo3);

        final PathView pathView = (PathView) findViewById(R.id.pathView);
//        final Path path = makeConvexArrow(50, 100);
//        pathView.setPath(path);
        pathView.setFillAfter(true);
        pathView.useNaturalColors();
        pathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.getPathAnimator().
                        delay(100).
                        duration(1500).
                        interpolator(new AccelerateDecelerateInterpolator()).
                        start();
            }
        });
    }

    private Path makeConvexArrow(float length, float height) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(length / 4f, 0.0f);
        path.lineTo(length, height / 2.0f);
        path.lineTo(length / 4f, height);
        path.lineTo(0.0f, height);
        path.lineTo(length * 3f / 4f, height / 2f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        return path;
    }

}
