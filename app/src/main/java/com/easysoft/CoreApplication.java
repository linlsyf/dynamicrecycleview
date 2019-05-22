package com.easysoft;

import android.app.Application;

import com.easy.recycleview.outinter.RecycleConfig;
import com.easy.recycleview.outinter.ThemeConfig;
import com.easysoft.DebugUtlis.CrashHandler;
import com.easysoft.dyview.ImageUtils;
import com.easysoft.dyview.R;

/**
 * Created by lindanghong on 2018/4/20.
 */

public class CoreApplication extends Application {
    public static CoreApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RecycleConfig.getInstance().setIloadImage(ImageUtils.getInStance());
        ThemeConfig themeConfig=new ThemeConfig();
        themeConfig.setTitleColorResId(this.getResources().getColor(R.color.textcolor_main_normal));
        themeConfig.setHintColorResId(this.getResources().getColor(R.color.bg_second_normal));
        themeConfig.setBgColorResId(this.getResources().getColor(R.color.bg_main_dark));
        RecycleConfig.getInstance().setThemeConfig(themeConfig);

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
