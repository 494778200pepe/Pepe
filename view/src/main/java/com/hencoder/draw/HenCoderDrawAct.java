package com.hencoder.draw;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hencoder.draw.practicedraw1.Practice1Activity;
import com.hencoder.draw.practicedraw2.Practice2Activity;
import com.hencoder.draw.practicedraw3.Practice3Activity;
import com.hencoder.draw.practicedraw4.Practice4Activity;
import com.hencoder.draw.practicedraw5.Practice5Activity;
import com.pepe.ItemActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/6.
 */

public class HenCoderDrawAct extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(Practice1Activity.class, "Practice1"),
                new ItemActivity(Practice2Activity.class, "Practice2"),
                new ItemActivity(Practice3Activity.class, "Practice3"),
                new ItemActivity(Practice4Activity.class, "Practice4"),
                new ItemActivity(Practice5Activity.class, "Practice5")
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
                startActivity(new Intent(HenCoderDrawAct.this, act.activityClass));
            }
        });
    }
}