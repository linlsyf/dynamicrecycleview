package com.easysoft;

import android.app.Application;

import com.easy.recycleview.custom.baseview.config.ImgloadConfig;
import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.baseview.item.SectionView;
import com.easy.recycleview.custom.baseview.item.SpliteView;
import com.easy.recycleview.outinter.DefaultViewFactory;
import com.easysoft.DebugUtlis.CrashHandler;
import com.easysoft.dyview.ImageUtils;

import java.util.HashMap;
import java.util.Map;

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

        Map<Integer,Class> defaultViewNameMap=new HashMap<>();

        defaultViewNameMap.put(0, ContentItemView.class);
        defaultViewNameMap.put(1, SectionView.class);
        defaultViewNameMap.put(2, SpliteView.class);
        DefaultViewFactory.getInstance().setDefaultViewNameMap(defaultViewNameMap);
        CrashHandler.getInstance().init(this);

    }
    public static CoreApplication getAppContext() {
        return instance;
    }
}
