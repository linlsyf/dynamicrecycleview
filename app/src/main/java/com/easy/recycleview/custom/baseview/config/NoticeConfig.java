package com.easy.recycleview.custom.baseview.config;

import android.view.View;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.StringUtils;
import com.easy.recycleview.custom.bean.AddressItemBean;
import com.easysoft.dyview.R;


/**
 * Created by Chant on 2018/6/17.
 */

public class NoticeConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
        if (StringUtils.isNotEmpty(dataItemBean.getLeftSecondText())) {
            itemView.mNoticeTextView.setText(dataItemBean.getLeftSecondText());
            itemView.mNoticeTextView.setVisibility(View.VISIBLE);
            if (dataItemBean.getLeftSecondTextColor() != 0) {
                itemView.mNoticeTextView.setTextColor(dataItemBean.getLeftSecondTextColor());
            }
            if (dataItemBean.getLeftSecondTextbg() != 0) {
                itemView.mNoticeTextView.setBackgroundResource(dataItemBean.getLeftSecondTextbg());
            } else {
                itemView.mNoticeTextView.setBackgroundResource(R.drawable.transparent);
            }
        } else {
            itemView.mNoticeTextView.setVisibility(View.GONE);
        }
        if (dataItemBean.getLeftSecondImgResId() != 0) {
            itemView.mNoticeImageView.setVisibility(View.VISIBLE);
            itemView. mNoticeImageView.setImageResource(dataItemBean.getLeftSecondImgResId());
        } else {
            itemView.mNoticeImageView.setVisibility(View.GONE);
        }

    }
}
