package com.jingchen.pulltorefresh.pullableview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.easy.recycleview.view.RecyclerViewSupport;

/**
 * Created by Administrator on 2019/8/8 0008.
 */

public class PullableRecycleView  extends RecyclerViewSupport implements  Pullable{

    private int lastVisibleItemPosition;

    boolean canPullUp=false;

    public PullableRecycleView(Context context)
    {
        super(context);

    }



    public PullableRecycleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    public  void init() {

        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断是否到底部了
                if (newState ==RecyclerView.SCROLL_STATE_IDLE &&
                        lastVisibleItemPosition + 1 == recyclerView.getAdapter().getItemCount()) {
                    canPullUp=true;
                }else{
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

    @Override
    public boolean canPullDown() {
        return false;
    }

    @Override
    public boolean canPullUp() {
        return canPullUp;
    }
}
