package com.easy.recycleview.recycleview.item.config;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;

/**
 * Created by Chant on 2018/6/17.
 */

public class TitleTextViewConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
            itemView.mTitleTextView.setText(dataItemBean.getTitle());

    }
}
