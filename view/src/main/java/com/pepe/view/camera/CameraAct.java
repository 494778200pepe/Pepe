package com.pepe.view.camera;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.pepe.view.ItemActivity;
import com.pepe.view.camera.act.RotateAct;
import com.pepe.view.camera.act.TranslateAct;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wang on 2017/10/10.
 */

public class CameraAct extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(TranslateAct.class, "Translate"),
                new ItemActivity(RotateAct.class, "Rotate")
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
                startActivity(new Intent(CameraAct.this, act.activityClass));
            }
        });
    }
}
