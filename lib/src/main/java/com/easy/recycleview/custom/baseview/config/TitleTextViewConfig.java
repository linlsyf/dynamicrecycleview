package com.easy.recycleview.custom.baseview.config;


import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.DyItemBean;

/**
 * Created by Chant on 2018/6/17.
 */

public class TitleTextViewConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
            itemView.mTitleTextView.setText(dataItemBean.getTitle());

    }
}
