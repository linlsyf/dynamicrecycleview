package com.easy.recycleview.custom.baseview.config;

import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.DensityUtil;
import com.easy.recycleview.custom.bean.DyItemBean;


/**
 * Created by Chant on 2018/6/17.
 */

public class ContentLayoutConfig {
    public static void load(ContentItemView itemView, DyItemBean dataItemBean) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) itemView.mContenLayout.getLayoutParams();
        int mContentMagin=0;
        if (dataItemBean.getContentLayoutMagin() != 0) {
            mContentMagin = dataItemBean.getContentLayoutMagin();
        } else {
            mContentMagin = DensityUtil.dip2px(itemView.getContext(), 10);
        }

        params.topMargin = mContentMagin;
        params.bottomMargin = mContentMagin;
        itemView.mContenLayout.setLayoutParams(params);


    }
}