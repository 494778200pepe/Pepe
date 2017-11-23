package com.pepe.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.pepe.ItemActivity;
import com.pepe.view.camera.CameraAct;
import com.pepe.view.canvas.CanvasAct;
import com.pepe.view.paint.PaintAct;
import com.pepe.view.path.PathAct;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/22.
 */

public class DrawAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(CanvasAct.class, "Canvas"),
                new ItemActivity(CameraAct.class, "Camera"),
                new ItemActivity(PaintAct.class, "Paint"),
                new ItemActivity(PathAct.class, "Path")
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
                startActivity(new Intent(DrawAct.this, act.activityClass));
            }
        });
    }
}
