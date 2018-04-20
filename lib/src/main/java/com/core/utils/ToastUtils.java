package com.core.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	// 直接给字符串提示信息
	public static void show(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	// 从资源文件stringx.xml获取提示信息
	public static void show(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
	
	}
	
	/** 
     * 获取当前 jvm 的内存信息 
     * @return 
     */  
   public static String toMemoryInfo() {  
 
      Runtime currRuntime = Runtime.getRuntime ();  
 
      int nFreeMemory = ( int ) (currRuntime.freeMemory() / 1024 / 1024);  
 
      int nTotalMemory = ( int ) (currRuntime.totalMemory() / 1024 / 1024);  
 
      return nFreeMemory + "M/" + nTotalMemory + "M(free/total)" ;  
 
   }  
 
   
   static Toast mToast;




	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 */
	public static void showLong(Context context, int resid) {
		if (mToast == null) {
			mToast = Toast.makeText(context, resid, Toast.LENGTH_LONG);
		} else {
			mToast.setDuration(Toast.LENGTH_LONG);
			mToast.setText(resid);
		}
		mToast.show();
		// Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	public static void showShort(String mHasSelectNoticeText) {
		// TODO Auto-generated method stub
		
	}
}
