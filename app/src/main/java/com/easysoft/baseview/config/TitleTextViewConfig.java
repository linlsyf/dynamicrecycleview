package com.easysoft.baseview.config;

import com.easysoft.baseview.item.ContentItemView;
import com.easysoft.bean.AddressItemBean;

/**
 * Created by Chant on 2018/6/17.
 */

public class TitleTextViewConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
            itemView.mTitleTextView.setText(dataItemBean.getTitle());

    }
}
