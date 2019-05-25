package com.easy.recycleview.custom.baseview.config;

import android.widget.LinearLayout;

import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.DensityUtil;
import com.easy.recycleview.custom.bean.DyItemBean;
import com.easy.recycleview.inter.IItemView;
import com.easysoft.dynamicrecycleview.R;


/**
 * Created by Chant on 2018/6/17.
 */

public class ContentLayoutConfig {
    public static void load(ContentItemView itemView, DyItemBean dataItemBean) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) itemView.mContenLayout.getLayoutParams();
        LinearLayout.LayoutParams paramsItemView = ( LinearLayout.LayoutParams) itemView.getLayoutParams();





        int mContentMagin=0;

        if (dataItemBean.getViewType()== IItemView.ViewTypeEnum.ITEM.value()){
            params.height=DensityUtil.dip2px(itemView.getContext(), 50);

            if (dataItemBean.getContentLayoutMagin() != 0) {
                mContentMagin = dataItemBean.getContentLayoutMagin();
            } else {
                mContentMagin = DensityUtil.dip2px(itemView.getContext(), 10);
            }
        }
        if (dataItemBean.getViewType()== IItemView.ViewTypeEnum.SECTION.value()){

            int height=DensityUtil.dip2px(itemView.getContext(), 25);
            if (paramsItemView==null){
                paramsItemView= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            }
        }
        else if (dataItemBean.getViewType()== IItemView.ViewTypeEnum.SPLITE.value()){
            int height=DensityUtil.dip2px(itemView.getContext(), 1);
            if (paramsItemView==null){
                paramsItemView= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
            }
            itemView.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.context_bg_grey));
        }

        params.topMargin = mContentMagin;
        params.bottomMargin = mContentMagin;
        itemView.mContenLayout.setLayoutParams(params);

         if (null!=paramsItemView){
             itemView.setLayoutParams(paramsItemView);

         }


    }
}