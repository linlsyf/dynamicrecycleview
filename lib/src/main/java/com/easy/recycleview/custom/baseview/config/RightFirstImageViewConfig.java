package com.easy.recycleview.custom.baseview.config;

import android.view.View;
import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;


/**
 * Created by Chant on 2018/6/17.
 */

public class RightFirstImageViewConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
        //添加右侧第一个图片如指向图片
        int  imgResId=dataItemBean.getRightFistImgeSettings().getRightFirstImgResId();
        if (imgResId!= 0) {
            LinearLayout.LayoutParams mRightFirstParams = (LinearLayout.LayoutParams) itemView.mRightFirstImageView.getLayoutParams();
            if (dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius() != 0) {
                mRightFirstParams.width = dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius();
                mRightFirstParams.height = dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius();
            }
            boolean isVisiable = dataItemBean.getRightFistImgeSettings().isVisiable();
            if (isVisiable) {
                itemView.mRightFirstImageView.setVisibility(View.VISIBLE);

            } else {
                itemView.mRightFirstImageView.setVisibility(View.INVISIBLE);

            }
            itemView.mRightFirstImageView.setLayoutParams(mRightFirstParams);
            if ( RecycleConfig.getInstance().getIloadImage()!=null){
                RecycleConfig.getInstance().getIloadImage().loadResourceId(imgResId,itemView.mRightFirstImageView);
            }
        } else {
            itemView.mRightFirstImageView.setVisibility(View.GONE);
        }

    }
}
