package com.easy.recycleview.custom.bean;

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
    private  boolean  isVisiable=true;

    public int getRightFirstImgResId() {
        return rightFirstImgResId;
    }

    public AddressRightFistImgeSettings setRightFirstImgResId(int rightFirstImgResId) {
        this.rightFirstImgResId = rightFirstImgResId;
        return  this ;

    }

    public int getRightFirstImgRadius() {
        return rightFirstImgRadius;
    }

    public AddressRightFistImgeSettings setRightFirstImgRadius(int rightFirstImgRadius) {
        this.rightFirstImgRadius = rightFirstImgRadius;
        return  this ;

    }

    public boolean isVisiable() {
        return isVisiable;
    }

    public AddressRightFistImgeSettings setVisiable(boolean visiable) {
        isVisiable = visiable;
        return  this ;

    }
}
