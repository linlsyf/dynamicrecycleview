package com.easy.recycleview.recycleview.item.config;

import android.view.View;

import com.easy.recycleview.recycleview.item.AddressItemBean;
import com.easy.recycleview.recycleview.item.ContentItemView;

/**
 * Created by Chant on 2018/6/17.
 */

public class SwitchButtonConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {


//     
//        //添加选中按钮显示
        if (dataItemBean.isShowRightCheckbox()) {
            itemView.mSwitchButton.setCheckState(dataItemBean.isRightCheckBoxSelect());
            itemView.mSwitchButton.setVisibility(View.VISIBLE);
        } else {
            itemView.mSwitchButton.setVisibility(View.GONE);
        }
    }

    }