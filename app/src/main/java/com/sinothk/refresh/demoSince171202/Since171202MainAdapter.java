package com.sinothk.refresh.demoSince171202;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinothk.refresh.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limxing on 16/7/23.
 * <p>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class Since171202MainAdapter extends RecyclerView.Adapter<Since171202MainAdapter.MasonryView> {

    private List<String> list;

    public Since171202MainAdapter() {
        list = new ArrayList<>();
    }

    public Since171202MainAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.since_171202_item, parent, false);
        return new MasonryView(view);
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        holder.textView.setText(list.get(position));
//        holder.textView.setPadding(0, 20 * position, 0, 0);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<String> data) {
        if (list == null) {
            list = new ArrayList<>();
        } else {
            this.list.clear();
        }
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    class MasonryView extends RecyclerView.ViewHolder {
        TextView textView;

        public MasonryView(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            textView.setWidth(500);

        }

    }
}



