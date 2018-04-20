package com.core.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.utils.TextViewURLSpan;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean  isEmpty(String msg){
		boolean isempty=true;
		if (msg!=null&&!msg.equals("")) {
			isempty=false;
		}
		return isempty;
	}
	public static boolean  isNotEmpty(String msg){
		boolean isempty=false;
		if (msg!=null&&!msg.equals("")) {
			isempty=true;
		}
		return isempty;
	}
	
	
	
	/**
	 * 
	 * <br>创建者：ldh
	 * <br>修改时间：2015年9月28日 上午10:01:31 
	 * <br>注释：获得一个指定长度的随机字符串
	 * @param length
	 * @return
	 */
		public static String getRandomString(int length) { 
		    StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
		    StringBuffer sb = new StringBuffer(); 
		    Random random = new Random(); 
		    int range = buffer.length(); 
		    for (int i = 0; i < length; i ++) { 
		        sb.append(buffer.charAt(random.nextInt(range))); 
		    } 
		    return sb.toString(); 
		}
	/**
	 *
	 * 创建者：qjt
	 * 时间：2015-4-29 上午9:23:44
	 * 注释：判断当前字符串是否为手机号码格式
	 * @param number
	 * @return
	 */
	public static boolean isPhoneNumber(String number){
		if (isEmpty(number)){
			return false;
		}
		Pattern pattern = Pattern.compile("^(1)\\d{10}$");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}


	/**
	 *
	 *<br> 创建者：ldh
	 *<br>时间：2015年8月31日 上午11:58:14
	 *<br>注释：判断是否至少含有一个以上的字母以及一个以上的数字（要含字母+数字）
	 *<br>@param number
	 *<br>@return
	 */
	public static boolean isContainNumAndchar(String pwd) {
		Pattern pattern = Pattern.compile("^(?=.*[0-9].*)(?=.*[a-zA-Z].*).{2,}$");

		Matcher m = pattern.matcher(pwd);

		return m.matches();
	}

	/**
	 *
	 * 创建者：qjt
	 * 时间：2015-4-29 上午10:55:19
	 * 注释：判断当前字符串是否为邮箱格式
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if (isEmpty(email)){
			return false;
		}
		String str="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return temp;
	}
	/**
	 * 只判断字符串之前是否有空白字符
	 * @param cs
	 * @return
	 */
	public static boolean isBlank(CharSequence cs)
	{
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(cs.charAt(i)))
				return false;

		return true;
	}



	/**
	 *
	 *<br> 创建者：ldh
	 *<br>时间：2015年11月24日 上午10:47:06
	 *<br>注释：转换时间显示
	 *<br>@param time 毫秒
	 *<br>@return
	 */
	public static String generateTime(long time) {
		int totalSeconds = (int) (time / 1000);
		int seconds = totalSeconds % 60;
		int minutes = (totalSeconds / 60) % 60;
		int hours = totalSeconds / 3600;

		return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes,
				seconds) : String.format("%02d:%02d", minutes, seconds);
	}
	/**
	 *
	 *<br> 创建者：林党宏
	 *<br>时间：2016年6月2日 下午4:01:49
	 *<br>注释：拼接有文字的点击 textview
	 *@param contentTextView 用来显示的textview
	 *@param firstString 开头的文字
	 *@param clickString 点击的 带颜色的文字
	 *@param cliclkColor 点击的 带颜色的文字
	 */
	public static void setLinkText(Context mContext, TextView contentTextView, String firstString, String clickString, int  cliclkColor, View.OnClickListener mOnClickListener){
		contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
		SpannableString spanStr = new SpannableString(firstString);// 注册账号即表示您同意遵守?账号的
		SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
		ssb.append(clickString);
		// 计算第一个点击文字的位置
		final int start = spanStr.length();
		TextViewURLSpan clickItem1 = new TextViewURLSpan(mContext, mOnClickListener);
		clickItem1.setcliclkColorColor(cliclkColor);
		ssb.setSpan(clickItem1, start, start + clickString.length(), 0);
		// 设置文字到textview
		contentTextView.setText(ssb, TextView.BufferType.SPANNABLE);
	}

	/**
	 * 字符串转换成图片资源id
	 * @param paramContext
	 * @param paramString
	 * @return
	 */
	public static int getDrawableId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString,
				"drawable", paramContext.getPackageName());
	}

	/**
	 * 字符串转为字符串资源
	 * @param paramContext
	 * @param paramString
	 * @return
	 */
	public static int getStringId(Context paramContext, String paramString) {
		return paramContext.getResources().getIdentifier(paramString, "string",
				paramContext.getPackageName());
	}
}
