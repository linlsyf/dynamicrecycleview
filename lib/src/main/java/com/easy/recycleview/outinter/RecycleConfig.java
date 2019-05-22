package com.easy.recycleview.outinter;

import com.easy.recycleview.custom.baseview.base.select.MutiTypeSelectUtils;
import com.easy.recycleview.custom.baseview.inter.IloadImage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/27 0027.
 */

public class RecycleConfig {
    static RecycleConfig factory;

    Map<Integer,Class>   defaultViewNameMap=new HashMap();

    static ThemeConfig  themeConfig;
   static  MutiTypeSelectUtils mSelectUtils;

    private IloadImage iloadImage;

    public static RecycleConfig getInstance( ){
        if (factory==null){
            factory=new RecycleConfig();

            mSelectUtils=new MutiTypeSelectUtils();
            themeConfig=new ThemeConfig();
        }

        return  factory;
    }

    public  Map<Integer,Class>  getDefaultViewNameMap() {
        return defaultViewNameMap;
    }

    public void setDefaultViewNameMap( Map<Integer,Class>  defaultViewNameMap) {
        this.defaultViewNameMap = defaultViewNameMap;
    }

    public MutiTypeSelectUtils getSelectUtils() {
        return mSelectUtils;
    }

    public void setSelectUtils(MutiTypeSelectUtils mSelectUtils) {
        this.mSelectUtils = mSelectUtils;
    }

    public void setIloadImage(IloadImage iloadImage) {
        this.iloadImage = iloadImage;
    }

    public IloadImage getIloadImage() {
        return iloadImage;
    }

    public ThemeConfig getThemeConfig() {
        return themeConfig;
    }

    public void setThemeConfig(ThemeConfig themeConfig) {
        this.themeConfig = themeConfig;
    }
}
