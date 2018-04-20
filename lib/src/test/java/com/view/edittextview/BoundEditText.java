package com.view.edittextview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 
 *<br> 创建者：ldh
 *<br>时间：2015年7月24日 上午9:40:36
 *<br>注释：带删除按钮的EditText 如果外部设置setOnFocusChangeListener 
 *<br>需要在自定义的监听中调用本类的onFocusChange 才能控制删除按钮是否显示
 */
public class BoundEditText extends EditText {
	private Drawable dRight;
	private Drawable dleft;
	private Rect rBounds;
	private boolean hasFocus;
	/**是否设置光标一直在文字的最后面*/
	private boolean cursorInEnd=false;
	/**设置绑定的view  当输入框文字有时显示  用于控制点击取消按钮的位置可以任意放置  */
	private View bingView ;
	private Context context;
	/**
	 * 用于限制不可编辑的个人信息时，无法触发触摸事件
	 */
	private boolean isEdit = true;
	private cleanCallback callback;
	private boolean isshowDrawableAlways=false;
	/**控制是否显示右侧删除图标 */
	private boolean isshowDrawable=true;

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	// 构造器
	public BoundEditText(Context paramContext) {
		super(paramContext);
		this.context = paramContext;
		initEditText();
	}

	public BoundEditText(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		this.context = paramContext;
		initEditText();
	}

	public BoundEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		this.context = paramContext;
		initEditText();
	}

	// 初始化edittext 控件
	private void initEditText() {
		
		setEditTextDrawable();

		addTextChangedListener(new TextWatcher() { // 对文本内容改变进行监听
			public void afterTextChanged(Editable paramEditable) {

			}

			public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
			}

			public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
				BoundEditText.this.setEditTextDrawable();
			}
		});

		setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				BoundEditText.this.hasFocus = hasFocus;
					setEditTextDrawable();
				
			}
		});
	}

/**
 * 
 *<br> 创建者：ldh
 *<br>时间：2015年7月24日 上午9:48:06
 *<br>注释：自定义 setOnFocusChangeListener  冲掉默认监听 重新设置监听是否需要显示删除控件
 *<br>@param v
 *<br>@param hasFocus
 */
	public void onFocusChange(View v, boolean hasFocus){
		BoundEditText.this.hasFocus = hasFocus;
		setEditTextDrawable();
	}

	public void setIsShowDrawable(boolean isshowDrawable) {
		this.isshowDrawable = isshowDrawable;
	}

	// 控制图片的显示
	private void setEditTextDrawable() {
		 
		String  text=getText().toString();
		if (text.length() > 0 && hasFocus == true&&isshowDrawable) {
			setCompoundDrawables(this.dleft, null, this.dRight, null);
		} else {
			setCompoundDrawables(null, null, null, null);
		}
		if (isshowDrawableAlways&&isshowDrawable) {
			setCompoundDrawables(this.dleft, null, this.dRight, null);
		}
	}

	protected void finalize() throws Throwable {
		super.finalize();
		this.dRight = null;
		this.rBounds = null;
	}

	// 添加触摸事件
	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		if (!isEdit) {
			return false;
		}

		if ((this.dRight != null) && hasFocus == true) {
			this.rBounds = this.dRight.getBounds();
			int i = (int) paramMotionEvent.getRawX();

			if (i > (getRight() - this.rBounds.width())) {
				setText("");
				paramMotionEvent.setAction(MotionEvent.ACTION_CANCEL);
				if (callback != null) {
					callback.onClickClean();
				}
			}
		}
		return super.onTouchEvent(paramMotionEvent);
	}

	// 设置显示的图片资源
	public void setCompoundDrawables(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3,
			Drawable paramDrawable4) {
		if (paramDrawable3 != null)
			this.dRight = paramDrawable3;
		if (paramDrawable1 != null) {
			this.dleft = paramDrawable1;
		}
		super.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
	}

	public void showkeyboard(boolean Focusable) {
		if (Focusable == true) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		} else {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getWindowToken(), 0);

		}

	}

	public interface cleanCallback {
		/**
		 * 点击了清除按钮
		 */
		public void onClickClean();
	}

	public void setcleanCallback(cleanCallback callback) {
		this.callback = callback;
	}
	public void setShowDrawablesAlways(boolean isshowDrawableAlways){
		this.isshowDrawableAlways=isshowDrawableAlways;
		setEditTextDrawable();
	}
	
	
	 @Override
	    protected void onSelectionChanged(int selStart, int selEnd) {
	        
		if (cursorInEnd & selEnd < getText().length()) {// 光标位置到比原来的位置小
		// 如果设置了光标一直在后面
			setSelection(getText().length());
		} else {

			super.onSelectionChanged(selStart, selEnd);
		}

	    }
	 
	 /**
	  * 
	  *<br> 创建者：ldh
	  *<br>时间：2015年8月31日 下午3:09:40
	  *<br>注释：设置光标一直在文字的最后面
	  *<br>
	  */
	 public void setCursorInEnd(Boolean iscursorInLast){
		 this.cursorInEnd=iscursorInLast;
	 }
	 
	 /**
	  * 
	  *<br> 创建者：ldh
	  *<br>时间：2015年9月7日 上午11:14:42
	  *<br>注释：设置绑定的控件 监听是否内容为空 不为空显示
	  *<br>
	  */
	 public void setBingView(View v){
		 this.bingView=v;
	 }
}
