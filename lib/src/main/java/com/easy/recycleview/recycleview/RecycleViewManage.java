package com.easy.recycleview.recycleview;

import com.easy.recycleview.recycleview.item.inter.IloadImage;

/**
 * Created by lindanghong on 2018/4/20.
 */

public class RecycleViewManage {
    private IloadImage iloadImage;
    static RecycleViewManage  utils;
    public static RecycleViewManage getInStance(){
        if(utils==null){
            utils=new    RecycleViewManage();
        }
        return  utils;
    }

    public IloadImage getIloadImage() {
        return iloadImage;
    }

    public void setIloadImage(IloadImage iloadImage) {
        this.iloadImage = iloadImage;
    }
}
