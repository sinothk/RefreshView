package com.sinothk.refresh.demoSince171202;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sinothk.refresh.demo.R;
import com.sinothk.refresh.since171202.ORecyclerView;
import com.sinothk.refresh.since171202.OnItemClickListener;
import com.sinothk.refresh.since171202.OnItemLongClickListener;

import java.util.ArrayList;


/**
 * Created by limxing on 16/7/23.
 * <p/>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class Since171202MainActivity extends AppCompatActivity {
    private ORecyclerView recycleView;
    private boolean b;
    private ArrayList<String> list;
    private Since171202MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.since171202_activity_main);

        list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("leefeng.me" + i);
        }

        recycleView = (ORecyclerView) findViewById(R.id.recycleview);
        recycleView.setLoadMore(true);
        recycleView.setRefresh(true);
        recycleView.setNoDateShow();
        recycleView.setAutoLoadMore(true);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new Since171202MainAdapter(list);
//        adapter = new Since171202MainAdapter();
        recycleView.setAdapter(adapter);

        TextView tv = new TextView(Since171202MainActivity.this);
        tv.setText("这是头部");
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.RED);
        recycleView.setHeaderView(tv);

        tv = new TextView(Since171202MainActivity.this);
        tv.setText("这是底部");
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.RED);
        recycleView.setFootView(tv);

//        adapter.setData(list);

        recycleView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(Since171202MainActivity.this, "Click" + position, Toast.LENGTH_SHORT).show();
            }
        });

        recycleView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onLongClick(int po) {
                Toast.makeText(Since171202MainActivity.this, "Long:" + po, Toast.LENGTH_SHORT).show();
            }
        });

        recycleView.setORecyclerViewListener(new ORecyclerView.ORecyclerViewListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b = !b;
                        list.add(0, "leefeng.me" + "==onRefresh");
                        recycleView.stopRefresh(b);
                        adapter.notifyItemInserted(0);
                        adapter.notifyItemRangeChanged(0, list.size());

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recycleView.stopLoadMore();
                        list.add(list.size(), "leefeng.me" + "==onLoadMore");

                        adapter.notifyItemRangeInserted(list.size() - 1, 1);
                    }
                }, 2000);
            }
        });

        recycleView.setScrollChangeListener(new ORecyclerView.ORecyclerViewScrollChange() {
            @Override
            public void onORecyclerViewScrollChange(View view, int i, int i1) {

            }
        });
    }
}
