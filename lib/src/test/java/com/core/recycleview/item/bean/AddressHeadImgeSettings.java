package com.core.recycleview.item.bean;

import java.io.Serializable;

/**
 * Created by ldh on 2017/4/24.
 */

public class AddressHeadImgeSettings implements Serializable {
//    private String headImgType= ChatType.USER.getCode();
    /**头像资源文件*/
    private int headImgDrawableId =0;
    /**头像资源文件*/
    private String headImgUrl ="";
    /**头像半径大小*/
    private int headImgRadius =0;

    private int headImgWidth = 0;

    private int headImgHeight = 0;

    /**头像用户id*/
    private  String headImgUserId ="";
    /**头像用户name*/
    private  String headImgUserName ="";

//    public String getHeadImgType() {
//        return headImgType;
//    }
//
//    public void setHeadImgType(String headImgType) {
//        this.headImgType = headImgType;
//    }

    public int getHeadImgDrawableId() {
        return headImgDrawableId;
    }

    public void setHeadImgDrawableId(int headImgDrawableId) {
        this.headImgDrawableId = headImgDrawableId;
    }

    public int getHeadImgRadius() {
        return headImgRadius;
    }

    public void setHeadImgRadius(int headImgRadius) {
        this.headImgRadius = headImgRadius;
    }

    public int getHeadImgWidth() {
        return headImgWidth;
    }

    public void setHeadImgWidth(int headImgWidth) {
        this.headImgWidth = headImgWidth;
    }

    public int getHeadImgHeight() {
        return headImgHeight;
    }

    public void setHeadImgHeight(int headImgHeight) {
        this.headImgHeight = headImgHeight;
    }

    public String getHeadImgUserId() {
        return headImgUserId;
    }

    public void setHeadImgUserId(String headImgUserId) {
        this.headImgUserId = headImgUserId;
    }

    public String getHeadImgUserName() {
        return headImgUserName;
    }

    public void setHeadImgUserName(String headImgUserName) {
        this.headImgUserName = headImgUserName;
    }

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
    
    
}
