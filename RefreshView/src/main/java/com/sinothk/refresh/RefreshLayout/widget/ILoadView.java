package com.sinothk.refresh.RefreshLayout.widget;

import android.view.View;

import com.sinothk.refresh.RefreshLayout.QRefreshLayout;

/**
 * Created by chqiu on 2017/3/27.
 */

public interface ILoadView {
    enum STATE {
        REFRESH, START, PULL, COMPLETE
    }

    STATE getState();

    boolean canTargetScroll();

    void onRefreshBegin(View targetView);

    void setState(STATE state);

    void onPrepare(View targetView);

    void onPulling(float dis, View targetView);

    void addToRefreshLayout(QRefreshLayout layout);

    View getView();

}
