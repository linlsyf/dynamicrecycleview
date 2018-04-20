package com.view.edittextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 
   *<br> 创建者：林党宏
   *<br>时间：2016年3月31日 下午6:12:28
   *<br>注释：用于延迟监听
 */
public class DelayListenerEditText extends EditText {
//    private String TAG = "MyEditText";  
    private static final int MSGCODE = 0x12121212;  
      private mTextWatcher textWatcher = new mTextWatcher();  
    /**当前输入框的文字*/
    private String text;  
    /**监听输入变化次数*/
    private int msgCount = 0;  
    /**延迟处理时间 默认为1.5秒 由外部设置延迟时间*/
    long delayMillis = 900;

	public DelayListenerEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.addTextChangedListener(textWatcher);
	}
  
    private onTextChangerListener listener = null;  
  
    public void setOnTextChangerListener(onTextChangerListener listener) {  
        this.listener = listener;  
    }  
  
    public interface onTextChangerListener {  
        public void onTextChanger(String text);  
    }  
  
    private class mTextWatcher implements TextWatcher {  
        public void afterTextChanged(Editable s) {  
            text = s.toString();  
              msgCount++;  
              Message msg = new Message();  
            msg.what = MSGCODE;  
            mHandler.sendMessageDelayed(msg, delayMillis);  
        }

		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}

		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
		}  
  
    }  
    
    /**
     * 
       *<br> 创建者：林党宏
       *<br>时间：2016年3月31日 下午6:17:56
       *<br>注释：设置延迟监听时间长度
     */
    public void setDelayMillis(long delayMillis) {
		this.delayMillis = delayMillis;
	}
    /**  
     * 防止调用setText时重新触发TextWatcher事件  
     *   
     * @param text  
     */  
    public void resetListener(String text) {  
        removeTextChangedListener(textWatcher);  
        setText(text);  
        setSelection(text.length());
        addTextChangedListener(textWatcher);  
    }  
  
    /**监听 延迟处理文字*/
    Handler mDelayedHandler = new Handler();  
  
    @SuppressLint("HandlerLeak")  
    Handler mHandler = new Handler() {  
        public void handleMessage(Message msg) {  
            if (msg.what == MSGCODE) {  
                if (msgCount == 1) {  
                    if (listener != null) {  
                        listener.onTextChanger(text); 
                        resetListener(text);
                    }  
                    msgCount = 0;  
                } else {  
                    msgCount--;  
                }  
  
            }  
        }  
    };  
  
}  