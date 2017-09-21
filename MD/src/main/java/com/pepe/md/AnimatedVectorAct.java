package com.pepe.md;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.pepe.md.animatedvector.ExampleActivity;
import com.pepe.md.animatedvector.FillInHeartActivity;
import com.pepe.md.animatedvector.PathMorphActivity;
import com.pepe.md.animatedvector.RotateActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pepe on 2016/9/14 0014.
 * chiuki/animated-vector-drawable: Android AnimatedVectorDrawable examples
 * https://github.com/chiuki/animated-vector-drawable
 */
public class AnimatedVectorAct extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Demo> demos = Arrays.asList(
                new Demo(this, ExampleActivity.class, R.string.example_from_documentation),
                new Demo(this, RotateActivity.class, R.string.clock),
                new Demo(this, PathMorphActivity.class, R.string.smiling_face),
                new Demo(this, FillInHeartActivity.class, R.string.fill_in_heart)
        );
        ArrayAdapter<Demo> adapter = new ArrayAdapter<Demo>(
                this,
                android.R.layout.simple_list_item_1,
                demos);
        getListView().setAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Demo demo = demos.get(position);
                startActivity(new Intent(AnimatedVectorAct.this, demo.activityClass));
            }
        });
    }

    public static class Demo {
        public final Class<?> activityClass;
        public final String title;

        public Demo(Context context, Class<?> activityClass, int titleId) {
            this.activityClass = activityClass;
            this.title = context.getString(titleId);
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
}
