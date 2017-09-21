package com.pepe.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.pepe.retrofit.Adapter.ContentListAdapter;
import com.pepe.retrofit.Bean.ContentBean;
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
public class CartoonContentAct extends AutoLayoutActivity {
    private List<ContentBean.ResultBean.ImageListBean> contentList = new ArrayList<ContentBean.ResultBean.ImageListBean>();
    private Context mContext;
    private String comicName;
    private int id;

    private ContentListAdapter myAdapter;

    private ViewPager content_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contentlist);
        comicName=getIntent().getStringExtra("comicName");
        id=getIntent().getIntExtra("id",0);
        mContext=CartoonContentAct.this;
        initView();
        getChapterList();

    }

    private void initView(){
        content_viewpager = (ViewPager) findViewById(R.id.content_viewpager);
        myAdapter=new ContentListAdapter(mContext,contentList);
        content_viewpager.setAdapter(myAdapter);
        content_viewpager.setOffscreenPageLimit(1);//这里是足有各1个
        content_viewpager.setPageMargin(50);
    }

    private void getChapterList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Call<ContentBean> call = myService.getContentList(BaseParam.AppKey,comicName,id);
        call.enqueue(new Callback<ContentBean>() {
            @Override
            public void onResponse(Response<ContentBean> response, Retrofit retrofit) {

                if(response.isSuccess()) {
                    ContentBean bean=response.body();
                    if (bean != null) {
                        ContentBean.ResultBean resultBean = bean.getResult();
                        List<ContentBean.ResultBean.ImageListBean> imageListBeanList = resultBean.getImageList();
                        Log.d("pepe", "size---" + imageListBeanList.size());
                        Log.d("pepe", "toString---" + imageListBeanList.toString());
                        if (imageListBeanList.size() == 0) {
                            Toast.makeText(mContext, "没有更多了！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        contentList.addAll(imageListBeanList);
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
