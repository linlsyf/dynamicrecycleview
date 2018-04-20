package com.core.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 键盘工具类
 */
public class KeyboardUtils
{
    /**
     * 打开软键盘
     *
     * @param mContext 上下文
     * @param mEditText mEditText输入框
     */
    public static void openKeybord(Context mContext,EditText mEditText)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void openKeybord(Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 关闭软键盘
     *
     * @param mContext 上下文
     * @param mEditText 输入框
     */
    public static void closeKeybord(Context mContext,EditText mEditText)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
     /**
        *<br> 创建者：林党宏
        *<br>时间：2016/12/1 9:16
        *<br>注释：关闭软键盘 用户不用指定焦点在哪个键盘上
        */
    public static void closeKeybord(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

}
