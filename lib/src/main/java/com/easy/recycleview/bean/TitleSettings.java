package com.easy.recycleview.bean;

/**
 * Created by Administrator on 2019/5/1 0001.
 */

public class TitleSettings {

    float textSize=16;
    int color=0;
      int lines=0;
    public float getTextSize() {
        return textSize;
    }

    public TitleSettings setTextSize(float textSize) {
        this.textSize = textSize;
        return  this ;

    }

    public int getColor() {
        return color;
    }

    public TitleSettings setColor(int color) {
        this.color = color;
        return  this ;

    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}
