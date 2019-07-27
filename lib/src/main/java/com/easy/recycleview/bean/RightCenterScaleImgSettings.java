package com.easy.recycleview.bean;

/**
 * Created by Administrator on 2019/6/9 0009.
 */

public class RightCenterScaleImgSettings {

    /**右侧可点击图片资源id */
    private  int rightCenterScaleImgResId=0;
    private  int imgRadius=0;
    private  int layoutWidth=0;

    public int getRightCenterScaleImgResId() {
        return rightCenterScaleImgResId;
    }

    public RightCenterScaleImgSettings setRightCenterScaleImgResId(int rightCenterScaleImgResId) {
        this.rightCenterScaleImgResId = rightCenterScaleImgResId; return  this ;
    }

    public int getImgRadius() {
        return imgRadius;
    }

    public RightCenterScaleImgSettings setImgRadius(int imgRadius) {
        this.imgRadius = imgRadius; return  this ;
    }

    public int getLayoutWidth() {
        return layoutWidth;
    }

    public RightCenterScaleImgSettings setLayoutWidth(int layoutWidth) {
        this.layoutWidth = layoutWidth; return  this ;
    }
}
