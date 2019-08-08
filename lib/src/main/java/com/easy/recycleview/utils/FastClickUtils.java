package com.easy.recycleview.utils;

import android.widget.Toast;

/**
 * Created by Administrator on 2019/7/27 0027.
 */

public class FastClickUtils {
    private static long mLastClickTime = 0;
    public static final long TIME_INTERVAL = 1000L;

    public static boolean   isNotFastClick(){
        long nowTime = System.currentTimeMillis();
        boolean isFastClick=false;
        if (nowTime - mLastClickTime > TIME_INTERVAL) {
            mLastClickTime = nowTime;
            isFastClick=true;
        }
        return  isFastClick;
    }

}
