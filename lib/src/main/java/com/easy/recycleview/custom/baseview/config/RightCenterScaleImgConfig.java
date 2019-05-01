package com.easy.recycleview.custom.baseview.config;

import android.view.View;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.DyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;


/**
 * Created by Chant on 2018/6/17.
 */

public class RightCenterScaleImgConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
        if (dataItemBean.getRightCenterScaleImgResId() != 0) {
            itemView. mRightCenterScaleImgeLayout.setVisibility(View.VISIBLE);
            if ( RecycleConfig.getInstance().getIloadImage()!=null) {
                RecycleConfig.getInstance().getIloadImage().loadResourceId(dataItemBean.getRightCenterScaleImgResId(), itemView.mRightCenterScaleImgeView);
            }
        } else {
            itemView.mRightCenterScaleImgeLayout.setVisibility(View.GONE);
        }
    }
}
