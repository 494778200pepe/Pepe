package com.pepe.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hencoder.practicedraw1.Practice1Activity;
import com.pepe.view.paint.PaintAct;
import com.pepe.view.touch.EventAct;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(Practice1Activity.class, "Practice1"),
                new ItemActivity(Practice1Activity.class, "Practice2"),
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
