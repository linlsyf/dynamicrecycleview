package com.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;


import com.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 应用信息工具类
 */
public class AppInfo {

    /**
     * 获取APP应用版本号
     *
     * @param context 环境
     * @return
     */
    public static String getAppVersion(Context context) {
        return PackageInfo.getPackageInfo(context).versionName;
    }


    public static int getAppVersionCode(Context context) {
        return PackageInfo.getPackageInfo(context).versionCode;
    }

    public static String getAppLocalizedVerion(Context context) {

        String versionStr = PackageInfo.getPackageInfo(context).versionName;
        if (StringUtils.isEmpty(versionStr)) versionStr = "";
        versionStr += "(b" + PackageInfo.getPackageInfo(context).versionCode + ")";
        return versionStr;
    }

    /**
     * 获取APP应用名字
     *
     * @param context 环境
     * @return
     */
    public static String getAppName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(PackageInfo.getPackageInfo(context).packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    ;

    /**
     * 获取APP唯一标识码
     *
     * @param context 环境
     * @return
     */
    public static String getAppUUID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        return DEVICE_ID;
    }

    /**
     * 获取APP应用包名
     *
     * @param context 环境
     * @return
     */
    public static String getAppPackageId(Context context) {
        return PackageInfo.getPackageInfo(context).packageName;
    }

    /**
     * 生成唯一标识符
     *
     * @return
     */
    public static String createUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * 读取当前时间年月日毫秒后String返回
     *
     * @return
     */
    public static long createMsgIdTag() {
        return System.currentTimeMillis();
    }


    /**
     * 判读应用是否在前台运行
     *
     * @param context 环境
     * @return
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getPackageName();
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // 应用程序位于堆栈的顶层
            if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

    public static boolean isMainProcessRunning(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.processName != null && appProcess.processName.equalsIgnoreCase(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMainProcess(Context context) {
        String processName = getCurrentProcessName(context);
        return context.getPackageName().equalsIgnoreCase(processName);
    }

    public static boolean isNetWorkConn(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mActiveInfo = mConnectivityManager.getActiveNetworkInfo();
        return (mActiveInfo != null && mActiveInfo.isAvailable()
                && "CONNECTED".equals(mActiveInfo.getState().name()));
    }

    public static boolean isOnWifi(Context context) {
        return networkIsOn(context, true);
    }

    public static boolean isOnMobile(Context context) {
        return networkIsOn(context, false);
    }

    private static boolean networkIsOn(Context context, boolean wifi) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mActiveInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mActiveInfo != null && mActiveInfo.isAvailable() && "CONNECTED".equals(mActiveInfo.getState().name())) {
            String name = mActiveInfo.getTypeName();
            if (wifi) {
                return "WIFI".equals(name);
            } else {
                return "MOBILE".equals(name);
            }
        }
        return false;
    }

    public static boolean isIntentAvailable(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.GET_RESOLVED_FILTER);
        return list.size() > 0;
    }

    public static boolean isAllServiceRunning(Context context, String... serviceNames) {
        if (serviceNames == null || serviceNames.length <= 0) {
            return true;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(128);
        if (runningServices.size() <= 0) {
            return false;
        }
        List<String> nameList = new ArrayList<>();
        Collections.addAll(nameList, serviceNames);
        boolean single = nameList.size() == 1;
        for (int i = 0; i < runningServices.size(); i++) {
            String runningName = runningServices.get(i).service.getClassName();
            if (single) {
                if (runningName.equals(nameList.get(0))) {
                    return true;
                }
            } else {
                boolean remove = nameList.remove(runningName);
                if (remove && nameList.size() == 0) {
                    return true;
                }
            }

        }
        return false;
    }

}
