
# 1
    <?xml version="1.0" encoding="utf-8"?>
    <com.sinothk.refresh.RefreshLayout.QRefreshLayout 
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/refreshlayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">
        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </com.sinothk.refresh.RefreshLayout.QRefreshLayout>

# 2
    RecyclerView  recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
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
# 3       
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
