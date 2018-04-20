package com.core.recycleview.item.bean;

import android.view.View;

import java.io.Serializable;

/**
 * Created by ldh on 2017/4/24.
 */

public class AddressRightFistImgeSettings implements Serializable {
    /**右侧第一个图片半径宽度*/
    private  int rightFirstImgRadius=0;
    /**右侧图片如箭头指向，加载图片*/
    private  int rightFirstImgResId =0;
    /**是否隐藏 只隐藏不gone */
    private  boolean  isInvisiable=false;

    public int getRightFirstImgResId() {
        return rightFirstImgResId;
    }

    public void setRightFirstImgResId(int rightFirstImgResId) {
        this.rightFirstImgResId = rightFirstImgResId;
    }

    public int getRightFirstImgRadius() {
        return rightFirstImgRadius;
    }

    public void setRightFirstImgRadius(int rightFirstImgRadius) {
        this.rightFirstImgRadius = rightFirstImgRadius;
    }

    public boolean isInvisiable() {
        return isInvisiable;
    }

    public void setInvisiable(boolean invisiable) {
        isInvisiable = invisiable;
    }
}
