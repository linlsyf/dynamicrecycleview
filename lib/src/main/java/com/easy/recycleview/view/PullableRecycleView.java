package com.easy.recycleview.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.easy.recycleview.SwipOnRefreshListener;

/**
 * Created by Administrator on 2019/8/8 0008.
 */

public class PullableRecycleView  extends RecyclerViewSupport  {

    private int lastVisibleItemPosition;

    boolean canPullUp=false;
    boolean mRefreshing;

    public PullableRecycleView(Context context)
    {
        super(context);

    }



    public PullableRecycleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    public  void init(final SwipOnRefreshListener listener) {

        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断是否到底部了
                if (newState ==RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItemPosition + 1 == recyclerView.getAdapter().getItemCount()) {
                    canPullUp=true;

                      if(!mRefreshing){
                          listener.onPullRefresh();
                      }
              //  }else{
                    canPullUp=false;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView,dx, dy);
                //获取最后一个项目位置

                LinearLayoutManager linearLayoutManager=(LinearLayoutManager)getLayoutManager();

                lastVisibleItemPosition =linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
    public void setRefreshing(boolean refreshing) {
        mRefreshing=refreshing;
    }

}
