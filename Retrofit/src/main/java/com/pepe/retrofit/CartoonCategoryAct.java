package com.pepe.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pepe.retrofit.Adapter.CategoryListAdapter;
import com.pepe.retrofit.Bean.CategoryBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class CartoonCategoryAct extends Activity {
    private Map<String, String> map = new HashMap<String, String>();
    private ListView listview;
    private List<String> categoryList = new ArrayList<String>();
    private CategoryListAdapter myAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cartoon_category);
        mContext = CartoonCategoryAct.this;
        listview = (ListView) findViewById(R.id.listview);
        myAdapter = new CategoryListAdapter(mContext,categoryList);
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, CartoonBookListAct.class);
                intent.putExtra("category",
                        categoryList.get(position));
                startActivity(intent);
            }
        });
        getCategoryList();

    }


    private void getCategoryList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Call<CategoryBean> call = myService.getCategory(BaseParam.AppKey);
        call.enqueue(new Callback<CategoryBean>() {
            @Override
            public void onResponse(Response<CategoryBean> response, Retrofit retrofit) {

                if (response.isSuccess()) {
                    CategoryBean bean = response.body();
                    if (bean != null) {
                        List<String> list = bean.getResult();
                        categoryList.addAll(list);
                    }
                    Log.d("pepe",bean.toString()+"1"+bean.getResult().size()+"--"+categoryList.size());
                    if(categoryList.size()>0){
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
}
