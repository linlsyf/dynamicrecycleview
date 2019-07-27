package com.easy.recycleview.bean;

/**
 * Created by Administrator on 2019/5/1 0001.
 */

public class HintSettings {
    float textSize=16;
    int color=0;

    public float getTextSize() {
        return textSize;
    }

    public HintSettings setTextSize(float textSize) {
        this.textSize = textSize; return  this ;
    }

    public int getColor() {
        return color;
    }

    public HintSettings setColor(int color) {
        this.color = color; return  this ;
    }
}
