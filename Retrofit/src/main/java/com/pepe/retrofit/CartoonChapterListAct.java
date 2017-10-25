package com.pepe.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pepe.retrofit.Adapter.ChapterListAdapter;
import com.pepe.retrofit.Bean.ChapterBean;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

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
public class CartoonChapterListAct extends AutoLayoutActivity {
    private List<ChapterBean.ResultBean.ChapterListBean> chapterList = new ArrayList<ChapterBean.ResultBean.ChapterListBean>();
    private Context mContext;
    private String comicName;

    private ChapterListAdapter myAdapter;

    private PullToRefreshListView listView;
    private int skip = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_chapterlist);
        comicName=getIntent().getStringExtra("comicName");
        mContext=CartoonChapterListAct.this;
        initView();
        getChapterList();

    }

    private void initView(){
        listView = (PullToRefreshListView) findViewById(R.id.listview_chapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        myAdapter=new ChapterListAdapter(mContext,chapterList);
        listView.setAdapter(myAdapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                skip = 0;
                getChapterList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                skip++;
                getChapterList();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, CartoonContentAct.class);
                intent.putExtra("id", chapterList.get(position).getId());
                intent.putExtra("comicName", comicName);
                startActivity(intent);
            }
        });
    }

    private void getChapterList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Call<ChapterBean> call;
        call = myService.getChapterList(BaseParam.AppKey,skip*20,comicName);
        call.enqueue(new Callback<ChapterBean>() {
            @Override
            public void onResponse(Response<ChapterBean> response, Retrofit retrofit) {
                if (listView.isRefreshing()) {
                    listView.onRefreshComplete();
                }
                if(response.isSuccess()) {
                    ChapterBean bean=response.body();
                    if (bean != null) {
                        ChapterBean.ResultBean resultBean = bean.getResult();
                        List<ChapterBean.ResultBean.ChapterListBean> chapterListBeanList = resultBean.getChapterList();
                        Log.d("pepe", "size---" + chapterListBeanList.size());
                        Log.d("pepe", "toString---" + chapterListBeanList.toString());
                        if (skip == 0)
                            chapterList.clear();
                        if (chapterListBeanList.size() == 0) {
                            Toast.makeText(mContext, "没有更多了！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        chapterList.addAll(chapterListBeanList);
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
