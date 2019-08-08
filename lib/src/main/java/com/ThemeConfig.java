package com;

import com.easy.recycleview.outinter.IDyviewThemeConfig;

/**
 * Created by Administrator on 2019/5/22 0022.
 */

public class ThemeConfig implements IDyviewThemeConfig {
    int  titleColorResId=0;
    int  hintColorResId=0;
    int  bgColorResId=0;
    int  bgResourcResId=0;

    public int getTitleColorResId() {
        return titleColorResId;
    }

    public ThemeConfig setTitleColorResId(int titleColorResId) {
        this.titleColorResId = titleColorResId;
        return this;
    }

    public int getHintColorResId() {
        return hintColorResId;
    }

    public ThemeConfig setHintColorResId(int hintColorResId) {
        this.hintColorResId = hintColorResId;        return this;

    }

    public int getBgColorResId() {
        return bgColorResId;
    }

    public ThemeConfig setBgColorResId(int bgColorResId) {
        this.bgColorResId = bgColorResId;        return this;

    }

    public int getBgResourcResId() {
        return bgResourcResId;
    }

    public ThemeConfig setBgResourcResId(int bgResourcResId) {
        this.bgResourcResId = bgResourcResId;        return this;

    }
}
