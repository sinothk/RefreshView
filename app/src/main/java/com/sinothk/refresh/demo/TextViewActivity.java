package com.sinothk.refresh.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sinothk.refresh.RefreshLayout.QRefreshLayout;
import com.sinothk.refresh.RefreshLayout.listener.RefreshHandler;


public class TextViewActivity extends AppCompatActivity {
    private QRefreshLayout refreshLayout;
    private TextView textView;
    private String mDatas;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        refreshLayout = (QRefreshLayout) findViewById(R.id.refreshlayout);
        textView = (TextView) findViewById(R.id.textview);
        mDatas = "初始数据";
        textView.setText(mDatas);
        refreshLayout.setLoadMoreEnable(true);
        refreshLayout.setRefreshHandler(new RefreshHandler() {
            @Override
            public void onRefresh(QRefreshLayout refresh) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas = "下拉刷新增加的数据\n" + mDatas;
                        textView.setText(mDatas);
                        refreshLayout.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore(QRefreshLayout refresh) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas = mDatas + "\n上拉增加的数据";
                        textView.setText(mDatas);
                        refreshLayout.loadMoreComplete();
                    }
                }, 3000);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(textView.getContext(),
                        textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
