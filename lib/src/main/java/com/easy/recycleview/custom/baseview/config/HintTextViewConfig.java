package com.easy.recycleview.custom.baseview.config;

import android.view.View;
import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;


/**
 * Created by Chant on 2018/6/17.
 */

public class HintTextViewConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
        if (dataItemBean.isHintShow()) {
            itemView.mHintTextView.setVisibility(View.VISIBLE);
            itemView. mHintTextView.setText(dataItemBean.getHint());
            if (dataItemBean.getHintLeftMagin() != 0) {
                LinearLayout.LayoutParams mHintParams =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mHintParams.leftMargin = dataItemBean.getHintLeftMagin();
                itemView.mHintTextView.setLayoutParams(mHintParams);
            }

            int colorResId=dataItemBean.getHintSettings().getColor();
            int titleColorResId= RecycleConfig.getInstance().getThemeConfig().getHintColorResId();
            if (colorResId!=0){
                itemView.mHintTextView.setTextColor(colorResId);
            }else  if(titleColorResId!=0){
                itemView.mHintTextView.setTextColor(titleColorResId);
            }
        } else {
            itemView.mHintTextView.setVisibility(View.GONE);
        }

    }
}
