package com.easysoft.baseview.config;

import android.view.View;

import com.easysoft.baseview.item.ContentItemView;
import com.easysoft.bean.AddressItemBean;

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
