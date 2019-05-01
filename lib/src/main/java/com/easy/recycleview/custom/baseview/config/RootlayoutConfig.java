package com.easy.recycleview.custom.baseview.config;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.DyItemBean;


/**
 * Created by Chant on 2018/6/17.
 */

public class RootlayoutConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
        LinearLayout.LayoutParams rootParams = (LinearLayout.LayoutParams) itemView.mRootlayout.getLayoutParams();
        if (itemView.mBindItemBean.getItemHight() != 0) {
            rootParams.height = itemView.mBindItemBean.getItemHight();
        } else {
            rootParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        itemView.mRootlayout.setLayoutParams(rootParams);
        if (dataItemBean.getContentBgResid()!=0){
            itemView.mRootlayout.setBackgroundResource(dataItemBean.getContentBgResid());

        }
    }
}
