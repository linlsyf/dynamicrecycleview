package com.easysoft.dyview;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 
 *<br> 创建者：ldh
 *<br>时间：2015年8月20日 上午11:17:18
 *<br>注释：文字部分高亮 点击跳转
 *<br>
 */
public class TextViewURLSpan extends ClickableSpan{
	
	Context context;
	/**点击事件*/
	private final OnClickListener mOnClickListener;

	/**高亮选中的文字的颜色*/
	int HighlightColor;
	/**选中的文字的颜色*/
	int cliclkColor;
	/**
	 *
	 */
	public TextViewURLSpan(Context context,OnClickListener mOnClickListener) {
		this.context=context;
		this.mOnClickListener=mOnClickListener;
	}
	
	
	@Override
	public void onClick(View widget) {
		
		if (mOnClickListener!=null) {
			mOnClickListener.onClick(widget);
		}
		avoidHintColor(widget);
	}
	
	@Override
	public void updateDrawState(TextPaint ds) {
		super.updateDrawState(ds);
		
		if (cliclkColor != 0) {

			ds.setColor(cliclkColor); // 设置文本颜色
		}
		// 去掉下划线
		ds.setUnderlineText(false);
	}
	
	private void avoidHintColor(View view) {
		if (view instanceof TextView) {
			((TextView) view).setHighlightColor(context.getResources().getColor(android.R.color.transparent));
		}
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
	  * 
	  *<br> 创建者：ldh
	  *<br>时间：2015年8月20日 上午11:22:09
	  *<br>注释：设置点击文字后文字的背景 如果不设置为透明
	  *<br>
	  */
	 public void setHighlightColor(){
		 
	 }
	 /***
	  * 
	  *<br> 创建者：ldh
	  *<br>时间：2015年8月20日 上午11:21:58
	  *<br>注释：设置被点文字的颜色
	  *<br>
	  */
	 public void setcliclkColorColor(int cliclkColor){
		 this. cliclkColor=cliclkColor;
	 }
		
}
