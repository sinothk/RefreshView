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

public class RecyclerViewSimpleAdapter extends XRecyclerViewAdapter<String> {

    public RecyclerViewSimpleAdapter(RecyclerView recyclerView, List<String> datas) {
        super(recyclerView, datas, R.layout.item_textview);
    }

    @Override
    public void bindData(XViewHolder holder, String data, int position) {
        //方法一：
        holder.setText(R.id.textview, data);
        //方法二：
        //TextView textView = holder.getView(R.id.text);
        //textView.setText(data);
    }

}
