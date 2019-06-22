package com.easy.recycleview.custom.bean;


import java.io.Serializable;

/**
 *创建者：林党宏
 *时间：2017/4/24
 *注释：右侧第二个图片的数据封装对象
 */

public class RightSecondImgSettings implements Serializable {
    /**用于区分不同业务如群还是人 实现不同头像加载*/
    private  String headLoadType ="";
    /**右侧第二个图片*/
    private  int rightSecondImgResId =0;
    /**右侧侧第二个图片宽高 */
    private  int rightSecondImgRadius=0;
    /**右侧第二个图片  头像用户url*/
    private  String rightSecondImgURL ="";
    /**右侧第二个图片  头像storePath*/
    private  String rightSecondImgStorePath ="";
    /**右侧第二个图片  头像用户id*/
    private  String rightSecondImgUserName ="";
   /**当id为空时是否显示空图片*/
    private  boolean showEmptyImg=false;
    public String getHeadLoadType() {
        return headLoadType;
    }

    public RightSecondImgSettings setHeadLoadType(String headLoadType) {
        this.headLoadType = headLoadType; return  this ;
    }

    public int getRightSecondImgResId() {
        return rightSecondImgResId;
    }

    public RightSecondImgSettings setRightSecondImgResId(int rightSecondImgResId) {
        this.rightSecondImgResId = rightSecondImgResId; return  this ;
    }
    
    public String getRightSecondImgURL() {
		return rightSecondImgURL;
	}

	public RightSecondImgSettings setRightSecondImgURL(String rightSecondImgURL) {
		this.rightSecondImgURL = rightSecondImgURL; return  this ;
	}

	public String getRightSecondImgUserName() {
        return rightSecondImgUserName;
    }

    public RightSecondImgSettings setRightSecondImgUserName(String rightSecondImgUserName) {
        this.rightSecondImgUserName = rightSecondImgUserName; return  this ;
    }

    public int getRightSecondImgRadius() {
        return rightSecondImgRadius;
    }

    public RightSecondImgSettings setRightSecondImgRadius(int rightSecondImgRadius) {
        this.rightSecondImgRadius = rightSecondImgRadius; return  this ;
    }

    public boolean isShowEmptyImg() {
        return showEmptyImg;
    }

    public RightSecondImgSettings setShowEmptyImg(boolean showEmptyImg) {
        this.showEmptyImg = showEmptyImg; return  this ;
    }

	public String getRightSecondImgStorePath() {
		return rightSecondImgStorePath;
	}

	public RightSecondImgSettings setRightSecondImgStorePath(String rightSecondImgStorePath) {
		this.rightSecondImgStorePath = rightSecondImgStorePath;  return  this ;
	}
    
    
}
