package com.easy.recycleview.custom.baseview.config;

import android.view.View;
import android.widget.RelativeLayout;

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

           boolean isImagShow=false;
        CentLayoutConfig  config=dataItemBean.getCentLayoutConfig();
          if (config.getImgResId()!=0){
              contentItemView.mCentImg.setVisibility(View.VISIBLE);
              contentItemView.mCentImg.setImageResource(config.getImgResId());
              isImagShow=true;
          }
          if (StringUtils.isNotEmpty(config.getImgUrl())){
              isImagShow=true;
              if ( RecycleConfig.getInstance().getIloadImage()!=null) {
                  RecycleConfig.getInstance().getIloadImage().load(dataItemBean.getHeadImgeSettings().getHeadImgUrl(), contentItemView.mCentImg);
              }
          }

            if (StringUtils.isNotEmpty(config.getName())){

              if (!isImagShow){
              RelativeLayout.LayoutParams  layoutParams=(RelativeLayout.LayoutParams) contentItemView.mCentTv.getLayoutParams();
                      layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

              }
                contentItemView.mCentTv.setVisibility(View.VISIBLE);
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
}
