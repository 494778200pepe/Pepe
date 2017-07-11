package com.pepe.view.paint;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.pepe.view.ItemActivity;
import com.pepe.view.paint.act.PaintAlignAct;
import com.pepe.view.paint.act.PaintAntiAliasAct;
import com.pepe.view.paint.act.PaintCapAct;
import com.pepe.view.paint.act.PaintStyleAct;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wang on 2017/7/11.
 */

public class PaintAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(PaintAlignAct.class, "Align"),
                new ItemActivity(PaintAntiAliasAct.class, "AntiAlias"),
                new ItemActivity(PaintCapAct.class, "Cap"),
                new ItemActivity(PaintStyleAct.class, "Style")
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
                startActivity(new Intent(PaintAct.this, act.activityClass));
            }
        });
    }
}