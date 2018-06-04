package com.easy.recycleview.recycleview.item;

import android.widget.ImageView;

import com.easy.recycleview.utils.StringUtils;

/**
 * Created by lindanghong on 2018/6/4.
 */

public class HeadImageViewConfig {
    public static void load(AddressItemBean dataItemBean,ImageView mImageView){
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
