package com.sinothk.refresh.v1.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sinothk.refresh.R;
import com.sinothk.refresh.RefreshLayout.QRefreshLayout;
import com.sinothk.refresh.RefreshLayout.listener.RefreshHandler;
import com.sinothk.refresh.RefreshLayout.widget.classics.FooterView;
import com.sinothk.refresh.RefreshLayout.widget.classics.HeaderView;
import com.sinothk.refresh.v1.adapter.XRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewSimpleAdapterActivity extends AppCompatActivity {
    private QRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<String> mDatas;
    private Handler mHandler = new Handler();

    RecyclerViewSimpleAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerDecoration(Color.parseColor("#FF0000"), 12));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        refreshLayout = (QRefreshLayout) findViewById(R.id.refreshlayout);
        refreshLayout.setHeaderView(new HeaderView(this));
        refreshLayout.setFooterView(new FooterView(this));
        refreshLayout.setLoadMoreEnable(true);
        refreshLayout.setRefreshHandler(new RefreshHandler() {
            @Override
            public void onRefresh(QRefreshLayout refresh) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.add(0, "下拉刷新增加的数据");
                        recyclerAdapter.setDataLists((List<String>) mDatas.clone());
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

                        recyclerView.scrollToPosition(recyclerAdapter.getItemCount());

                        recyclerAdapter.addAll((List<String>) tempData.clone());

                        refreshLayout.loadMoreComplete();
                    }
                }, 2000);
            }
        });

        mDatas = new ArrayList<>();
        getData();
        recyclerAdapter = new RecyclerViewSimpleAdapter(recyclerView, mDatas);
        recyclerView.setAdapter(recyclerAdapter);


        //点击事件
        recyclerAdapter.setOnItemClickListener(new XRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(RecyclerViewSimpleAdapterActivity.this, "点击 : " + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(recyclerAdapter);

    }

    public void getData() {
        Collections.addAll(mDatas, "第1条数据", "第2条数据", "第3条数据", "第4条数据", "第5条数据"
                //,
//                "第6条数据", "第7条数据", "第8条数据", "第9条数据", "第10条数据", "第11条数据",
//                "第12条数据", "第13条数据", "第14条数据", "第15条数据", "第16条数据", "第17条数据", "第18条数据"
                , "第19条数据", "第20条数据");
    }
}
