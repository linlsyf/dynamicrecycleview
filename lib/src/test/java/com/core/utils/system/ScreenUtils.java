package com.core.utils.system;

import android.app.Activity;
import android.content.Context;

public class ScreenUtils {

	
	
	
	
	public static int getScreenWidth(Activity activity){
		
		int ScreenWidth=activity.getResources().getDisplayMetrics().widthPixels;
		return ScreenWidth;
	}
	public static int getScreenHight(Activity activity){
		
		int ScreenHight=activity.getResources().getDisplayMetrics().heightPixels;
		return ScreenHight;
	}
	public static boolean isPortrait(Activity activity){
		
		boolean isprotrait=activity.getResources().getConfiguration().orientation==1?true:false;
		return isprotrait;
	}
	
	/**
	 * 
	 * <br>创建者：ldh
	 * <br>修改时间：2015年10月13日 上午9:48:07 
	 * <br>注释：获得dimen中定义的宽度
	 * @return
	 */
	public static int getDimensionPixelSize(Context context,int id){
		       
		return context.getResources().getDimensionPixelSize(id);
	}
}
