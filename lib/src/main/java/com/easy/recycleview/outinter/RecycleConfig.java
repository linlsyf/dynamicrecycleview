package com.easy.recycleview.outinter;

import com.easy.recycleview.custom.baseview.base.select.MutiTypeSelectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/27 0027.
 */

public class RecycleConfig {
    static RecycleConfig factory;
//    static  Context context;

    Map<Integer,Class>   defaultViewNameMap=new HashMap();
   static  MutiTypeSelectUtils mSelectUtils;
    public static RecycleConfig getInstance( ){
        if (factory==null){
            factory=new RecycleConfig();
        }
        if (mSelectUtils==null){
            mSelectUtils=new MutiTypeSelectUtils();
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
}
