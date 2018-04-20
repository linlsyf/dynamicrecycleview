package com.core.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivityHelper {

	
	public static void openActivity(Context context, Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(context, cls);
		context.startActivity(intent);
	}
	
	
}
