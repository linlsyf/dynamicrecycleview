package com.easy.recycleview.custom.baseview.config;


import com.easy.recycleview.ContentItemView;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.outinter.RecycleConfig;

/**
 * Created by Chant on 2018/6/17.
 */

public class TitleTextViewConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
            itemView.mTitleTextView.setText(dataItemBean.getTitle());
             int colorResId=dataItemBean.getTitleSettings().getColor();
             int titleColorResId= RecycleConfig.getInstance().getThemeConfig().getTitleColorResId();
              if (colorResId!=0){
                  itemView.mTitleTextView.setTextColor(colorResId);
              }else  if(titleColorResId!=0){
                  itemView.mTitleTextView.setTextColor(titleColorResId);
              }

    }
}
