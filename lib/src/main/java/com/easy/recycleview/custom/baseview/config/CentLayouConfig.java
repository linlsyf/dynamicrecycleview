package com.easy.recycleview.custom.baseview.config;

import android.view.View;

import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.custom.baseview.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.StringUtils;
import com.easy.recycleview.custom.bean.CentLayoutConfig;
import com.easy.recycleview.outinter.RecycleConfig;

/**
 * Created by Administrator on 2019/6/29 0029.
 */

public class CentLayouConfig {


    public static void load(ContentItemView contentItemView, DyItemBean dataItemBean) {

          if (null==dataItemBean.getCentLayoutConfig()){
              return;
          }
        contentItemView.mCentLayout.setVisibility(View.VISIBLE);

        CentLayoutConfig  config=dataItemBean.getCentLayoutConfig();
          if (config.getImgResId()!=0){
              contentItemView.mCentImg.setVisibility(View.VISIBLE);
              contentItemView.mCentImg.setImageResource(config.getImgResId());

          }
          if (StringUtils.isNotEmpty(config.getImgUrl())){
              if ( RecycleConfig.getInstance().getIloadImage()!=null) {
                  RecycleConfig.getInstance().getIloadImage().load(dataItemBean.getHeadImgeSettings().getHeadImgUrl(), contentItemView.mCentImg);
              }
          }

          contentItemView.mCentTv.setText(config.getName());
        int colorResId=dataItemBean.getTitleSettings().getColor();
        int titleColorResId= RecycleConfig.getInstance().getThemeConfig().getTitleColorResId();
        if (colorResId!=0){
            contentItemView.mCentTv.setTextColor(colorResId);
        }else  if(titleColorResId!=0){
            contentItemView.mCentTv.setTextColor(titleColorResId);
        }
    }
}
