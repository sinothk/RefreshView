package com.sinothk.refresh.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sinothk.refresh.RefreshLayout.QRefreshLayout;
import com.sinothk.refresh.RefreshLayout.listener.OnItemClickListener;
import com.sinothk.refresh.RefreshLayout.listener.OnItemLongClickListener;
import com.sinothk.refresh.RefreshLayout.listener.RefreshHandler;
import com.sinothk.refresh.RefreshLayout.widget.Material.MaterialBlackFooterView;
import com.sinothk.refresh.RefreshLayout.widget.Material.MaterialBlackHeaderView;
import com.sinothk.refresh.RefreshLayout.widget.Material.MaterialFooterView;
import com.sinothk.refresh.RefreshLayout.widget.Material.MaterialHeaderView;
import com.sinothk.refresh.RefreshLayout.widget.classics.FooterView;
import com.sinothk.refresh.RefreshLayout.widget.classics.HeaderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class RecyclerViewActivity extends AppCompatActivity {
    private QRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<String> mDatas;
    private Handler mHandler = new Handler();

    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);
        mDatas = new ArrayList<>();
        getData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter = new RecyclerAdapter(recyclerView);
        recyclerView.setAdapter(recyclerAdapter);

        refreshLayout = (QRefreshLayout) findViewById(R.id.refreshlayout);
//        refreshLayout.setHeaderView(new MaterialBlackHeaderView(this));
//        refreshLayout.setFooterView(new MaterialBlackFooterView(this));

        refreshLayout.setHeaderView(new HeaderView(this));
        refreshLayout.setFooterView(new FooterView(this));

        refreshLayout.setLoadMoreEnable(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        refreshLayout.setRefreshHandler(new RefreshHandler() {
            @Override
            public void onRefresh(QRefreshLayout refresh) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.add(0, "下拉刷新增加的数据");
                        recyclerAdapter.setData(mDatas);
                        refreshLayout.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(QRefreshLayout refresh) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<String> tempData = new ArrayList<>();
                        for (int i = recyclerAdapter.getItemCount(); i < (recyclerAdapter.getItemCount() + 10); i++) {
                            tempData.add("上拉增加的数据_" + i);
                        }

                        recyclerAdapter.addData(tempData);
                        refreshLayout.loadMoreComplete();
                    }
                }, 2000);
            }
        });

        recyclerAdapter.setData(mDatas);
        recyclerAdapter.setOnItemClickListener(new OnItemClickListener<String>() {
            @Override
            public void onClick(String s, int position) {
                Toast.makeText(RecyclerViewActivity.this, "点击 : " +
                        s, Toast.LENGTH_SHORT).show();

                Log.e("onClick","11");
            }
        });

        recyclerAdapter.setOnItemLongClickListener(new OnItemLongClickListener<String>() {
            @Override
            public void onLongClick(String s, int position) {
                Toast.makeText(RecyclerViewActivity.this, "长按 : " +
                        s, Toast.LENGTH_SHORT).show();

                Log.e("onClick","22");

            }
        });
    }

    public void getData() {
        Collections.addAll(mDatas, "第1条数据", "第2条数据", "第3条数据", "第4条数据", "第5条数据",
                "第6条数据", "第7条数据", "第8条数据", "第9条数据", "第10条数据", "第11条数据",
                "第12条数据", "第13条数据", "第14条数据", "第15条数据", "第16条数据", "第17条数据", "第18条数据"
                , "第19条数据", "第20条数据");
    }
}
