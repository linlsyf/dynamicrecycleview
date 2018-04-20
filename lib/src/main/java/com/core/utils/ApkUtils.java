package com.core.utils;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/**
 * 管理apk的工具
 * @author ldh
 *
 */
public class ApkUtils {

//	private static String TAG = "ApkUtils";
	
	private static ApkUtils instance = null;
	
	private Context context;
	
	private ApkUtils(Context context){
		this.context = context;
	}
	
	public static ApkUtils getInstance(Context context){
			if(instance == null){
				synchronized(ApkUtils.class){
					instance = new ApkUtils(context);
				}
			}
			return instance;
	}
	
	/**
	 * 获取当前版本号(显示用)
	 * @return
	 */
	public  String getVersionName() {
		try {
			PackageInfo manager = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return manager.versionName;
		} catch (NameNotFoundException e) {
			return "Unknown";
		}
	}
	
	/**
	 * 获取当前版本号(开发用)
	 * @return
	 */
    public  int getVersionCode() {
		try {
			PackageInfo manager = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return manager.versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}
    
    /**
     * 获取当前应用名
     * @return
     */
    public String getApplicationName() { 
        PackageManager packageManager = null; 
        ApplicationInfo applicationInfo = null; 
        try { 
            packageManager = context.getPackageManager(); 
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0); 
        } catch (NameNotFoundException e) {
            applicationInfo = null; 
        } 
        String applicationName =  "";
        if(applicationInfo!=null){
        	applicationName =  (String) packageManager.getApplicationLabel(applicationInfo); 
        }
        return applicationName; 
    } 
    /**
     * 
     * <br>创建者：ldh
     * <br>修改时间：2015年10月21日 下午1:55:07 
     * <br>注释：获取应用信息
     * @return
     */
    public ApplicationInfo getApplicationInfo() { 
    	PackageManager packageManager = null; 
    	ApplicationInfo applicationInfo = null; 
    	try { 
    		packageManager = context.getPackageManager(); 
    		applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0); 
    	} catch (NameNotFoundException e) {
    		applicationInfo = null; 
    	} 
    	
    	return applicationInfo; 
    } 
    /**
     * 创建者：zw
     * 时间：2015-7-10 上午10:30:54
     * 注释：获取应用程序的Icon
     * @return
     */
    public Drawable getAppIcon() {
    	PackageManager packageManager = null; 
        ApplicationInfo applicationInfo = null; 
        try { 
            packageManager = context.getPackageManager(); 
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0); 
        } catch (NameNotFoundException e) {
            applicationInfo = null; 
        } 
        Drawable appIcon = null;
        if(applicationInfo!=null){
        	appIcon = packageManager.getApplicationIcon(applicationInfo);
        }
        return appIcon; 
	}
   
	
    /**
     * 根据应用名打开相关应用，失败时返回false
     * @param packageName
     * @return
     */
	public boolean openAppByPackageName(String packageName) {
		try {
			Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
			resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			resolveIntent.setPackage(packageName);
			List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
			if (resolveInfoList != null && resolveInfoList.size() > 0) {
				ResolveInfo resolveInfo = resolveInfoList.get(0);
				String activityPackageName = resolveInfo.activityInfo.packageName;
				String className = resolveInfo.activityInfo.name;

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				ComponentName componentName = new ComponentName(activityPackageName, className);

				intent.setComponent(componentName);
				context.startActivity(intent);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	 /**
     * 根据应用名卸载相关应用，失败时返回false
     * @param packageName
     * @return
     */
	public boolean deleteAppByPackageName(String packageName){
		try {
			Intent intent = new Intent(Intent.ACTION_DELETE);
			intent.setData(Uri.fromParts("package", packageName, null));
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
