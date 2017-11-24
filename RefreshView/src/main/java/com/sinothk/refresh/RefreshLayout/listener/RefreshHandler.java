package com.sinothk.refresh.RefreshLayout.listener;

import com.sinothk.refresh.RefreshLayout.QRefreshLayout;

/**
 * @author chqiu
 */
public interface RefreshHandler {
    void onRefresh(QRefreshLayout refresh);

    void onLoadMore(QRefreshLayout refresh);
}
