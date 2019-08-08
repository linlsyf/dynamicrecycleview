package com.easy.recycleview.custom.baseview.config;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.StringUtils;
import com.easy.recycleview.bean.CentLayoutConfig;
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
            if (config.getImgResId()!=0|StringUtils.isNotEmpty(config.getImgUrl())){
                 isImagShow=true;
                 contentItemView.mCentMsgLayout.setVisibility(View.VISIBLE);
             }

          if (config.getImgResId()!=0){
              contentItemView.mCentImg.setVisibility(View.VISIBLE);

//              contentItemView.mCentImg.set
              contentItemView.mCentImg.setImageResource(config.getImgResId());
          }
          if (StringUtils.isNotEmpty(config.getImgUrl())){
              isImagShow=true;
              contentItemView.mCentImg.setVisibility(View.VISIBLE);
              if ( RecycleConfig.getInstance().getIloadImage()!=null) {
                  RecycleConfig.getInstance().getIloadImage().load(config.getImgUrl(), contentItemView.mCentImg);
              }
          }



        if (isImagShow){

            LinearLayout.LayoutParams  layoutParams=(LinearLayout.LayoutParams) contentItemView.mCentImg.getLayoutParams();
               if (config.getImgRadius()!=0){
                   layoutParams.width=config.getImgRadius();
                   layoutParams.height=config.getImgRadius();
               }

        }

            if (StringUtils.isNotEmpty(config.getName())) {

                int colorResId = dataItemBean.getTitleSettings().getColor();
                int titleColorResId = RecycleConfig.getInstance().getThemeConfig().getTitleColorResId();

                if (isImagShow){
                    contentItemView.mCentTv.setText(config.getName());
                    if (colorResId != 0) {
                        contentItemView.mCentTv.setTextColor(colorResId);
                    } else if (titleColorResId != 0) {
                        contentItemView.mCentTv.setTextColor(titleColorResId);
                    }
                }else{
                    contentItemView.mCentSingleTv.setVisibility(View.VISIBLE);

                    contentItemView.mCentSingleTv.setText(config.getName() );
                    if (colorResId != 0) {
                        contentItemView.mCentSingleTv.setTextColor(colorResId);
                    } else if (titleColorResId != 0) {
                        contentItemView.mCentSingleTv.setTextColor(titleColorResId);
                    }

                }


            }

    }
}
