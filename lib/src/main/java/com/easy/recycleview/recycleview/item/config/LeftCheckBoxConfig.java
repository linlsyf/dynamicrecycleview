package com.easy.recycleview.recycleview.item.config;

import android.view.View;
import android.widget.CheckBox;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;
import com.easy.recycleview.recycleview.sectionview.MutiTypeSelectUtils;

/**
 * Created by Chant on 2018/6/10.
 */

public class LeftCheckBoxConfig {
    public static void load(ContentItemView contentItemView, AddressItemBean dataItemBean) {
        if (dataItemBean.isShowLeftCheckBox()) {
            contentItemView.mLeftCheckBox.setVisibility(View.VISIBLE);
            boolean isChecked = dataItemBean.isLeftCheckBoxIsChecked();
            if (contentItemView.mSelectUtils != null && !contentItemView.mChangeSelectRefresh) {
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
