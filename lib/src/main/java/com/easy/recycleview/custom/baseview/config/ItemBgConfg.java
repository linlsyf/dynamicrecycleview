package com.easy.recycleview.custom.baseview.config;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.BgSetting;
import com.easy.recycleview.custom.bean.DyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;

/**
 * Created by Administrator on 2019/5/22 0022.
 */

public class ItemBgConfg {
    public static  void load(final ContentItemView itemView, DyItemBean dataItemBean) {
        BgSetting bgSetting =dataItemBean.getBgSetting();
        int colorResId= bgSetting.getContentBgResid();
        int titleColorResId= RecycleConfig.getInstance().getThemeConfig().getBgColorResId();
        if (colorResId!=0){
            itemView.mRootlayout.setBackgroundColor(colorResId);
        }else  if(titleColorResId!=0){
            itemView.mRootlayout.setBackgroundColor(titleColorResId);
//            itemView.mRootlayout.setBackgroundResource(titleColorResId);
        }


    }
}