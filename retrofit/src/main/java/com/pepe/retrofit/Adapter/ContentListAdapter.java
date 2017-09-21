package com.pepe.retrofit.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.pepe.retrofit.Bean.ContentBean;
import com.pepe.retrofit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class ContentListAdapter extends PagerAdapter {
    private Context mContext;
    private List<ContentBean.ResultBean.ImageListBean> imageList;
    private List<ImageView> viewList=new ArrayList<ImageView>();
    public ContentListAdapter(Context mContext, List<ContentBean.ResultBean.ImageListBean> imageList){
        this.mContext=mContext;
        this.imageList=imageList;
    }

    @Override
    // 复写
    public int getCount() {
        return imageList.size();
    }
    @Override
    // 复写
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (arg1);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    // 复写
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(viewList.get(position));
        Log.d("pepe","移除");
    }
    @Override
    // 复写
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        imageView.setLayoutParams(layoutParams);
        viewList.add(imageView);
        ContentBean.ResultBean.ImageListBean iamge=imageList.get(position);
        Glide.with(mContext)
                .load(iamge.getImageUrl())
                .placeholder(R.mipmap.book_default)
                .error(R.mipmap.glide_error)
                .crossFade()
                .fitCenter()//完全填充，但图像可能不会完整显示
                .into(imageView);
        ((ViewPager) container).addView(imageView);
        Log.d("pepe","新建");
        return imageView;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
    }


    @Override
    public void startUpdate(ViewGroup container) {
    }


}
