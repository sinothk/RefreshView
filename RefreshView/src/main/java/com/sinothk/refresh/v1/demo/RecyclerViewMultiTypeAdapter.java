package com.sinothk.refresh.v1.demo;

import android.support.v7.widget.RecyclerView;


import com.sinothk.refresh.R;
import com.sinothk.refresh.v1.adapter.XRecyclerViewAdapter;
import com.sinothk.refresh.v1.adapter.XViewHolder;

import java.util.List;

/**
 * Created by 梁玉涛 on 2017/12/03.
 * 功能：
 */

public class RecyclerViewMultiTypeAdapter extends XRecyclerViewAdapter<News> {

    public RecyclerViewMultiTypeAdapter(RecyclerView mRecyclerView, List<News> dataLists) {
        super(mRecyclerView, dataLists);
    }

    @Override
    public int getItemLayoutResId(News data, int position) {
        int layoutResId = -1;
        if (data.getNewsType() == News.TYPE_NONE_PICTURE) {
            layoutResId = R.layout.item_textview;
        }else if (data.getNewsType() == News.TYPE_SINGLE_PICTURE){
            layoutResId = R.layout.item_image;
        }

        return layoutResId;
    }

    @Override
    public void bindData(XViewHolder holder, News data, int position) {

        if (data.getNewsType() == News.TYPE_NONE_PICTURE) {
            holder.setText(R.id.textview, data.getTitle());
        }else if (data.getNewsType() == News.TYPE_SINGLE_PICTURE){
            holder.setText(R.id.textview2, data.getTitle());
        }
    }
}