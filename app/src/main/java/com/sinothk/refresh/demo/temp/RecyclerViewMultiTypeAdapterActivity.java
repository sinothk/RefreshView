package com.sinothk.refresh.demo.temp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sinothk.refresh.RefreshLayout.QRefreshLayout;
import com.sinothk.refresh.RefreshLayout.listener.RefreshHandler;
import com.sinothk.refresh.RefreshLayout.widget.classics.FooterView;
import com.sinothk.refresh.RefreshLayout.widget.classics.HeaderView;
import com.sinothk.refresh.demo.R;
import com.sinothk.refresh.demo.temp.lib.adapter.XRecyclerViewAdapter;
import com.sinothk.refresh.demo.temp.lib.adapter.decoration.DividerDecoration;
import com.sinothk.refresh.demo.temp.lib.adapter.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RecyclerViewMultiTypeAdapterActivity extends AppCompatActivity {
    private QRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private Handler mHandler = new Handler();

    RecyclerViewMultiTypeAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.addItemDecoration(new DividerDecoration(Color.parseColor("#0000FF"), 12));

        recyclerView.addItemDecoration(new SpaceDecoration(Color.BLACK, 12));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

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
                        ArrayList<News> mDatas = getData();
                        mDatas.add(0, new News(1, "下拉刷新时间——" + new Date().getTime()));

                        recyclerAdapter.setDataLists((List<News>) mDatas.clone());
                        refreshLayout.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(QRefreshLayout refresh) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<News> tempData = new ArrayList<>();
                        for (int i = recyclerAdapter.getItemCount(); i < (recyclerAdapter.getItemCount() + 10); i++) {
                            News news = new News();
                            news.setTitle("第" + i + "条数据");
                            if (i % 2 == 0) {
                                news.setNewsType(0);
                            } else {
                                news.setNewsType(1);
                            }
                            tempData.add(news);
                        }

                        recyclerView.scrollToPosition(recyclerAdapter.getItemCount());
                        recyclerAdapter.addAll((List<News>) tempData.clone());
                        refreshLayout.loadMoreComplete();
                    }
                }, 2000);
            }
        });

        recyclerAdapter = new RecyclerViewMultiTypeAdapter(recyclerView, new ArrayList<News>());
        recyclerView.setAdapter(recyclerAdapter);


        //点击事件
        recyclerAdapter.setOnItemClickListener(new XRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(RecyclerViewMultiTypeAdapterActivity.this, "点击 : " + position + ", v = " + recyclerAdapter.getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerAdapter.setOnItemLongClickListener(new XRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View v, int position) {
                Toast.makeText(RecyclerViewMultiTypeAdapterActivity.this, "长按 : " + position + ", v = " + recyclerAdapter.getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        recyclerView.setAdapter(recyclerAdapter);

        ArrayList<News> mDatas = getData();
        recyclerAdapter.setDataLists((List<News>) mDatas.clone());
    }

    public ArrayList<News> getData() {
        ArrayList<News> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            News news = new News();
            news.setTitle("第" + i + "条数据");
            if (i % 2 == 0) {
                news.setNewsType(0);
            } else {
                news.setNewsType(1);
            }
            datas.add(news);
        }

        return datas;
    }
}
