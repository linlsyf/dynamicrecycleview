package com.easy.recycleview.custom.baseview.config;

import android.view.View;
import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.DyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;


/**
 * Created by Chant on 2018/6/17.
 */

public class RightFirstImageViewConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
        //添加右侧第一个图片如指向图片
        if (dataItemBean.getRightFistImgeSettings().getRightFirstImgResId() != 0) {
            LinearLayout.LayoutParams mRightFirstParams = (LinearLayout.LayoutParams) itemView.mRightFirstImageView.getLayoutParams();
            if (dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius() != 0) {
                mRightFirstParams.width = dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius();
                mRightFirstParams.height = dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius();
            }
            boolean isInvisiable = dataItemBean.getRightFistImgeSettings().isInvisiable();
            if (isInvisiable) {
                itemView.mRightFirstImageView.setVisibility(View.INVISIBLE);
            } else {
                itemView.mRightFirstImageView.setVisibility(View.VISIBLE);
            }
            itemView.mRightFirstImageView.setLayoutParams(mRightFirstParams);
            if ( RecycleConfig.getInstance().getIloadImage()!=null){
                RecycleConfig.getInstance().getIloadImage().loadResourceId(dataItemBean.getRightFistImgeSettings().getRightFirstImgResId(),itemView.mRightFirstImageView);
            }
        } else {
            itemView.mRightFirstImageView.setVisibility(View.GONE);
        }

    }
}
