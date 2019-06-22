package com.easy.recycleview.custom.baseview.config;

import android.view.View;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.bean.DyItemBean;


/**
 * Created by Chant on 2018/6/17.
 */

public class SwitchButtonConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {


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
