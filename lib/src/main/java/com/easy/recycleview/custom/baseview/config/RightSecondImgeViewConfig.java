package com.easy.recycleview.custom.baseview.config;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.DensityUtil;
import com.easy.recycleview.custom.baseview.utils.StringUtils;
import com.easy.recycleview.custom.bean.DyItemBean;
import com.easy.recycleview.custom.bean.RightSecondImgSettings;
import com.easy.recycleview.outinter.RecycleConfig;
import com.easysoft.dynamicrecycleview.R;


/**
 * Created by Chant on 2018/6/17.
 */

public class RightSecondImgeViewConfig {

    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
        //添加右侧图片显示如头像
        RightSecondImgSettings secondImgSetting=dataItemBean.getRightSecondImgSettings();
        if (StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgURL())||StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgStorePath()) ||secondImgSetting.getRightSecondImgResId() != 0) {
            LinearLayout.LayoutParams mHeadParams = (LinearLayout.LayoutParams) itemView.mRightSecondImgeView.getLayoutParams();
            mHeadParams.gravity = Gravity.CENTER_VERTICAL;
            if (dataItemBean.getRightSecondImgSettings().getRightSecondImgRadius() != 0) {
                mHeadParams.width = dataItemBean.getRightSecondImgSettings().getRightSecondImgRadius();
                mHeadParams.height = dataItemBean.getRightSecondImgSettings().getRightSecondImgRadius();
            }
            int contentMargin = DensityUtil.dip2px(itemView.getContext(),5);
            mHeadParams.rightMargin = contentMargin;
            itemView.mRightSecondImgeView.setLayoutParams(mHeadParams);
            if (StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgURL())) {
                itemView.mRightSecondImgeView.setVisibility(View.VISIBLE);
                if ( RecycleConfig.getInstance().getIloadImage() != null) {
                     RecycleConfig.getInstance().getIloadImage().load(secondImgSetting.getRightSecondImgURL(), itemView.mRightSecondImgeView);
                }
            }
            else if (StringUtils.isNotEmpty(dataItemBean.getRightSecondImgSettings().getRightSecondImgStorePath())) {
                itemView.mRightSecondImgeView.setVisibility(View.VISIBLE);
                if ( RecycleConfig.getInstance().getIloadImage()!=null){
                     RecycleConfig.getInstance().getIloadImage().loadPath(dataItemBean.getRightSecondImgSettings().getRightSecondImgStorePath(), itemView.mRightSecondImgeView);
                }
            }
            else if (dataItemBean.getRightSecondImgSettings().getRightSecondImgResId() != 0) {
                itemView.mRightSecondImgeView.setVisibility(View.VISIBLE);
                if ( RecycleConfig.getInstance().getIloadImage() != null) {
                     RecycleConfig.getInstance().getIloadImage().loadResourceId(dataItemBean.getRightSecondImgSettings().getRightSecondImgResId(), itemView.mRightSecondImgeView);
                }
            }

        } else {
            boolean isShowEmptyImge = dataItemBean.getRightSecondImgSettings().isShowEmptyImg();
            if (isShowEmptyImge) {
                itemView.mRightSecondImgeView.setVisibility(View.VISIBLE);
                if ( RecycleConfig.getInstance().getIloadImage()!=null){
                     RecycleConfig.getInstance().getIloadImage().loadResourceId(R.drawable.empty_photo,itemView.mRightSecondImgeView);
                }
            } else {
                itemView.mRightSecondImgeView.setVisibility(View.GONE);
            }
        }
    }
}
