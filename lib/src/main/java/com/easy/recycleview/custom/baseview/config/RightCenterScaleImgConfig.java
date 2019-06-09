package com.easy.recycleview.custom.baseview.config;

import android.view.View;
import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.DyItemBean;
import com.easy.recycleview.custom.bean.RightCenterScaleImgSettings;
import com.easy.recycleview.outinter.RecycleConfig;


/**
 * Created by Chant on 2018/6/17.
 */

public class RightCenterScaleImgConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
        if (dataItemBean.getRightCenterScaleImgSettings().getRightCenterScaleImgResId() != 0) {
            itemView. mRightCenterScaleImgeLayout.setVisibility(View.VISIBLE);

            RightCenterScaleImgSettings imgSettings=dataItemBean.getRightCenterScaleImgSettings();

            if (imgSettings.getLayoutWidth() != 0) {
                LinearLayout.LayoutParams mHeadParams = (LinearLayout.LayoutParams) itemView.mRightCenterScaleImgeLayout.getLayoutParams();

                mHeadParams.width = imgSettings.getLayoutWidth();

            }
            if (imgSettings.getImgRadius() != 0) {
                LinearLayout.LayoutParams mHeadParams = (LinearLayout.LayoutParams)   itemView.mRightCenterScaleImgeView.getLayoutParams();

                mHeadParams.width = imgSettings.getImgRadius();
                mHeadParams.height =imgSettings.getImgRadius();
                itemView.mRightCenterScaleImgeView.setLayoutParams(mHeadParams);

            }


            if ( RecycleConfig.getInstance().getIloadImage()!=null) {
                RecycleConfig.getInstance().getIloadImage().loadResourceId(dataItemBean.getRightCenterScaleImgSettings().getRightCenterScaleImgResId(), itemView.mRightCenterScaleImgeView);
            }
        } else {
            itemView.mRightCenterScaleImgeLayout.setVisibility(View.GONE);
        }
    }
}
