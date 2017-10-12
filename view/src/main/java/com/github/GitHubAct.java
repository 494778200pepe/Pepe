package com.github;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.github.Card.CardAct;
import com.github.FunSwitch.FunSwitchAct;
import com.pepe.view.ItemActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wang on 2017/10/11.
 */

public class GitHubAct extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<ItemActivity> acts = Arrays.asList(
                new ItemActivity(FunSwitchAct.class, "FunSwitch"),
                new ItemActivity(CardAct.class, "Card")
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
                startActivity(new Intent(GitHubAct.this, act.activityClass));
            }
        });
    }
}
