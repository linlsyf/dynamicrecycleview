package com.easy.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2019/4/27 0027.
 */

public class RecyclerViewSupport  extends RecyclerView {
    private static final String TAG = "RecyclerViewEmptySupport";
    /**
     * 当数据为空时展示的View
     */
    private View mEmptyView;
    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {
        @SuppressLint("LongLogTag")
        @Override
        public void onChanged() {
            Log.i(TAG, "onChanged: 000");
            Adapter<?> adapter = getAdapter(); //这种写发跟之前我们之前看到的ListView的是一样的，判断数据为空否，再进行显示或者隐藏
            if (adapter != null && mEmptyView != null) {
                if (adapter.getItemCount() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    RecyclerViewSupport.this.setVisibility(View.GONE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                    RecyclerViewSupport.this.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    public RecyclerViewSupport(Context context) {
        super(context);
    }

    public RecyclerViewSupport(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public RecyclerViewSupport(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * * @param emptyView 展示的空view
     */
    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        Log.i(TAG, "setAdapter: adapter::" + adapter);
        if (adapter != null) {
            //这里用了观察者模式，同时把这个观察者添加进去，
            // 至于这个模式怎么用，谷歌一下，不多讲了，因为这个涉及到了Adapter的一些原理，感兴趣可以点进去看看源码，还是受益匪浅的
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        //当setAdapter的时候也调一次（实际上，经我粗略验证，不添加貌似也可以。不行就给添上呗，多大事嘛）
        emptyObserver.onChanged();
    }
}
