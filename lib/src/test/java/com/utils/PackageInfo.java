package com.utils;


import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 应用包信息工具类
 */
public class PackageInfo {

    /**
     * 获取应用包信息
     * @param context 环境
     * @return
     */
    public static android.content.pm.PackageInfo getPackageInfo(Context context) {
        android.content.pm.PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }
}
