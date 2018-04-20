package com.core.utils;

import java.io.File;

import android.text.TextUtils;

public class FileUtils {
	/*
	 * 创建目录
	*/
	public static File createDir(String filePath) {
		File file = new File(filePath);
		if (file.exists() == false) 
		{
			if(file.mkdirs()==true)
				return file;
		}
		return null;
	}
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 
	 *<br> 创建者：ldh
	 *<br>时间：2015年6月4日 上午11:07:37
	 *<br>注释：获得文件后缀名
	 *<br>@param isclassify 是否分类  用于快速查找文件对应的图标
	 *<br>@param isIncludeDot 是否包括"."
	 *<br>@return
	 */
	public static String GetFileExtName(String filePath,boolean isIncludeDot, boolean isclassify) {
		String ExtName = "";
		int p = filePath.lastIndexOf(".");
		if (p >= 0) {
			if (isIncludeDot == true)
				ExtName = filePath.substring(p);
			else
				ExtName = filePath.substring(p + 1);
		}
		
		if (isclassify) {
			
			if (ExtName.equalsIgnoreCase("mp4") || ExtName.equalsIgnoreCase("3gp") || ExtName.equalsIgnoreCase("rmvb") || ExtName.equalsIgnoreCase("avi")) {
				ExtName = "video";
			}
			if (ExtName.equalsIgnoreCase("jpg") || ExtName.equalsIgnoreCase("png") || ExtName.equalsIgnoreCase("gif") || ExtName.equalsIgnoreCase("bmp")) {
				ExtName = "jpg";
			}
			if (ExtName.equalsIgnoreCase("rar") || ExtName.equalsIgnoreCase("zip")) {
				ExtName = "zip";
			}
			ExtName = "bxfile_file_" + ExtName;
		}

		return ExtName;
	}
	/**
	 * 
	 * 创建者：ldh
	 * 时间：2015年6月2日 上午11:41:22
	 * 注释：获取文件大小
	 * @param size
	 * @return
	 */
	public static String getFileSizeStr(long size) {
		if (size <= 0)
			return "0.0B";
		java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
		float temp = (float) size / 1024;
		if (temp >= 1024) {
			return df.format(temp / 1024) + "M";
		} else {
			return df.format(temp) + "K";
		}
	}
	 /* 
	 * 创建者：ldh
	 * 时间：2015年6月2日 上午11:36:55
	 * 注释：查找文件是否存在
	 * @param path
	 * @return
	 */
	  public static boolean isExists(String path) {
	        File file = new File(path);
	        return file.exists();
	    }
	  /**
		 * 
		* 创建者：qjt
		* 时间：2015-4-14 下午5:51:19
		* 注释：输入文件路径,文件名，搜索该文件
		* @param filePath fileName
		 */
		public static boolean searchFile(String filePath, String fileName) {		
			File f = new File(filePath);
			if (f.isDirectory()) {
				File[] files = f.listFiles();
				if(files!=null){
					for (int i = 0; i < files.length; i++) {
						File file = files[i];
						if (file.isDirectory() == false)
						{	
							if(file.getName().equals(fileName))
								return true;
						}
					}
				}
					
			}
			return false;
		}
		/**
		   * 
		   * 创建者：ldh
		   * 时间：2015年6月2日 上午11:40:42
		   * 注释：获取文件后缀名
		   * @param fileName
		   * @return
		   */
			public static String getExspansion(String fileName){
				if(TextUtils.isEmpty(fileName))
					return null;
				int index = fileName.lastIndexOf(".");
				if(-1==index || index==(fileName.length()-1))
					return null;
				return fileName.substring(index);
			}
			/**
			 * 
			 * 创建者：ldh
			 * 时间：2015年6月2日 上午11:41:59
			 * 注释：判断是是否为文件夹
			 * @param filePath
			 * @return
			 */
			public static boolean isDir(String filePath) {
				File file = new File(filePath);
				return file.exists() && file.isDirectory();
			}
}
