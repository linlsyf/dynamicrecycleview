package com.core.recycleview.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.easysoft.costumes.R;


/**
 * 开关按钮 自定义仿 IOS7  自适应大小 可更换图片
 */
public class IOSSwitchButton extends RelativeLayout {

	private Context mContext;
	private View mRootView;
	/**用于切换图片的image*/
	private ImageView imageView;
	/**是否开启*/
	private boolean isOn = false;
	/** 状态切换监听*/
	private OnStateChangeListener mListener;
	/**自定义open 的图片*/
	private int mOpenImageId =0;
	/**自定义closed 的图片*/
	private int mClosedImageId =0;
	/**是否延迟改变图片，如只有数据库保存成功才改变成功图片 否则外部再调用设置为false*/
	private boolean mDelayChangeImage =false;
	/**点击是否可以改变状态*/
	private boolean ischangeAable=true;
	private String ischangeAableMessage="";

	public IOSSwitchButton(Context context) {
		super(context);
		initUI(context);
	}
	public IOSSwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initUI(context);
	}

	private void initUI(Context context) {
		this.mContext = context;
		// 头部控件
		mRootView = LayoutInflater.from(context).inflate(R.layout.view_iosswitchbutton, this, true);

		imageView = (ImageView) mRootView.findViewById(R.id.iv);
		imageView.setImageResource(R.drawable.btn_switch_closed);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			if (ischangeAable==false) {//不能改变状态
				
				if (!"".equals(ischangeAableMessage)) {
					Toast.makeText(mContext,ischangeAableMessage,Toast.LENGTH_SHORT).show();
				}
				return true;
			}else if (mDelayChangeImage == true) {
				delayChange();
			}else {
				
				if (isOn) {
					isOn = false;
				}else {
					isOn = true;
				}
				switchState(isOn,true);
			}
			break;

		default:
			break;
		}

		return true;
	}


	/**
	 * 只监听点击状态 外部控制是否改变是否选中和图片
	 */
	private void delayChange(){
		boolean delatyIsSelected=isOn;
		if (delatyIsSelected) {
			delatyIsSelected = false;
		}else {
			delatyIsSelected = true;
		}
		if (mListener != null)
			mListener.onStateChanged(delatyIsSelected);
		
		
	}
	
	 
	/**
	 * 切换开关的状态
	 * @param ischeck
	 * @param isListener
     */
	private void switchState(boolean ischeck,boolean isListener) {
		
		
		if (mListener != null&&isListener)
			mListener.onStateChanged(ischeck);
		
		
		if (ischeck) {
			// 切换关闭的图片

			if (mOpenImageId != 0) {
				imageView.setImageResource(mOpenImageId);

			} else {

				imageView.setImageResource(R.drawable.btn_switch_open);
			}

		} else {
			// 切换打开的图片

			if (mClosedImageId != 0) {
				imageView.setImageResource(mClosedImageId);

			} else {

				imageView.setImageResource(R.drawable.btn_switch_closed);
			}

		}
		
	}
	
	

	/**
	 * 切换开关的状态
	 * @param openImageId
	 * @param closedImageId
	 * @param isopen  默认状态是否为打开
     */
     public void setCustomImageId(int openImageId,	int closedImageId,boolean isopen)  {
		this.mOpenImageId = openImageId;

		this.mClosedImageId = closedImageId;
		this.isOn=isopen;
		if (isopen) {
			imageView.setImageResource(openImageId);
		}else {
			imageView.setImageResource(closedImageId);
		}
	}
     
	/**
	 * 获得当前选中状态
	 * @return
     */
     public boolean  getCheckState(){
    	 return this.isOn;
     }
     /**
      * 
      *<br> 创建者：ldh
      *<br>时间：2015年6月9日 上午11:14:23
      *<br>注释：设置当前选中状态
      *<br>@param ischeck
      */
	public void setCheckState(boolean ischeck) {

		this.isOn = ischeck;
		switchState(isOn,false);

	}

	/**
	 * 设置状态切换监听
	 * @param listener
     */
	public void setOnStateChangeListener(OnStateChangeListener listener) {
		this.mListener = listener;
	}
	public interface OnStateChangeListener {
		public void onStateChanged(boolean isOn);
	}
	
	/**
	 * 设置为true时 只做isOn  如只有数据库保存成功才显示改变状态
	 * @param mDelayChangeImage
     */
	public void setmDelayChangeImage(boolean mDelayChangeImage) {
		this.mDelayChangeImage = mDelayChangeImage;
	}
	
	/**
	 * 是否可以改变状态    false 为不可变
	 * 如 一旦设定状态后  点击也不改变图片状态为打开
	 * @param able
     */
	public void  setChangeable(boolean able){
		
		this. ischangeAable = able;
	}
	/**
	 * 设置是否可以点击 如果不能点击那么提示出入的信息
	 * @param able
	 * @param msg
     */
	public void  setChangeable(boolean able,String msg){
		
		this. ischangeAable = able;
		this.ischangeAableMessage=msg;
				
	}
}
