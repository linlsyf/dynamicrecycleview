package com.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.format.Time;

public class TimeUtils {

	public static int compare_date(String DATE1, String DATE2) {
		// int compare=0;
		
		
		
		
		
		 DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 
		 
		 if (DATE1.contains("-")) {
			 df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		 
		 
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return -1;// 后面添加的放到前面
				// return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static String getCurrentTime() {
		Time localTime = new Time("Asia/Hong_Kong");
		localTime.setToNow();
//		String date = localTime.format("%Y/%m/%d %H:%M:%S");
		 String date = localTime.format("%Y-%m-%d %H:%M:%S");
		return date;
	}

	//获得date 
	public static Date getLongTime(String createTime) {

		SimpleDateFormat shortsdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// long create = sdf.parse(createTime).getTime();
		
		 if (createTime.contains("-")) {
			 sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		 

//		long create = 0;
		Date date = null;

		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			if (date == null) {

				try {
					date = shortsdf.parse(createTime);
				} catch (ParseException e1) {
					return date;
				}

			}
		}
		
		return date;

	}

	public static String parseDate(String createTime) {
		try {
			String ret = "";
			long create = 0;
			Date date = null;
			date=getLongTime(createTime); 
			
			if (date==null) {
				return "更早";
			}
			
			create = date.getTime();
			Calendar now = Calendar.getInstance();
			long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600
					+ now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 毫秒数
			long ms_now = now.getTimeInMillis();
			if (ms_now - create < ms) {
				ret = "今天";
			} else if (ms_now - create < (ms + 24 * 3600 * 1000)) {
				ret = "昨天";
			} else if (ms_now - create < (ms + 24 * 3600 * 1000 * 2)) {
				ret = "前天";
			} else {
				ret = "更早";
			}
			return ret;
		} catch (Exception e) {
			// return getCurrentTime();
			return "更早";
		}
	}

	public static String formatTimeString(String timeString) {
		String formattime = "";
		// DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		DateFormat returndf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// DateFormat returndf = new SimpleDateFormat("yyyy年MM月dd日 [hh:mm:ss]");
		Date dt1 = new Date();
		try {
			dt1 = returndf.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		formattime = returndf.format(dt1);
		return formattime;
	}

}
