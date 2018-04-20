package com.core.DebugUtlis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;

import com.core.utils.FileUtils;
import com.core.utils.TimeUtils;
/**
 * 
 * 写错误信息
 *  默认会删除已经存在的文件
 *  发布时关闭
 */
public class CrashInfo2File {
	public static String saveCrashInfo2File(Throwable ex) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		StringBuffer sb = new StringBuffer();
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		
		
//		String appVersion = ApkUtils.getInstance(GlobalConstants.getInstance().getApplicationContext()).getVersionName();
		String appVersion ="1.0";
		String descappend=appVersion + "\r\n"+TimeUtils.getCurrentTime() + "\r\n";
		
		String result = descappend + writer.toString();
//		String result = writer.toString();
		sb.append(result);
		try {
			long timestamp = System.currentTimeMillis();
			String time = formatter.format(new Date());
			String fileName = "crash-" + time + "-" + timestamp + ".txt";
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				File sd = Environment.getExternalStorageDirectory();
				String fullPath = sd.getPath() + "/MMMDebug/";
				File dir = new File(fullPath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileUtils.delAllFile(fullPath);
				FileOutputStream fos = new FileOutputStream(fullPath + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return fileName;
		} catch (Exception e) {
			// Log.e(TAG, "an error occured while writing file...", e);
		}
		return null;
	}

}
