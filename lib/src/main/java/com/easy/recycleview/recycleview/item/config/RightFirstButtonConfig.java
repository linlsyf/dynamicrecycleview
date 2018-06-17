package com.easy.recycleview.recycleview.item.config;

import android.view.View;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;
import com.easy.recycleview.utils.StringUtils;

/**
 * Created by Chant on 2018/6/17.
 */

public class RightFirstButtonConfig{
        public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {
            if (StringUtils.isNotEmpty(dataItemBean.getRightFirstButtonText())) {
                itemView.mRightFirstButton.setVisibility(View.VISIBLE);
                itemView.mRightFirstButton.setText(dataItemBean.getRightFirstButtonText());
                if (dataItemBean.getRightFirstButtonBgResId() != 0) {
                    itemView.mRightFirstButton.setVisibility(View.VISIBLE);
                    itemView.mRightFirstButton.setBackgroundResource(dataItemBean.getRightFirstButtonBgResId());
                }
            } else {
                itemView.mRightFirstButton.setVisibility(View.GONE);
            }

        }
}
