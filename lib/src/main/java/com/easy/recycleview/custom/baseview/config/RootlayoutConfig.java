package com.easy.recycleview.custom.baseview.config;

import com.easy.recycleview.ContentItemView;
import com.easy.recycleview.bean.DyItemBean;


/**
 * Created by Chant on 2018/6/17.
 */

public class RootlayoutConfig {
    public static  void load(ContentItemView itemView, DyItemBean dataItemBean) {
//        LinearLayout.LayoutParams rootParams = (LinearLayout.LayoutParams) itemView.mRootlayout.getLayoutParams();
//        if (itemView.mBindItemBean.getItemHight() != 0) {
//            rootParams.height = itemView.mBindItemBean.getItemHight();
//        } else {
//            rootParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        }
//        itemView.mRootlayout.setLayoutParams(rootParams);
          if (null!=dataItemBean.getRootlayoutSetting().getBackground()){
              itemView.mRootlayout.setBackground(dataItemBean.getRootlayoutSetting().getBackground());
          }

        ItemBgConfg.load(itemView,dataItemBean);

    }
}
