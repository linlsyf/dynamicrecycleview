package com.core.DebugUtlis;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import android.os.Environment;
import android.util.Log;

import com.core.db.manger.StringUtils;
import com.core.utils.TimeUtils;

/**
 * 
 * @author {Denforth}
 *    写log 工具
 */
public class LogUtils {
	private static final String LogTAG = "ODebug";
	private static final int BYTE_TO_WRITE = 8192; 
	
	public  boolean debug_logfile_switch = true;//true;//false;
	
	String fileName = "MsgLog.txt";

	String fullPath;

	/**追加写入 还是刷新只记录最后的错误信息*/
	boolean append = true;
	
	public LogUtils(){
		
		instance=this;
		
		
	}
	
	private static LogUtils instance;
	public static LogUtils getInstance() {
		if (instance == null) {
			instance = new LogUtils();
		}
		return instance;
	}
	public  void log2file(String msg){
		
		
		if (msg==null) {
			return;
		}
		 if (debug_logfile_switch) {
			
		}
		
		if(debug_logfile_switch)
		{
			fileName = "MsgLog.txt";
			// 读取SD卡状态
			String status = Environment.getExternalStorageState();
			Log.d(LogTAG, "status of sdcard:" + status);
			if (!status.equals(Environment.MEDIA_MOUNTED)) {// mounted
				// 请插入SD卡作为临时文件存放目录！
				return;
			}

			File sd = Environment.getExternalStorageDirectory();
			
			
			if (StringUtils.isBlank(fullPath)) {
				
				 fullPath = sd.getPath() + "/";
			}
			File file = new File(fullPath);
			if (file.exists() == false) {
				// 目标文件夹不存在时创建该文件夹
				file.mkdirs();
			}

			InputStream is = null;
			try {
				is = new ByteArrayInputStream(msg.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (is != null) {
				String logfile = fullPath + fileName;
				File f = new File(logfile);
				try {
					
					FileOutputStream fos = new FileOutputStream(f, append); // new
																			// FileOutputStream(f);
					byte buf[] = new byte[BYTE_TO_WRITE];
					int numread = 0;
					while (((numread = is.read(buf)) != -1)) {
						fos.write(buf, 0, numread);
					}
					;
					fos.flush();
					fos.close();
					is.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * <br>创建者：ldh
	 * <br>修改时间：2015年9月25日 下午4:04:31 
	 * <br>注释：是否打开log
	 * @param debug_logfile_switch
	 */
	public  void setDebugSwitch(boolean isOpenlog) {
		this.debug_logfile_switch = isOpenlog;
	}
/**
 * 
 * <br>创建者：ldh
 * <br>修改时间：2015年9月25日 下午4:06:26 
 * <br>注释：设置log 文件名
 * @param fileName
 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setAppend(boolean append) {
		this.append = append;
	}
	
	/**
	 * 
	 *<br> 创建者：ldh
	 *<br>时间：2015年9月30日 下午3:24:51
	 *<br>注释：清除log 文件
	 *<br>
	 */
	public void cleanLog(){
		File sd = Environment.getExternalStorageDirectory();
		 fullPath = sd.getPath() + "/";
		 fullPath=fullPath+fileName;
		 
		 File file=new File(fullPath);
		 
		 if (file.exists()) {
			file.delete();
		}
	}
	
	public void logError(Throwable ex){
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();

		log2file("\r\n" + result + TimeUtils.getCurrentTime());

	}
}
