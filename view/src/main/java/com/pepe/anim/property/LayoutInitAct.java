package com.pepe.anim.property;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.pepe.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @date 2017/11/18.
 */

public class LayoutInitAct extends Activity {

    private ListView mListView;
    private ArrayAdapter mAdapter;
    private Button mAddListBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_prop_layout_init);

        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData());
        mListView.setAdapter(mAdapter);

        mAddListBtn = (Button) findViewById(R.id.addlist);
        mAddListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addAll(getData());
            }
        });
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        return data;
    }
}
