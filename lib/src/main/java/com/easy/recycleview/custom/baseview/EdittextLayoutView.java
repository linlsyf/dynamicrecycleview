package com.easy.recycleview.custom.baseview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.easy.recycleview.custom.baseview.base.BaseLinearLayout;
import com.easysoft.dynamicrecycleview.R;


/**
 *创建者：林党宏
 *时间：2017/2/6
 *注释：输入框 显示删除按钮
 */

public class EdittextLayoutView extends BaseLinearLayout {
    Context mContext;
    RelativeLayout mCleanLayout;
    EditText mContentEditText;
    boolean mCanEdit=true;
    private CallbackListener mCallbackListener;
    /**控制是否显示右侧清除按钮 */
    private boolean mShowCleanImg=true;

    public EdittextLayoutView(Context context) {
        super(context);
    }

    public EdittextLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    protected void initUI(Context context) {
        mContext = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_edittextlayout, this, true);
         mCleanLayout =(RelativeLayout) rootView.findViewById(R.id.cleanLayout);
        mContentEditText =(EditText) rootView.findViewById(R.id.edit);
        mContentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")){
                    setCleanLayoutVisable(false);
                }else{
                    if (mCanEdit){
                        if (mCallbackListener!=null){
                            mCallbackListener.onCallback(s.toString().trim());
                        }
                        setCleanLayoutVisable(true);
                    }

                }

            }
        });

        mCleanLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContentEditText.setText("");
            }
        });
//        mContentEditText.setInputType( InputType.TYPE_TEXT_FLAG_MULTI_LINE);
//        mContentEditText.setInputType( InputType.TYPE_CLASS_TEXT);

    }

    public void setInputType(int type){
        mContentEditText.setInputType(type);
    }

    public String getText(){
        return mContentEditText.getText().toString().trim();
    }


    public EditText getContentEditText() {
        return mContentEditText;
    }

    public void setText(String text){
        mContentEditText.setText(text);
//        if (text!=null){
//            mContentEditText.setSelection(getText().length()+1);
//        }
    }

    public void setOpenKeybord(boolean openKeybord){
        mContentEditText.setSelection(mContentEditText.getText().toString().trim().length()+1);
        if (openKeybord){

            postDelayed(new Runnable() {
                @Override
                public void run() {

                    mContentEditText.setFocusable(true);
                    mContentEditText.setFocusableInTouchMode(true);
                    mContentEditText.requestFocus();

                    InputMethodManager imm = (InputMethodManager) mContext
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mContentEditText, InputMethodManager.RESULT_SHOWN);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);


                }
            },500);

        }
    }

    public void setCleanLayoutVisable(boolean isVisable){
        if (isVisable&mShowCleanImg){
            mCleanLayout.setVisibility(View.VISIBLE);
        }else{
            mCleanLayout.setVisibility(View.GONE);

        }
    }
    public void setCanUserInput(boolean isCanEdit){
        mCanEdit=isCanEdit;
        if (!mCanEdit){
            mContentEditText.setFocusable(false);
            mCleanLayout.setVisibility(View.GONE);
        }else {
            mContentEditText.setFocusable(true);

        }
    }

    public EditText getInput(){
        return mContentEditText;
    }

    public void setHint(String hint){
        mContentEditText.setHint(hint);
    }
    public void setShowCleanImg(boolean showCleanImg){
        this.mShowCleanImg=showCleanImg;
    }
    public void setCallback(CallbackListener  Listener){
        this.mCallbackListener=Listener;
    }
    public interface CallbackListener {

        void onCallback(String text);
    }
}
