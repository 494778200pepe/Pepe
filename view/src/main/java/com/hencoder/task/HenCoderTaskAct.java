package com.hencoder.task;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hencoder.task.Flipboard.FlipboardAct;
import com.hencoder.task.Like.LikeAct;
import com.hencoder.task.MIMovement.MIMovementAct;
import com.hencoder.task.Ruler.RulerAct;
import com.pepe.ItemActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/6.
 */

public class HenCoderTaskAct extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(

                new ItemActivity(FlipboardAct.class, "Flipboard"),
                new ItemActivity(LikeAct.class, "Like"),
                new ItemActivity(MIMovementAct.class, "MIMovement"),
                new ItemActivity(RulerAct.class, "Ruler")
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
                startActivity(new Intent(HenCoderTaskAct.this, act.activityClass));
            }
        });
    }
}
