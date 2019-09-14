package com.easy.recycleview;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Administrator on 2019/8/31 0031.
 */

public interface SwipOnRefreshListener extends SwipeRefreshLayout.OnRefreshListener {


    void onPullRefresh();

}
