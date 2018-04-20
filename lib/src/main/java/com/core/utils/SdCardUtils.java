package com.core.utils;

import java.io.File;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class SdCardUtils {



	public static File getSDcardFile() {
		return Environment.getExternalStorageDirectory();
	}
}
