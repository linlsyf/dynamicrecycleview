package com.easy.recycleview.recycleview.item;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.easy.recycleview.utils.StringUtils;

/**
 * Created by lindanghong on 2018/6/4.
 */

public class HeadImageViewConfig {
    public static void load(AddressItemBean dataItemBean,ImageView mImageView){
        if (null!=dataItemBean.getHeadImgeSettings()) {//添加头像资源id
            mImageView.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams mHeadParams = (LinearLayout.LayoutParams) mImageView.getLayoutParams();
            mHeadParams.gravity = Gravity.CENTER_VERTICAL;

            if (dataItemBean.getHeadImgeSettings().getHeadImgWidth() != 0&&dataItemBean.getHeadImgeSettings().getHeadImgHeight() !=0){
                mHeadParams.width = dataItemBean.getHeadImgeSettings().getHeadImgWidth();
                mHeadParams.height = dataItemBean.getHeadImgeSettings().getHeadImgHeight();
            }else  if (dataItemBean.getHeadImgeSettings().getHeadImgRadius() != 0) {
                mHeadParams.width = dataItemBean.getHeadImgeSettings().getHeadImgRadius();
                mHeadParams.height = dataItemBean.getHeadImgeSettings().getHeadImgRadius();
            }
            mImageView.setLayoutParams(mHeadParams);
        } else {
            mImageView.setVisibility(View.GONE);
        }
        if (dataItemBean.getHeadImgeSettings().getHeadImgDrawableId() != 0) {
            if (dataItemBean.getIloadImage()!=null){
                dataItemBean.getIloadImage().loadResourceId(dataItemBean.getHeadImgeSettings().getHeadImgDrawableId(),mImageView);
            }
        }
        else if (StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgPath())) {
            if (dataItemBean.getIloadImage()!=null) {
                dataItemBean.getIloadImage().loadPath(dataItemBean.getHeadImgeSettings().getHeadImgPath(), mImageView);
            }
        }
        else if (StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgUrl())) {
            if (dataItemBean.getIloadImage()!=null) {
                dataItemBean.getIloadImage().load(dataItemBean.getHeadImgeSettings().getHeadImgUrl(), mImageView);
            }
        }
        else if (null!=dataItemBean.getHeadImgeSettings().getBitmap()) {
            if (dataItemBean.getIloadImage()!=null) {
                dataItemBean.getIloadImage().load(dataItemBean.getHeadImgeSettings().getBitmap(), mImageView);
            }
        }
    }
}
