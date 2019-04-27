package com.easy.recycleview.custom.baseview.config;


import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.AddressItemBean;

/**
 * Created by Chant on 2018/6/17.
 */

public class TitleTextViewConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
            itemView.mTitleTextView.setText(dataItemBean.getTitle());

    }
}
