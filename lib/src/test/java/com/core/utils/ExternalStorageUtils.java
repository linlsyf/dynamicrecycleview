package com.core.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;

import com.easysoft.costumes.R;

public class ExternalStorageUtils {

	public static File getDiskCacheDir(Context context, String uniqueName) {

		// Check if media is mounted or storage is built-in, if so, try and use
		// external cache dir
		// otherwise use internal cache dir
		final String cachePath = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)
				|| !isExternalStorageRemovable() ?getExternalCacheDir(context).getPath() : context.getCacheDir()
				.getPath();
	

		return new File(cachePath + File.separator + uniqueName);
	}
	// @SuppressLint("NewApi")
		public static boolean isExternalStorageRemovable() {
			/*
			 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			 * return Environment.isExternalStorageRemovable(); }
			 */
			return false;
		}
		
		// @SuppressLint("NewApi")
		public static File getExternalCacheDir(Context context) {
			/*
			 * if (hasExternalCacheDir()) { return context.getExternalCacheDir(); }
			 */

			// Before Froyo we need to construct the external cache dir ourselves

			String app_nameString = context.getResources().getString(
					R.string.app_name);

			final String cacheDir = "/" + app_nameString +"/GlobalResource"+ "/cache/";

			return new File(Environment.getExternalStorageDirectory().getPath()
					+ cacheDir);
		}

}
