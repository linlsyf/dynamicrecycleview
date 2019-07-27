package com.easy.recycleview.bean;

/**
 * Created by Administrator on 2019/6/29 0029.
 */

public class CentLayoutConfig {
    private  String name;
    private  int imgRadius=0;
    private  int imgResId =0;
    private  String imgUrl ="";

    public String getName() {
        return name;
    }

    public CentLayoutConfig setName(String name) {
        this.name = name;
        return  this;
    }

    public int getImgRadius() {
        return imgRadius;
    }

    public CentLayoutConfig setImgRadius(int imgRadius) {
        this.imgRadius = imgRadius;
        return  this;

    }

    public int getImgResId() {
        return imgResId;
    }

    public CentLayoutConfig setImgResId(int imgResId) {
        this.imgResId = imgResId;
        return  this;

    }

    public String getImgUrl() {
        return imgUrl;
    }

    public CentLayoutConfig setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return  this;

    }
}
