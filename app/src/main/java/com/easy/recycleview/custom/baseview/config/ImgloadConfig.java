package com.easy.recycleview.custom.baseview.config;


import com.easy.recycleview.custom.baseview.inter.IloadImage;

/**
 * Created by Administrator on 2019/4/10 0010.
 */

public class ImgloadConfig {

    private static ImgloadConfig instance; // 单例引用，这里我们做成单例的，因为我们一个应用程序里面只需要一个UncaughtExceptionHandler实例

    private IloadImage iloadImage;
    private ImgloadConfig() {
    }

    public synchronized static ImgloadConfig getInstance() { // 同步方法，以免单例多线程环境下出现异常
        if (instance == null) {
            instance = new ImgloadConfig();
        }
        return instance;
    }

    public void setIloadImage(IloadImage iloadImage) {
        this.iloadImage = iloadImage;
    }

    public IloadImage getIloadImage() {
        return iloadImage;
    }
}
