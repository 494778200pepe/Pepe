package com.pepe.anim;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.pepe.anim.property.AnimatorSetAct;
import com.pepe.anim.property.DropAct;
import com.pepe.anim.property.KeyframeAct;
import com.pepe.anim.property.LayoutChangeAct;
import com.pepe.anim.property.LayoutInitAct;
import com.pepe.anim.property.MenuAct;
import com.pepe.anim.property.ObjectAnimatorAct;
import com.pepe.anim.property.PropertyValuesHolderAct;
import com.pepe.anim.property.TimerActivity;
import com.pepe.anim.property.ValueAnimatorAct;
import com.pepe.ItemActivity;

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
                new ItemActivity(ObjectAnimatorAct.class, "Object"),
                new ItemActivity(ValueAnimatorAct.class, "Value"),
                new ItemActivity(AnimatorSetAct.class, "Set"),
                new ItemActivity(PropertyValuesHolderAct.class, "Holder"),
                new ItemActivity(KeyframeAct.class, "Keyframe"),
                new ItemActivity(LayoutInitAct.class, "Layout_init"),
                new ItemActivity(LayoutChangeAct.class, "Layout_change"),
                new ItemActivity(DropAct.class, "Drop"),
                new ItemActivity(MenuAct.class, "Menu"),
                new ItemActivity(TimerActivity.class, "Timer")
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
