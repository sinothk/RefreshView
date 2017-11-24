package com.sinothk.refresh.RefreshLayout.widget.classics;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.sinothk.refresh.R;
import com.sinothk.refresh.RefreshLayout.QRefreshLayout;
import com.sinothk.refresh.RefreshLayout.listener.TargetHandler;
import com.sinothk.refresh.RefreshLayout.widget.ILoadView;

import static com.sinothk.refresh.RefreshLayout.widget.ILoadView.STATE.REFRESH;
import static com.sinothk.refresh.RefreshLayout.widget.ILoadView.STATE.COMPLETE;
import static com.sinothk.refresh.RefreshLayout.widget.ILoadView.STATE.PULL;
import static com.sinothk.refresh.RefreshLayout.widget.ILoadView.STATE.START;

/**
 * LoadView默认实现
 *
 * @author chqiu
 */
public class FooterView extends HeaderView {

    public FooterView(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getMeasuredWidth();
        mMeasuredHeight = mContent.getMeasuredHeight() + mMargin * 2;
        mContent.layout(0, mMargin, width, mContent.getMeasuredHeight() + mMargin);
    }

    @Override
    protected String getStateTips(ILoadView.STATE state) {
        if (state == REFRESH) {
            return getContext().getString(R.string.loading_tips);
        } else if (state == START) {
            return getContext().getString(R.string.normal_tips_2);
        } else if (state == PULL) {
            return getContext().getString(R.string.pulling_tips_2);
        } else if (state == COMPLETE) {
            return getContext().getString(R.string.complete_tips);
        }
        return "";
    }

    @Override
    protected int getIconRes() {
        return R.drawable.icon_pull_2;
    }

    @Override
    protected TargetHandler getTargetHandler() {
        return new TargetHandler() {
            @Override
            public void handleTarget(View targetView, float dis) {
                targetView.setTranslationY(-dis);
            }
        };
    }

    @Override
    public void addToRefreshLayout(QRefreshLayout layout) {
        QRefreshLayout.LayoutParams footerParams = new QRefreshLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 0);
        footerParams.gravity = Gravity.BOTTOM;
        layout.addView(this, footerParams);
    }
}
