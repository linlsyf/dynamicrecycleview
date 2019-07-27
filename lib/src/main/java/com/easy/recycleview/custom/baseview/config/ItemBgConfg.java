package com.easy.recycleview.custom.baseview.config;

import com.easy.recycleview.ContentItemView;
import com.easy.recycleview.bean.BgSetting;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;

/**
 * Created by Administrator on 2019/5/22 0022.
 */

public class ItemBgConfg {
    public static  void load(final ContentItemView itemView, DyItemBean dataItemBean) {
        BgSetting bgSetting =dataItemBean.getBgSetting();
        int colorResId= bgSetting.getContentBgResid();
        int commonColorResId= RecycleConfig.getInstance().getThemeConfig().getBgColorResId();
        int bgResourcResId= RecycleConfig.getInstance().getThemeConfig().getBgResourcResId();

         if (bgSetting.getContentBgResid()!=0){
             itemView.mRootlayout.setBackgroundResource(bgSetting.getContentBgResid());

         }

       else  if (bgResourcResId!=0){
            itemView.mRootlayout.setBackgroundResource(bgResourcResId);
        }
       else  if (colorResId!=0){
            itemView.mRootlayout.setBackgroundColor(colorResId);
        }
        else  if(commonColorResId!=0){
            itemView.mRootlayout.setBackgroundColor(commonColorResId);
        }


    }
}
