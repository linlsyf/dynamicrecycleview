package com.core.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * 
 *<br> 创建者：ldh
 *<br>时间：2015年9月22日 上午10:00:10
 *<br>注释：界面共用工具
 *<br>
 */
public class TimeLogOutUtils {
	
	
	private  Timer timer ;
	private  TimerTask timerTask;
	/**每5分钟锁定一次*/
//	private  int  logOutTime=10*1000;
	private  int  logOutTime=5*60*1000;
	
	private static Context applicationContext;
	private static TimeLogOutUtils instance;
	
	public static TimeLogOutUtils getInstance(Activity basecontext) {
		
		if (instance == null) {
			
			
			applicationContext=basecontext.getApplicationContext();
			instance = new TimeLogOutUtils();
		}
		return instance;
	
	}
	

	
	// 上一次触碰时间
		long lastTimeMillis = 0;
		public   void resetTime(){
			// 最新的触碰时间
			long currentTimeMillis = System.currentTimeMillis();
			if(lastTimeMillis == 0){
				// 第一次触碰
				lastTimeMillis = currentTimeMillis;
				// 开启监听
				startVerify();
			}else{
				//时间差
				long temp = currentTimeMillis - lastTimeMillis;
				// 如果时间差小于5分钟，就先停掉前一次的监听，再重新开启
				if(temp < logOutTime){
//					if(temp < 1000 * 60 * 5){
					stopVerify();
					startVerify();
				}
				// else 如果大于,那么上一次的监听在运行着，5分钟之后自然会锁定
			}
		}
		
		private Handler mHandler = new Handler(applicationContext.getMainLooper()){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
//				verify();
		
			}
		};
		/**
		 * 
		 *<br> 创建者：ldh
		 *<br>时间：2015年9月29日 上午9:20:56
		 *<br>注释：退出程序
		 *<br>
		 */
//	public void verify() {
//		
//		
//		lastTimeMillis = 0;
//		stopVerify();
//
//		Intent intent = new Intent(applicationContext, DialogActivity.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		applicationContext.startActivity(intent);
//
//	}
	
		/**
		 * 5分钟一次弹出
		 * 
		 * @param 
		 * @return void
		 * @throws
		 */
	public void startVerify() {
		if (timer == null) {
			timer = new Timer();
		}
		if (timerTask == null) {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					mHandler.sendEmptyMessage(0);
				}
			};
		}
		timer.schedule(timerTask, logOutTime, logOutTime);

	}
		
		/**
		 * 停止检测
		 * 
		 * @param 
		 * @return void
		 * @throws
		 */
		public  void stopVerify(){
			if(timer != null){
				timer.cancel();
				timer = null;
			}
			if(timerTask != null){
				timerTask.cancel();
				timerTask = null;
			}
		}
}
