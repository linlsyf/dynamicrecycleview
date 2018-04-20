package com.core.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtil {
	public static int displayWidth;
	public static  int displayHeight;
	public int climbover;
	public int menuWidth;

	float menuWidhtPercent;
	float menuClimbPercent;

	@SuppressLint("NewApi")
	public static void calculateScreenBounds(Context context) {
//		displayHeight = ((WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
//				.getHeight();
//		displayWidth = ((WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
//				.getWidth();

		Display display =  ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		displayWidth = size.x;
		displayHeight = size.y;

	}

	public int getScreenOrientation(final Activity activity) {
		final Configuration c2a = activity.getResources().getConfiguration();
		return c2a.orientation;
	}

	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

}
