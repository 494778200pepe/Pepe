package com.hencoder.anim;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hencoder.anim.practicedraw6.Practice6Activity;
import com.hencoder.anim.practicedraw7.Practice7Activity;
import com.pepe.ItemActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/6.
 */

public class HenCoderAnimAct extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(Practice6Activity.class, "Practice6"),
                new ItemActivity(Practice7Activity.class, "Practice7")
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
                startActivity(new Intent(HenCoderAnimAct.this, act.activityClass));
            }
        });
    }
}