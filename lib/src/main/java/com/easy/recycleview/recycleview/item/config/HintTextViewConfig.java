package com.easy.recycleview.recycleview.item.config;

import android.view.View;
import android.widget.LinearLayout;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;

/**
 * Created by Chant on 2018/6/17.
 */

public class HintTextViewConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
        if (dataItemBean.isHintShow()) {
            itemView.mHintTextView.setVisibility(View.VISIBLE);
            itemView. mHintTextView.setText(dataItemBean.getHint());
            if (dataItemBean.getHintLeftMagin() != 0) {
                LinearLayout.LayoutParams mHintParams =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                mHintParams.leftMargin = dataItemBean.getHintLeftMagin();
                itemView.mHintTextView.setLayoutParams(mHintParams);
            }
        } else {
            itemView.mHintTextView.setVisibility(View.GONE);
        }

    }
}