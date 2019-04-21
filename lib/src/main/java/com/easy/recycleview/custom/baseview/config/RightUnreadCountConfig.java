package com.easy.recycleview.custom.baseview.config;


import com.easy.recycleview.custom.baseview.item.ContentItemView;
import com.easy.recycleview.custom.bean.AddressItemBean;

/**
 * Created by Chant on 2018/6/17.
 */

public class RightUnreadCountConfig {
    public static  void load(ContentItemView itemView, AddressItemBean dataItemBean) {


        if (dataItemBean.getRightUnreadCount() != 0) {
//            mMessageCountView.setVisibility(View.VISIBLE);
//            float textSize = ResourcesUtil.getResourcesFloat(mContext, R.string.recentcontactsitemview_textsize);
//            mMessageCountView.setTextSize(textSize);
//            mMessageCountView.setImage(R.drawable.item_recentcontacts_donotbother);
//            mMessageCountView.setMessageCount(dataItemBean.getRightUnreadCount());
        } else {
//            mMessageCountView.reset();
        }

    }
}
