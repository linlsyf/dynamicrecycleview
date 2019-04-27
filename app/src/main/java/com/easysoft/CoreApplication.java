package com.easysoft;

import android.app.Application;

import com.easy.recycleview.custom.baseview.config.ImgloadConfig;
import com.easysoft.DebugUtlis.CrashHandler;
import com.easysoft.dyview.ImageUtils;

/**
 * Created by lindanghong on 2018/4/20.
 */

public class CoreApplication extends Application {
    public static CoreApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ImgloadConfig.getInstance().setIloadImage(ImageUtils.getInStance());

//        Map<Integer,Class> defaultViewNameMap=new HashMap<>();
//        defaultViewNameMap.put(IItemView.ViewTypeEnum.ITEM.value(), ContentItemView.class);
//        defaultViewNameMap.put(IItemView.ViewTypeEnum.SECTION.value(), SectionView.class);
//        defaultViewNameMap.put(IItemView.ViewTypeEnum.SPLITE.value(), SpliteView.class);
//        RecycleConfig.getInstance().setDefaultViewNameMap(defaultViewNameMap);

        CrashHandler.getInstance().init(this);

    }
    public static CoreApplication getAppContext() {
        return instance;
    }
}
