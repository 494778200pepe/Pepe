package com.pepe.anim;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.github.GitHubAct;
import com.hencoder.HenCoderAct;
import com.pepe.view.ItemActivity;
import com.pepe.view.MainActivity;
import com.pepe.view.R;
import com.pepe.view.camera.CameraAct;
import com.pepe.view.canvas.CanvasAct;
import com.pepe.view.canvas.CanvasTestAct;
import com.pepe.view.paint.PaintAct;
import com.pepe.view.touch.EventAct;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/11.
 */

public class PropertyAnimAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
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
                startActivity(new Intent(PropertyAnimAct.this, act.activityClass));
            }
        });
    }
}
