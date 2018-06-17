package com.easy.recycleview.recycleview.item.config;

import android.view.View;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;

/**
 * Created by Chant on 2018/6/17.
 */

public class RightCenterScaleImgConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
        if (dataItemBean.getRightCenterScaleImgResId() != 0) {
            itemView. mRightCenterScaleImgeLayout.setVisibility(View.VISIBLE);
            if (dataItemBean.getIloadImage()!=null){
                dataItemBean.getIloadImage().loadResourceId(dataItemBean.getRightCenterScaleImgResId(),itemView.mRightCenterScaleImgeView);
            }
        } else {
            itemView.mRightCenterScaleImgeLayout.setVisibility(View.GONE);
        }
    }
}
