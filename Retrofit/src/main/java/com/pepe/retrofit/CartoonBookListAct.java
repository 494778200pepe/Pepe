package com.pepe.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pepe.retrofit.Adapter.BookListAdapter;
import com.pepe.retrofit.Bean.BookBean;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by pepe on 2016/4/18.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class CartoonBookListAct extends AutoLayoutActivity {
    private List<BookBean.ResultBean.BookListBean> bookList = new ArrayList<BookBean.ResultBean.BookListBean>();
    private Context mContext;
    private String mCategory;

    private BookListAdapter myAdapter;

    private PullToRefreshListView listView;
    private int skip = 1;
    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_booklist);
        mCategory=getIntent().getStringExtra("category");
        mContext=CartoonBookListAct.this;
        initView();
        getBookList();

    }

    private void initView(){
        listView = (PullToRefreshListView) findViewById(R.id.listview_book);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        myAdapter=new BookListAdapter(mContext,bookList);
        listView.setAdapter(myAdapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                skip = 1;
                getBookList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                skip++;
                getBookList();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, CartoonChapterListAct.class);
                intent.putExtra("comicName", "无概念少女");
                startActivity(intent);
            }
        });
    }

    private void getBookList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Call<BookBean> call = myService.getBookList(BaseParam.AppKey,skip,mCategory);
        call.enqueue(new Callback<BookBean>() {
            @Override
            public void onResponse(Response<BookBean> response, Retrofit retrofit) {
                if (listView.isRefreshing()) {
                    listView.onRefreshComplete();
                }
                if(response.isSuccess()) {
                    Toast.makeText(mContext,"woca", Toast.LENGTH_SHORT).show();
                    BookBean bean=response.body();
                    if (bean != null) {
                        BookBean.ResultBean resultBean = bean.getResult();
                        List<BookBean.ResultBean.BookListBean> bookListBeanList = resultBean.getBookList();
                        if (skip == 0)
                            bookList.clear();
                        if (bookListBeanList.size() == 0) {
                            Toast.makeText(mContext, "没有更多了！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        bookList.addAll(bookListBeanList);
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
