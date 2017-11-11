package com.pepe.animation.Vector;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pepe.animation.R;


/**
 * Created by pepe on 2016/9/12.
 */
public class VectorDemo1Act extends Activity implements View.OnClickListener {
    private ImageView imageView1,imageView2,imageView3,imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vector_demo1);
        imageView1 = (ImageView) findViewById(R.id.image_xml);
        imageView1.setOnClickListener(this);
        imageView2 = (ImageView) findViewById(R.id.image_java);
        imageView2.setOnClickListener(this);
        imageView3 = (ImageView) findViewById(R.id.image3);
        imageView3.setOnClickListener(this);
        imageView4 = (ImageView) findViewById(R.id.image4);
        imageView4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        animate(v);
    }

    private void animate(View v) {
        Drawable drawable = ((ImageView)v).getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }
}
