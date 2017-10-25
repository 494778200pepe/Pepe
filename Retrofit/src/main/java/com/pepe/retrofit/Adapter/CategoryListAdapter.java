package com.pepe.retrofit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pepe.retrofit.R;

import java.util.List;

/**
 * Created by pepe on 2016/4/20.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class CategoryListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> categoryList;

    public CategoryListAdapter(Context mContext, List<String> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_category, null,false);
            holder.category_name = (TextView) convertView.findViewById(R.id.category_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("pepe",position+"");
        holder.category_name.setText(categoryList.get(position));
        return convertView;
    }

    public class ViewHolder{
        TextView category_name;
    }
}


