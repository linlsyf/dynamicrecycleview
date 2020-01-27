package com.easy.recycleview.custom.baseview.config;


import com.easy.recycleview.ContentItemView;
import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.bean.TitleSettings;
import com.easy.recycleview.outinter.RecycleConfig;

/**
 * Created by Chant on 2018/6/17.
 */

public class TitleTextViewConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
            itemView.mTitleTextView.setText(dataItemBean.getTitle());
        TitleSettings settings=dataItemBean.getTitleSettings();
             int colorResId=settings.getColor();
             int titleColorResId=0;
              if (null!=RecycleConfig.getInstance().getThemeConfig()){

                  titleColorResId= RecycleConfig.getInstance().getThemeConfig().getTitleColorResId();
              }
              if (colorResId!=0){
                  itemView.mTitleTextView.setTextColor(colorResId);
              }else  if(titleColorResId!=0){
                  itemView.mTitleTextView.setTextColor(titleColorResId);
              }
              if (settings.getLines()>0){
                  itemView.mTitleTextView.setLines(settings.getLines());
              }



    }
}
