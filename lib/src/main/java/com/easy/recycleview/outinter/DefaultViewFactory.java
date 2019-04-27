package com.easy.recycleview.outinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/27 0027.
 */

public class DefaultViewFactory {
    static  DefaultViewFactory factory;

    Map<Integer,Class>   defaultViewNameMap=new HashMap();

    public static DefaultViewFactory getInstance(){
        if (factory==null){
            factory=new DefaultViewFactory();

        }
        return  factory;
    }

    public  Map<Integer,Class>  getDefaultViewNameMap() {
        return defaultViewNameMap;
    }

    public void setDefaultViewNameMap( Map<Integer,Class>  defaultViewNameMap) {
        this.defaultViewNameMap = defaultViewNameMap;
    }
}
