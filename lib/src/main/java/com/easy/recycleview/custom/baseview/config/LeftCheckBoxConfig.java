package com.easy.recycleview.custom.baseview.config;

import android.view.View;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.AddressItemBean;


/**
 * Created by Chant on 2018/6/10.
 */

public class LeftCheckBoxConfig {
    public static void load(ContentItemView contentItemView, AddressItemBean dataItemBean) {
        if (dataItemBean.isShowLeftCheckBox()) {
            contentItemView.mLeftCheckBox.setVisibility(View.VISIBLE);
            boolean isChecked = dataItemBean.isLeftCheckBoxIsChecked();
            if ( !contentItemView.mChangeSelectRefresh) {
                isChecked =contentItemView. checkContain(dataItemBean);
                dataItemBean.setLeftCheckBoxIsChecked(isChecked);
            }
            contentItemView. mChangeSelectRefresh = false;
            contentItemView. mLeftCheckBox.setChecked(isChecked);
        } else {
            contentItemView. mLeftCheckBox.setVisibility(View.GONE);
        }
    }
}
