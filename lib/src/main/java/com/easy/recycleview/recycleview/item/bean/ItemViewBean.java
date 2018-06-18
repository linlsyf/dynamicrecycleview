package com.easy.recycleview.recycleview.item.bean;

import android.view.View;

/**
 * Created by Chant on 2018/6/17.
 */

public class ItemViewBean {
    private int type=0;
    private View typeView;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public View getTypeView() {
        return typeView;
    }

    public void setTypeView(View typeView) {
        this.typeView = typeView;
    }
}
