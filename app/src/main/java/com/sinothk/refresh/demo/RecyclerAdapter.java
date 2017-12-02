package com.sinothk.refresh.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinothk.refresh.RefreshLayout.listener.OnItemClickListener;
import com.sinothk.refresh.RefreshLayout.listener.OnItemLongClickListener;

import java.util.ArrayList;

/**
 * Created by 梁玉涛 on 2017/12/03.
 * 功能：
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    private ArrayList<String> data;
    private RecyclerView recyclerView;
    private OnItemClickListener<String> onItemClickListener;
    private OnItemLongClickListener<String> onItemLongClickListener;

    public RecyclerAdapter(RecyclerView recyclerView) {
        this.data = new ArrayList<>();
        this.recyclerView = recyclerView;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textview,
                parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.view.setText(data.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(data.get(position), position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickListener.onLongClick(data.get(position), position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<String> data) {
        this.data.clear();
        this.data = (ArrayList<String>) data.clone();
        notifyDataSetChanged();
    }

    public void addData(ArrayList<String> addData) {
        recyclerView.scrollToPosition(getItemCount());

        data.addAll(addData);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<String> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<String> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView view;
        View itemView;

        public Holder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            view = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
