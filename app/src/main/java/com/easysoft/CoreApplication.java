package com.easysoft;

import android.app.Application;

import com.easysoft.DebugUtlis.CrashHandler;
import com.easysoft.baseview.config.ImgloadConfig;
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
        CrashHandler.getInstance().init(this);

    }
    public static CoreApplication getAppContext() {
        return instance;
    }
}
