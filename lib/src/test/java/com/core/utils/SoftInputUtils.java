package com.core.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class SoftInputUtils {

	public static void hiddenSoftInput(Activity activity) {
		View view = activity.getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
	
	public static void showkeyboard(Activity activity, boolean Focusable) {
		if (Focusable == true) {
			//setFocusable(true);
			InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		} else {
			 hiddenSoftInput( activity);

		}

	}

	public static boolean isShowSoftInput(Activity activity) {
		boolean isshow=false;
		if (activity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
//			// 关闭输入法
//			InputMethodManager imm = (InputMethodManager) activity.getApplicationContext()
//					.getSystemService(Context.INPUT_METHOD_SERVICE);
//
//			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			
			isshow=true;
		}
		
		return isshow;
	}
}
