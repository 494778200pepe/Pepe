package com.pepe.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.github.GitHubAct;
import com.hencoder.anim.practicedraw6.Practice6Activity;
import com.hencoder.anim.practicedraw7.Practice7Activity;
import com.hencoder.draw.practicedraw1.Practice1Activity;
import com.hencoder.draw.practicedraw2.Practice2Activity;
import com.hencoder.draw.practicedraw3.Practice3Activity;
import com.hencoder.draw.practicedraw4.Practice4Activity;
import com.hencoder.draw.practicedraw5.Practice5Activity;
import com.pepe.view.camera.CameraAct;
import com.pepe.view.paint.PaintAct;
import com.pepe.view.touch.EventAct;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(GitHubAct.class, "GitHub"),
                new ItemActivity(Practice1Activity.class, "Practice1"),
                new ItemActivity(Practice2Activity.class, "Practice2"),
                new ItemActivity(Practice3Activity.class, "Practice3"),
                new ItemActivity(Practice4Activity.class, "Practice4"),
                new ItemActivity(Practice5Activity.class, "Practice5"),
                new ItemActivity(Practice6Activity.class, "Practice6"),
                new ItemActivity(Practice7Activity.class, "Practice7"),
                new ItemActivity(CameraAct.class, "Camera"),
                new ItemActivity(PaintAct.class, "Paint"),
                new ItemActivity(PaintAct.class, "Canvas"),
                new ItemActivity(PaintAct.class, "Path"),
                new ItemActivity(EventAct.class, "Event")
        );
        ArrayAdapter<ItemActivity> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                acts);
        getListView().setAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ItemActivity act = acts.get(position);
                startActivity(new Intent(MainActivity.this, act.activityClass));
            }
        });
    }
}
