package com.hencoder;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hencoder.anim.HenCoderAnimAct;
import com.hencoder.draw.HenCoderDrawAct;
import com.hencoder.layout.HenCoderLayoutAct;
import com.hencoder.task.HenCoderTaskAct;
import com.pepe.view.ItemActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/6.
 */

public class HenCoderAct extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(HenCoderDrawAct.class, "Draw"),
                new ItemActivity(HenCoderAnimAct.class, "Anim"),
                new ItemActivity(HenCoderTaskAct.class, "Task"),
                new ItemActivity(HenCoderLayoutAct.class, "Layout")
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
                startActivity(new Intent(HenCoderAct.this, act.activityClass));
            }
        });
    }
}
