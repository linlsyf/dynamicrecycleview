package com.easy.recycleview.bean;

import android.graphics.Bitmap;

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
    /**头像资源文件 本地路径*/
    private String headImgPath ="";
    /**头像半径大小*/
    private int headImgRadius =0;

    private int headImgWidth = 0;

    private int headImgHeight = 0;

    private Bitmap bitmap;


    public int getHeadImgDrawableId() {
        return headImgDrawableId;
    }

    public AddressHeadImgeSettings setHeadImgDrawableId(int headImgDrawableId) {
        this.headImgDrawableId = headImgDrawableId;

        return this;
    }

    public int getHeadImgRadius() {
        return headImgRadius;
    }

    public AddressHeadImgeSettings setHeadImgRadius(int headImgRadius) {
        this.headImgRadius = headImgRadius;
        return  this ;
    }

    public int getHeadImgWidth() {
        return headImgWidth;
    }

    public AddressHeadImgeSettings setHeadImgWidth(int headImgWidth) {
        this.headImgWidth = headImgWidth;
        return  this ;
    }

    public int getHeadImgHeight() {
        return headImgHeight;
    }

    public AddressHeadImgeSettings setHeadImgHeight(int headImgHeight) {
        this.headImgHeight = headImgHeight;
        return  this ;
    }


	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public AddressHeadImgeSettings setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
        return  this ;
	}

    public String getHeadImgPath() {
        return headImgPath;
    }

    public AddressHeadImgeSettings setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
        return  this ;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public AddressHeadImgeSettings setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return  this ;
    }
}
