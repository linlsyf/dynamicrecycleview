package com.core.utils.system;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class AndroidUtil {

	private static final String TAG = "AndroidUtil";

	private static List<String> INVALID_IMEIs = new ArrayList<String>();
	static {
		INVALID_IMEIs.add("358673013795895");
		INVALID_IMEIs.add("004999010640000");
		INVALID_IMEIs.add("00000000000000");
		INVALID_IMEIs.add("000000000000000");
	}

	/**
	 * 
	 * 创建者：qjt 时间：2015-4-14 下午5:17:53 注释：判读应用是否在前台运行
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isAppOnForeground(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = context.getPackageName();
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
		if (tasksInfo.size() > 0) {
			// 应用程序位于堆栈的顶层
			if (packageName.equals(tasksInfo.get(0).topActivity.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <br>
	 * 创建者：zw <br>
	 * 时间：2015-5-26 下午4:28:11 <br>
	 * 注释：将按Back按钮转换为按Home键一样的效果
	 * 
	 * @param context
	 */
	public static void setAppOnBackground(Context context) {

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		context.startActivity(intent);

		((Activity) context).moveTaskToBack(true);// 移动任务包含此活动活动栈的后面。在任务活动的顺序不变（如为false，退出）
	}
	
	
	// private static long firstTime = 0;
	// /**
	// * 创建者：zw
	// * 时间：2015-5-26 下午4:38:18
	// * 注释：用于FragmentActivity按返回3秒内连续按两次，把app退到后台
	// * @param context
	// */
	// public static void onBackAction(FragmentActivity context){
	// if (System.currentTimeMillis() - firstTime < 3000) {
	// onAppBack(context);
	// } else {
	// firstTime = System.currentTimeMillis();
	// Toast.makeText(context, "请连续按两次退到后台",Toast.LENGTH_SHORT).show();
	// }
	// }
	/**
	 * 创建者：zw 时间：2015-5-26 下午4:47:28
	 * 注释：用于FragmentActivity按返回把app退到后台或者返回到主界面，当主界面没有子Fragment的时候
	 * ，直接以home的方式退到后台
	 * 
	 * @param context
	 */
	public static void onAppBack(FragmentActivity context) {
		FragmentManager mFragmentManager = context.getSupportFragmentManager();
		int BackStackCount = mFragmentManager.getBackStackEntryCount();
		if (BackStackCount > 0) {
			mFragmentManager.popBackStack();
		} else {
			// 与home键相似的作用退到后台
			setAppOnBackground(context);
		}
	}

	public static boolean isAppIsRunning(Context context, String MY_PKG_NAME) {
		boolean isAppRunning = false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(100);
		for (RunningTaskInfo info : list) {
			if (info.topActivity.getPackageName().equals(MY_PKG_NAME) && info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
				isAppRunning = true;
				// find it, break
				break;
			}
		}

		return isAppRunning;
	}

	public static boolean isAppIsRunning(Context context, String MY_PKG_NAME, String activityClassNameName) {
		boolean isAppRunning = false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(100);
		for (RunningTaskInfo info : list) {

			
//			
//			Log.v("topActivity.getClassName()", info.topActivity.getClassName());
//			Log.v("activityClassNameName",activityClassNameName);
//			
			activityClassNameName="com.android.internal.app.ResolverActivity";
			
			
			if (info.topActivity.getClassName().equals(activityClassNameName)) {
				isAppRunning = true;
				break;
			}
		}


		return isAppRunning;
	}
	public static boolean isRunning(Context context, String packageName) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		for (RunningAppProcessInfo rapi : infos) {
			if (rapi.processName.equals(packageName))
				return true;
		}
		return false;
	}
	
	public static boolean appIsRunning(Context ctx, String packageName) {
		@SuppressWarnings("static-access")
		ActivityManager am = (ActivityManager) ctx.getSystemService(ctx.ACTIVITY_SERVICE);

		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

		if (runningAppProcesses != null) {
			for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {

				if (runningAppProcessInfo.processName.startsWith(packageName)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean isTopActivy(Context context, String cmdName) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
		String cmpNameTemp = null;
		if (null != runningTaskInfos) {
			cmpNameTemp = (runningTaskInfos.get(0).topActivity).getClassName().toString();
			// Log.e("cmpname","cmpname:"+cmpName);

			Log.e("cmpname", cmpNameTemp);
		}
		if (null == cmpNameTemp)
			return false;
		return cmpNameTemp.equals(cmdName);
	}
}
