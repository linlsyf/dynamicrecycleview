package com.easy.recycleview.custom.baseview.config;

import android.support.v7.widget.GridLayoutManager;
import android.widget.RelativeLayout;

import com.easy.recycleview.bean.DyItemBean;
import com.easy.recycleview.ContentItemView;
import com.easy.recycleview.custom.baseview.utils.DensityUtil;
import com.easy.recycleview.inter.IItemView;
import com.easysoft.dynamicrecycleview.R;


/**
 * Created by Chant on 2018/6/17.
 */

public class ContentLayoutConfig {
    public static void load(ContentItemView itemView, DyItemBean dataItemBean) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) itemView.mContenLayout.getLayoutParams();
        GridLayoutManager.LayoutParams paramsItemView = ( GridLayoutManager.LayoutParams) itemView.getLayoutParams();

        int mContentMagin=0;

        if (dataItemBean.getViewType()== IItemView.ViewTypeEnum.ITEM.value()){
            //params.height=DensityUtil.dip2px(itemView.getContext(), 50);

            if (dataItemBean.getContentLayoutMagin() != 0) {
                mContentMagin = dataItemBean.getContentLayoutMagin();
            } else {
                mContentMagin = DensityUtil.dip2px(itemView.getContext(), 10);
            }
            params.topMargin = mContentMagin;
            params.bottomMargin = mContentMagin;
            itemView.mContenLayout.setLayoutParams(params);

        }
        if (dataItemBean.getViewType()== IItemView.ViewTypeEnum.SECTION.value()){

            int height=DensityUtil.dip2px(itemView.getContext(), 25);
            if (paramsItemView==null){
                paramsItemView= new GridLayoutManager.LayoutParams(GridLayoutManager.LayoutParams.MATCH_PARENT, height);
            }

             itemView.setLayoutParams(paramsItemView);

        }
        else if (dataItemBean.getViewType()== IItemView.ViewTypeEnum.SPLITE.value()){
            int height=DensityUtil.dip2px(itemView.getContext(), 1);
            if (paramsItemView==null){
                paramsItemView= new GridLayoutManager.LayoutParams(GridLayoutManager.LayoutParams.MATCH_PARENT, height);
            }

             if (dataItemBean.getBgSetting().getContentBgColorid()!=0){
                 itemView.mRootlayout.setBackgroundColor(dataItemBean.getBgSetting().getContentBgColorid());

             }else{
                 itemView.mRootlayout.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.context_bg_grey));

             }
            itemView.setLayoutParams(paramsItemView);
        }




    }
}