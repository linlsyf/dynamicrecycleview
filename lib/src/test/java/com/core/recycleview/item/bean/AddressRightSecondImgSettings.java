package com.core.recycleview.item.bean;

import java.io.Serializable;

import com.core.recycleview.item.AddressItemImgEnum;

/**
 *创建者：林党宏
 *时间：2017/4/24
 *注释：右侧第二个图片的数据封装对象
 */

public class AddressRightSecondImgSettings implements Serializable {
    /**用于区分不同业务如群还是人 实现不同头像加载*/
    private  String headLoadType = AddressItemImgEnum.USER.toString();
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

    public void setHeadLoadType(String headLoadType) {
        this.headLoadType = headLoadType;
    }

    public int getRightSecondImgResId() {
        return rightSecondImgResId;
    }

    public void setRightSecondImgResId(int rightSecondImgResId) {
        this.rightSecondImgResId = rightSecondImgResId;
    }
    
    public String getRightSecondImgURL() {
		return rightSecondImgURL;
	}

	public void setRightSecondImgURL(String rightSecondImgURL) {
		this.rightSecondImgURL = rightSecondImgURL;
	}

	public String getRightSecondImgUserName() {
        return rightSecondImgUserName;
    }

    public void setRightSecondImgUserName(String rightSecondImgUserName) {
        this.rightSecondImgUserName = rightSecondImgUserName;
    }

    public int getRightSecondImgRadius() {
        return rightSecondImgRadius;
    }

    public void setRightSecondImgRadius(int rightSecondImgRadius) {
        this.rightSecondImgRadius = rightSecondImgRadius;
    }

    public boolean isShowEmptyImg() {
        return showEmptyImg;
    }

    public void setShowEmptyImg(boolean showEmptyImg) {
        this.showEmptyImg = showEmptyImg;
    }

	public String getRightSecondImgStorePath() {
		return rightSecondImgStorePath;
	}

	public void setRightSecondImgStorePath(String rightSecondImgStorePath) {
		this.rightSecondImgStorePath = rightSecondImgStorePath;
	}
    
    
}
