package com.pepe.anim.tween;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.pepe.anim.custom.CustomAnimAct;
import com.pepe.view.ItemActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/11.
 */

public class TweenAnimAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(AlphaAnimAct.class, "Alpha"),
                new ItemActivity(TranslateAnimAct.class, "Translate"),
                new ItemActivity(ScaleAnimAct.class, "Scale"),
                new ItemActivity(RotateAnimAct.class, "Rotate"),
                new ItemActivity(AnimSetAct.class, "AnimSet"),
                new ItemActivity(CustomAnimAct.class, "Custom")
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
                startActivity(new Intent(TweenAnimAct.this, act.activityClass));
            }
        });
    }
}

