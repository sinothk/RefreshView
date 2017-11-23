package com.sinothk.refresh.RefreshLayout.widget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author chqiu
 */
public abstract class QLoadView extends FrameLayout implements ILoadView {

    public QLoadView(Context context) {
        super(context);
    }

    public View getView() {
        return this;
    }
}
