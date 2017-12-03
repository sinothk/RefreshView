package com.sinothk.refresh.demo.temp;

import android.support.v7.widget.RecyclerView;

import com.sinothk.refresh.demo.R;
import com.sinothk.refresh.demo.temp.lib.adapter.XRecyclerViewAdapter;
import com.sinothk.refresh.demo.temp.lib.adapter.XViewHolder;

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
        switch (data.getNewsType()) {
            case News.TYPE_NONE_PICTURE:
                layoutResId = R.layout.item_textview;
                break;
            case News.TYPE_SINGLE_PICTURE:
                layoutResId = R.layout.item_image;
                break;
        }
        return layoutResId;
    }

    @Override
    public void bindData(XViewHolder holder, News data, int position) {
        switch (data.getNewsType()) {
            case News.TYPE_NONE_PICTURE:
                holder.setText(R.id.textview, data.getTitle());
                break;
            case News.TYPE_SINGLE_PICTURE:
                holder.setText(R.id.textview2, data.getTitle());

//                holder.setText(R.id.newTitle, data.getTitle())
//                        .setText(R.id.newAuthor, data.getAuthor())
//                        .setText(R.id.newTime, data.getTime())
//                        .setImageUrl(R.id.newImage, data.getImageUrl(), new GlideCircleTransform(getContext()));
                break;
        }
    }
}