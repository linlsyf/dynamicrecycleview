package com.utils;

import android.content.Context;
import android.text.TextPaint;
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
