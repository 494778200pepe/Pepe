package com.pepe.retrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pepe.retrofit.Bean.ChapterBean;
import com.pepe.retrofit.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class ChapterListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ChapterBean.ResultBean.ChapterListBean> chapterList;
    public ChapterListAdapter(Context mContext, List<ChapterBean.ResultBean.ChapterListBean> chapterList){
        this.mContext=mContext;
        this.chapterList=chapterList;
    }

    @Override
    public int getCount() {
        return chapterList.size();
    }

    @Override
    public Object getItem(int position) {
        return chapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chapter, null,false);
            holder.chapter_id = (TextView) convertView.findViewById(R.id.chapter_id);
            holder.chapter_name = (TextView) convertView.findViewById(R.id.chapter_name);
            AutoUtils.autoSize(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ChapterBean.ResultBean.ChapterListBean chapter=chapterList.get(position);
        holder.chapter_id.setText(chapter.getId()+"");
        holder.chapter_name.setText(chapter.getName());
        return convertView;
    }

    public class ViewHolder{
        TextView chapter_id;
        TextView chapter_name;
    }
}
